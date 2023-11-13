package dk.tec.maso;

public class Worker extends Thread {
	private volatile boolean stop = false;
	
	@Override
	public void run() {
		int count = 0;
		while (!stop) {
			System.out.println("Count: " + count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopMe() {
		stop = true;
	}
}
