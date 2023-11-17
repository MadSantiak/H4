package dk.tec.maso.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import dk.tec.maso.object.Player;

public class ServerListener implements Runnable {
	private BufferedReader reader;
	private PlayerClient client;
	private ArrayList<Player> players;

	public ServerListener(BufferedReader reader, PlayerClient client, ArrayList<Player> players) {
		this.reader = reader;
		this.client = client;
		this.players = players;
	}

	public void run() {
		try {
			while (true) {
				String msg = reader.readLine();
				System.out.println(msg);
				if (msg.equals("game_start")) {
					client.setGameStarted(true);
				} else if (msg.split(",")[0].equals("new_player")) {
					int newId = Integer.parseInt(msg.split(",")[1]);
					client.handleNewPlayer(newId);
				} else if (msg.startsWith("existing_player")) {
	                String[] playerInfo = msg.split(",");
	                int existingId = Integer.parseInt(playerInfo[1]);
	                int existingX = Integer.parseInt(playerInfo[2]);
	                int existingY = Integer.parseInt(playerInfo[3]);
	                Player existingPlayer = new Player(existingId, existingX, existingY);
	                players.add(existingPlayer);
	                client.repaint();
	            } else {
					String[] positions = msg.split(",");
					int id = Integer.parseInt(positions[0]);
					int xPos = Integer.parseInt(positions[1]);
					int yPos = Integer.parseInt(positions[2]);
					client.setEnemyPosition(id, xPos, yPos);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
