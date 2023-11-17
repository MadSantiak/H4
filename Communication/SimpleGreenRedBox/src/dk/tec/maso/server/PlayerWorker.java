package dk.tec.maso.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PlayerWorker implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter writer;
	
	public PlayerWorker(Socket socket, PrintWriter writer) {
		this.socket = socket;
		this.writer = writer;
		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String msg;
		boolean run = true;
		try {
			while(run)
			{
				msg = in.readLine();
				if (msg == null) {
					run = false;
				}
				GameServer.sendPosition(msg, writer);
			}
		} catch (SocketTimeoutException ste) {
			System.out.println("Socket timed out..");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException io) {
				io.printStackTrace();
			}
			
		}
	}
}