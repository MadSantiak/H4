package dk.tec.maso;

public class ThreadSyncObject {
	int c1 = 0;
	int c2 = 0;
	
	Object l1 = new Object();
	Object l2 = new Object();
	
	void incrementC1()
	{
		synchronized(l1) {
			c1++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void incrementC2()
	{
		synchronized(l2) {
			c2++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final ThreadSyncObject app = new ThreadSyncObject();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					app.incrementC1();
					app.incrementC2();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					app.incrementC1();
					app.incrementC2();
				}
			}
		});
		
		long start = System.currentTimeMillis();
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("C1: " + app.c1 + " C2: " + app.c2 + "\tTime: " + (end-start));
	}
}
