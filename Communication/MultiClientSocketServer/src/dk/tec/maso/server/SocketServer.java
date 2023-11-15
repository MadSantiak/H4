package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
	private static List<PrintWriter> writers = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("Server Started");
		
		try(ServerSocket serverSocket = new ServerSocket(2000))
		{
			while(true) 
			{
				Socket socket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				writers.add(writer);

				ClientWorker w = new ClientWorker(socket, writer);
				Thread t = new Thread(w);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Server Ended");
	}
	
	public static void sendAll(String msg) {
		// For each "writer" in the list "writers", we send the message (msg):
		for (PrintWriter writer : writers)
		{
			writer.println(msg + "\n");
		}
	}

}
