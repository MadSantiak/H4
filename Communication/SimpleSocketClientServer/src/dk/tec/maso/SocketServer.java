package dk.tec.maso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		System.out.println("Server started..");
		
		try (
			ServerSocket serverSocket = new ServerSocket(2000);
			Socket ioSocket = serverSocket.accept();
			)
		{
			BufferedReader in = 
				new BufferedReader(
					new InputStreamReader(ioSocket.getInputStream()));
			PrintWriter out = new PrintWriter(ioSocket.getOutputStream(), true);
			
			String inString = in.readLine();
			out.println("Client wrote: " + inString);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
