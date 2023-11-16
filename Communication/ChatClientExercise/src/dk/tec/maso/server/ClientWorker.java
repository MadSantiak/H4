package dk.tec.maso.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientWorker implements Runnable {

	Socket s;
	ClientMessage dataMessage;

	public ClientWorker(Socket s, ClientMessage dataMessage) {
		this.s = s;
		this.dataMessage = dataMessage;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String username = in.readLine();
			String msg;
			boolean connected = true;
			while (connected) {
				msg = in.readLine();
				if (msg == null) {
					connected = false;
				} else {
					System.out.println("Setting message: " + msg);
					dataMessage.setMessage(username + ": " + msg);
					dataMessage.setNewMessage(true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			ChatServer.removeSocket(s);
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
