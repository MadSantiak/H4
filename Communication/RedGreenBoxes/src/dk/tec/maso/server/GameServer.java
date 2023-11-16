package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import dk.tec.maso.object.RectangleObject;
import dk.tec.maso.object.SendPosition;

public class GameServer {
	public static ArrayList<PrintWriter> players = new ArrayList<>();
	private static RectangleObject data = new RectangleObject();
	
	public static void main(String[] args) {
		System.out.println("Server started..");
		
		Thread t1 = new Thread(new SendPosition(players, data));
		t1.start();

		try (ServerSocket serverSocket = new ServerSocket(2000))
		{
			while(true) {
				Socket s = serverSocket.accept();
				PrintWriter writer = new PrintWriter(s.getOutputStream());
				players.add(writer);
				PlayerWorker pw = new PlayerWorker(s, writer);
				Thread t2 = new Thread(pw);
				t2.start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendPosition(String position) {
		for (PrintWriter w : players) {
			System.out.println(position);
			w.println(position);
		}
	}

}
