package dk.tec.maso.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import dk.tec.maso.object.Player;

public class PlayerWorker implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter writer;
	private Player player;
	
	public PlayerWorker(Socket socket, PrintWriter writer, Player player) {
		this.socket = socket;
		this.writer = writer;
		this.player = player;
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
				msg = player.getId() + "," + msg;
				GameServer.sendPosition(msg, writer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}