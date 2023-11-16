package dk.tec.maso.player;

import java.io.BufferedReader;
import java.io.IOException;

import dk.tec.maso.object.RectangleObject;

public class ServerListener implements Runnable {

	private BufferedReader reader;
	private RectangleObject playerRectangle;
	
	public ServerListener(BufferedReader reader, RectangleObject playerRectangle) {
		this.reader = reader;
		this.playerRectangle = playerRectangle;
	}
	
	@Override
	public void run() {
		String pos;
		while (true)
		{
			try {
				if (playerRectangle.isNewValue())
				{
					pos = reader.readLine();
					String[] positions = pos.split(",");
					int xPos = Integer.parseInt(positions[0]);
					int yPos = Integer.parseInt(positions[1]);
					playerRectangle.setEnemyX(xPos);
					playerRectangle.setEnemyY(yPos);
					playerRectangle.repaint();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
