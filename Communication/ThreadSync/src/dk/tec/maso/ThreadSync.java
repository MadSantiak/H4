package dk.tec.maso;

public class ThreadSync {
	static int count = 0;
	
	// Note the "synchronized" keyword makes each function "lock" on the object
	// (in this case, "this"), preventing both functions from being executed at the same time.
	static synchronized void increment()
	{
		count++;
	}
	
	static synchronized void decrement()
	{
		count--;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					increment();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					decrement();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count: " + count);
	}
}
