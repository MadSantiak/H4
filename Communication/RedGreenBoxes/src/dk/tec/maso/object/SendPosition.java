package dk.tec.maso.object;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SendPosition implements Runnable {
	private RectangleObject data;
	private ArrayList<PrintWriter> writers;
	
	public SendPosition(ArrayList<PrintWriter> writers, RectangleObject data) {
		this.data = data;
		this.writers = writers;
	}
	
	@Override
	public void run() {
		PrintWriter out = null;
		
		while (true) {
			if (data.isNewValue()) {
				int y = data.getEnemyY();
				int x = data.getEnemyX();
				String msg = x + "," + y;
				for (PrintWriter w : writers) 
				{
					System.out.println("SENDPOSITION: "+msg);
					w.println(msg);
				}
			}
			data.setNewValue(false);
		}
	}
	
}
