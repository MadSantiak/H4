package dk.tec.maso.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import dk.tec.maso.object.Player;
import dk.tec.maso.server.GameServer;


public class PlayerClient extends JFrame implements MouseListener, MouseMotionListener  {
	private static PlayerClient client;
	private ArrayList<Player> players = new ArrayList<>();
	Socket sock;
	BufferedReader in;
	PrintWriter out;
	
	
	int xPos = 100;
	int yPos = 100;
	int xPrev, yPrev;
	int xEnemy = 250;
	int yEnemy = 250;
	int size = 50;
	boolean dragging;
	boolean gameStarted;
	
	public boolean isGameStarted() {
		return gameStarted;
	}

	/**
	 * Helper method used to set a boolean field
	 * which is then later used to check if the game is started
	 * specifically whether "dragging" is allowed yet (checked when "pressed").
	 * It is called by the ServerListener when the GameServer
	 * detects all necessary players have connected
	 * @param gameStarted
	 */
	synchronized public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
		if (gameStarted) {
            JLabel start = new JLabel("Game is starting!");
            client.add(start, BorderLayout.CENTER);
            start.setBounds((getWidth() - 150) / 2, getHeight() / 2, 150, 20);
            start.setHorizontalAlignment(SwingConstants.CENTER);

            try {
				Thread.sleep(2000);
				client.remove(start);
				client.revalidate();
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
           
        }
	}

	/**
	 * Helper method used to set the position of the "Enemy" box,
	 * this is called by the ServeListener when the correct prompt is received.
	 * @param x
	 * @param y
	 */
	synchronized public void setEnemyPosition(int id, int x, int y) {
		for (Player p : players) {
			if (p.getId() == id) {
				p.setX(x);
				p.setY(y);
				repaint();
				return;
			}
		}
	}
	

	public void handleNewPlayer(int id) {
        Player newPlayer = new Player(id, 100,100);
        System.out.println("Handel new player");
        players.add(newPlayer);
        System.out.println(GameServer.getPlayers());
        repaint();
    }

//
//    public void handlePlayerPosition(int id, int x, int y) {
//        for (Player player : players) {
//            if (player.getId() == id) {
//                player.setX(x);
//                player.setY(y);
//                break;
//            }
//        }
//        repaint();
//    }
//	
	public static void main(String[] args) {
		client = new PlayerClient();
		client.setBounds(200, 200, 400, 400);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setTitle("Green-Red Box Game");
		client.setVisible(true);
	}
	
	public PlayerClient() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		try {
			sock = new Socket("localhost", 2000);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			Thread t1 = new Thread(new ServerListener(in, this, players));
			t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GREEN);
		g.fillRect(xPos, yPos, size, size);
		System.out.println(players);
		System.out.println(GameServer.getPlayers());
		for (Player player : players) {
			g.setColor(Color.RED);
			g.fillRect(player.getX(), player.getY(), size, size);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > xPos && x < xPos + size &&
			y > yPos && y < yPos + size &&
			gameStarted) 
		{
			
			dragging = true;
			xPrev = x;
			yPrev = y;
		}
		
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(dragging) {
			xPos += x - xPrev;
			yPos += y - yPrev;
			
			xPrev = x;
			yPrev = y;

			out.println(xPos + "," + yPos);
			repaint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




}
