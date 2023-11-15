package dk.tec.maso;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {

	private static List<PrintWriter> writers = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("Server Started!");
		
		// We create the socket for the server
		try (ServerSocket servSock = new ServerSocket(2000)) {
			// And loop infinitely
			while(true) {
				System.out.println("ServerSocket..");
				// Wait for someone to connect:
				Socket clientSock = servSock.accept();
				
				// We get the output stream from the connected client, and add it to a list of writers
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream(), true);
				writers.add(writer);
				
				// We initialize the ClientWorker thread for the writer,
				// listening on the input-stream of the client in question,
				// and sending it to all client writers when a message is received.
				ClientWorker w = new ClientWorker(clientSock, writer);
				Thread clientThread = new Thread(w);
				clientThread.start();
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Server Ended!");
	}
	
	public static void sendAll(String msg) {
		// For each "writer" in the list "writers", we send the message (msg):
		for (PrintWriter writer : writers)
		{
			writer.println(msg);
		}
	}

}
