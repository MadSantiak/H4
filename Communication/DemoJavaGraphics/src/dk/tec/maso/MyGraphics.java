package dk.tec.maso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MyGraphics extends JFrame implements MouseListener, MouseMotionListener {
	
	int xPos = 100;
	int yPos = 100;
	int xPrev, yPrev;
	int size = 50;
	boolean dragging;
	
	public MyGraphics() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLUE);
		g.fillRect(xPos, yPos, size, size);
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
