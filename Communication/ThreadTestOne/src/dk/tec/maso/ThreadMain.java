package dk.tec.maso;

import java.util.Random;
import java.util.Scanner;

public class ThreadMain {

	public static void main(String[] args) {
		Thread.currentThread().setName("Main-thread");
		System.out.println(Thread.currentThread().getName() + ": Started\n");
		
		// We name the threads so it's easier to debug later, rather than 
		// the bog-standard "Thread-X" naming convention, which
		// tells us nothing of the Threads function/purpose.
		MyThread t0 = new MyThread("Zero-Thread");
		
		Thread t1 = new Thread(new MyRunnable());
		Thread t2 = new Thread(new MyRunnable(), "Thread-two");
		Thread t3 = new Thread(new Runnable() {
			
			Random r = new Random();
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + ": " + i);
					try {
						Thread.sleep(r.nextInt(200));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Anon-Thread");
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		
		Worker w = new Worker();
		w.start();
		System.out.println("Press any key to stop!");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		
		w.stopMe();
		
		System.out.println("\n" + Thread.currentThread().getName() + ": Ended");		
	}

}
