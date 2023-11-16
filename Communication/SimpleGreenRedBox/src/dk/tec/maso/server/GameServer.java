package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class GameServer {
	public static ArrayList<PrintWriter> writers = new ArrayList<>();

	private static final int NUMBER_OF_PLAYERS = 2;
	private static CountDownLatch playersLatch = new CountDownLatch(NUMBER_OF_PLAYERS);
	
	public static void main(String[] args) {
		System.out.println("Server started..");
		
		try (ServerSocket serverSocket = new ServerSocket(2000))
		{
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				Socket s = serverSocket.accept();
				PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
				writers.add(writer);
				
				PlayerWorker pw = new PlayerWorker(s, writer);
				Thread t2 = new Thread(pw);
				t2.start();
				
				playersLatch.countDown();
				System.out.println(playersLatch.getCount());
				
			}
			try {
				playersLatch.await();
				System.out.println(writers);
				for (PrintWriter p : writers)
				{
					System.out.println("Printing to: " + p);
					p.println("game_start");
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendPosition(String position, PrintWriter origin) {
		for (PrintWriter w : writers) {
			if (w != origin) {
				w.println(position);
				w.flush();
			}
		}
	}

}
