package dk.tec.maso.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable {
	private BufferedReader reader;
	private SocketClient client;
	
	public ServerListener(BufferedReader reader, SocketClient client) {
		this.reader = reader;
		this.client = client;
	}
	
	public void run() {
		try {
			String msgFromServer;
			while ((msgFromServer = reader.readLine()) != null) {
//				client.txtReceived.setText(client.txtReceived.getText() + msgFromServer + "\n");
				client.txtReceived.append(msgFromServer);

			} 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

