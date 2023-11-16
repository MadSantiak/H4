package dk.tec.maso.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RectangleObject extends JFrame implements MouseListener, MouseMotionListener {
	
	public String name;
	private PrintWriter writer;
	public int xPos = 100;
	public int yPos = 100;
	public int xEnemy = 200;
	public int yEnemy = 200;
	int xPrev, yPrev;
	int size = 50;
	boolean dragging;
	boolean newValue;
	
	synchronized public void setNewValue(boolean newValue) {
		this.newValue = newValue;
	}
	
	synchronized public boolean isNewValue() {
		System.out.print("HURHRHR");
		return newValue;
	}

	synchronized public int getEnemyX() {
		return xEnemy;
	}
	synchronized public int getEnemyY() {
		return yEnemy;
	}
	
	synchronized public void setEnemyX(int value) {
		this.xEnemy = value;
	}
	synchronized public void setEnemyY(int value) {
		this.yEnemy = value;
	}
	
	public RectangleObject()
	{
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public RectangleObject(PrintWriter writer) {
		this.writer = writer;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
			
			repaint();
//			if(writer != null) {
//				newValue = true;
//				writer.println(x + "," + y);
//			}
//			System.out.println(xPos + "," + yPos);
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
