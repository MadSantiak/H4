package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import dk.tec.maso.object.Player;

public class GameServer {
	public static ArrayList<PrintWriter> writers = new ArrayList<>();
	public static ArrayList<Player> players = new ArrayList<>();
	public static int playerId = 0;

	synchronized public static ArrayList<Player> getPlayers() {
		return players;
	}
	
	public static void main(String[] args) {
		System.out.println("Server started..");

		try (ServerSocket serverSocket = new ServerSocket(2000)) {
			/**
			 * Due to the use of "CountDownLatch" to ensure the correct number of players
			 * I've reverted this to a for-loop rather than while as the while would: (1) be
			 * blocked by the latch await() from reiteration (2) be blocked from the logic
			 * to fire even if all connections were made, as it would be stuck in a loop.
			 */
			while (true) {
				Socket s = serverSocket.accept();
				PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
				writers.add(writer);
				Player player = new Player(playerId, 100, 100);
				playerId++;
				
				
				for (Player existingPlayer : players) {
	                	writer.println("existing_player," + existingPlayer.getId() + "," + existingPlayer.getX() + "," + existingPlayer.getY());				
	                }
				
				players.add(player);

				newPlayer(player, writer);
				PlayerWorker pw = new PlayerWorker(s, writer, player);
				Thread t2 = new Thread(pw);
				t2.start();

				startGame();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newPlayer(Player player, PrintWriter writer) {
		for (PrintWriter w : writers) {
			if (w != writer) {
				w.println("new_player," + player.getId());
			}
		}
	}

	public static void startGame() {
		System.out.println("Starting..");
		for (PrintWriter player : writers) {
			player.println("game_start");
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
