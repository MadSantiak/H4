package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class GameServer {
	public static ArrayList<PrintWriter> writers = new ArrayList<>();
	public static ArrayList<Socket> sockets = new ArrayList<>();

	private static final int NUMBER_OF_PLAYERS = 2;
	private static Semaphore playersSem = new Semaphore(NUMBER_OF_PLAYERS);

	private static boolean gameReset;
	
	synchronized static public ArrayList<Socket> getSockets() {
		return  sockets;
	}
	
	public static void main(String[] args) {
		System.out.println("Server started..");
		try (ServerSocket serverSocket = new ServerSocket(2000)) {
			
			/**
			 * Anonymous thread used to listen for disconnecting clients,
			 * allowing us to release the semaphore for re-use
			 * i.e. letting another player join.
			 */
			Thread checkConnections = new Thread(() -> {
				
				while (true) {
					Iterator<Socket> sockIt = sockets.iterator();
//					for (int i = 0; i < sockets.size(); i++) {
					while (sockIt.hasNext()) {
						Socket s = sockIt.next();
						if (s.isClosed()) {
							System.out.println("Disconnecting player");
							sockIt.remove();
							playersSem.release();
							gameReset = true;
							
						}
						if (!s.isClosed() && gameReset == true) {
							stopGame();
						}
						
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			checkConnections.start();

			while (true) {
				Socket s = serverSocket.accept();
				sockets.add(s);
				PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
				writers.add(writer);

				PlayerWorker pw = new PlayerWorker(s, writer);
				Thread t2 = new Thread(pw);
				t2.start();

				try {
					playersSem.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Player connected. Remaining before game starts: " + playersSem.availablePermits());
				if (playersSem.availablePermits() == 0) {
					startGame();
					gameReset = false;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Helper method that informs players the game can start, when the appropriate
	 * number of players have connected.
	 */
	public static void startGame() {
		System.out.println("Starting..");
		for (PrintWriter player : writers) {
			player.println("game_start");
		}
	}
	
	public static void stopGame() {
		System.out.println("Stopping..");
		for (PrintWriter player : writers) {
			player.println("game_stop");
		}
	}

	/**
	 * Communication helper. Note I've modified the parameters to accept the
	 * PrintWriter from the calling PlayerClient (via PlayerWorker), so we can check
	 * for origin.
	 * 
	 * @param position
	 * @param origin
	 */
	public static void sendPosition(String position, PrintWriter origin) {
		for (PrintWriter w : writers) {
			if (w != origin) {
				w.println(position);
				w.flush();
			}
		}
	}

}
