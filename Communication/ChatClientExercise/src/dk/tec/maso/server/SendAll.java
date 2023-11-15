package dk.tec.maso.server;

import java.net.Socket;
import java.util.ArrayList;

public class SendAll implements Runnable {
	
	private ClientMessage dataMessage;
	private ArrayList<Socket> sockets;

	public SendAll(ArrayList<Socket> sockets, ClientMessage dataMessage) {
		this.sockets = sockets;
		this.dataMessage = dataMessage;
		
	}

	@Override
	public void run() {
	}

}
