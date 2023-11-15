package dk.tec.maso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	static public String name;
	
	public static void main(String[] args)
	{
		// We try to connect to the socket localhost:2000:
		try (Socket sock = new Socket("localhost", 2000)){
			System.out.println("Client socket..");
			
			// Establishing IO from the socket, as well as from the console.
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader con = new BufferedReader(new InputStreamReader(System.in));

			// We ask the client for their preferred Username when communicating with other clients
			System.out.println("Please enter username:");
			String username = con.readLine();
			out.println(username + " joined the chat...");
			
			// And initate a Thread for ServerListener,
			// so we can continously get messages from the Server,
			// I.e. when the server sends out messages (from other clients):
			Thread t1 = new Thread(new ServerListener(in));
			t1.start();
			
			// We then get what the user writes in their console
			String userMsg = con.readLine();
			
			// And while there is something to send (when valid responses are typed)
			// we send that response to the out-stream on the Socket, sending it to the server.
			while (userMsg != null) {
				out.println(username + ": " + userMsg);
				userMsg = con.readLine();
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
