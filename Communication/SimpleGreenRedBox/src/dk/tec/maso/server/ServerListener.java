package dk.tec.maso.server;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable {
	private BufferedReader reader;
	private PlayerClient client;
	
	public ServerListener(BufferedReader reader, PlayerClient client) {
		this.reader = reader;
		this.client = client;
	}
	
	public void run() {
		try {
			String msgFromServer;
			while ((msgFromServer = reader.readLine()) != null) {
//				client.txtReceived.setText(client.txtReceived.getText() + msgFromServer + "\n");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(msgFromServer);

			} 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
