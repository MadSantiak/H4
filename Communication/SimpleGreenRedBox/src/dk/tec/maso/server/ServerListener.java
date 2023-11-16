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
			while (true) {
				String msg = reader.readLine();
				if (msg.equals("game_start")) {
					client.setGameStarted(true);
				} else {

					String[] positions = msg.split(",");
					int xPos = Integer.parseInt(positions[0]);
					int yPos = Integer.parseInt(positions[1]);
					System.out.println("Setting position..");
					client.setEnemyPosition(xPos, yPos);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
