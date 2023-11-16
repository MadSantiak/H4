package dk.tec.maso.server;

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

import javax.swing.JFrame;


public class PlayerClient extends JFrame implements MouseListener, MouseMotionListener  {
	private static PlayerClient client;
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

			Thread t1 = new Thread(new ServerListener(in, this));
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
		g.setColor(Color.RED);
		g.fillRect(xEnemy, yEnemy, size, size);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > xPos && x < xPos + size &&
			y > yPos && y < yPos + size) 
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(xPos);
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
