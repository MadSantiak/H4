package dk.tec.maso.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		try {
			while(true)
			{
				msg = in.readLine();
				GameServer.sendPosition(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}