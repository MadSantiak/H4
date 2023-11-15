package dk.tec.maso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClientWorker implements Runnable {
	private Socket client;
	private PrintWriter writer;
	

	public ClientWorker(Socket client, PrintWriter writer) {
		this.client = client;
		this.writer = writer;
	}

	@Override
	public void run() {
		// We get the input-stream of the client connection,
		// And when a message is received, we send the message out to all writers
		try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))){
			String msg;
			while((msg = in.readLine()) != null)
			{
				ServerMain.sendAll(msg);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		
	}
	
	
}
