package dk.tec.maso.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SendAll implements Runnable {

	private ClientMessage dataMessage;
	private ArrayList<Socket> sockets;
	int count;

	public SendAll(ArrayList<Socket> sockets, ClientMessage dataMessage) {
		this.sockets = sockets;
		this.dataMessage = dataMessage;

	}

	@Override
	public void run() {
		PrintWriter out = null;
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			if (dataMessage.isNewMessage()) {
				String msg = dataMessage.getMessage();
				for (Socket s : sockets) {
					try {
						System.out.println("Sending all: " + msg);
						out = new PrintWriter(s.getOutputStream(), true);
						out.println(msg);
						System.out.println("Sent msg..");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				dataMessage.setNewMessage(false);
			}
		}
	}
}
