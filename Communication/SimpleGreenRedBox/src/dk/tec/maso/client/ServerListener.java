package dk.tec.maso.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ServerListener implements Runnable {
	private BufferedReader reader;
	private PlayerClient client;

	public ServerListener(BufferedReader reader, PlayerClient client) {
		this.reader = reader;
		this.client = client;
	}

	public void run() {
		try {
			while (true) {
				String msg = reader.readLine();
				if (msg.equals("game_start")) {
					client.setGameStarted(true);
				} else if (msg.equals("game_stop")) {
					client.setGameStarted(false);
				
				} else if (msg != null) {
					String[] positions = msg.split(",");
					if (positions.length == 2) {
						int xPos = Integer.parseInt(positions[0]);
						int yPos = Integer.parseInt(positions[1]);
						client.setEnemyPosition(xPos, yPos);
					}
				}
			}
		} catch (SocketException se) {
			System.out.println("Socket closed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
