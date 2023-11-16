package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
	public static ArrayList<PrintWriter> writers = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Server started..");
		
		try (ServerSocket serverSocket = new ServerSocket(2000))
		{
			while(true) {
				Socket s = serverSocket.accept();
				PrintWriter writer = new PrintWriter(s.getOutputStream());
				writers.add(writer);
				
				PlayerWorker pw = new PlayerWorker(s, writer);
				Thread t2 = new Thread(pw);
				t2.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendPosition(String position) {
		for (PrintWriter w : writers) {
			System.out.println(position);
			w.println(position);
		}
	}

}
