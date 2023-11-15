package dk.tec.maso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	public static void main(String[] args) {
		// Initiate the Thread Pool ("executor").
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// We submit functions (Processor) to be executed by the Threads in the thread pool
		for(int i = 0; i < 5; i++)
		{
			executor.submit(new Processor(i));
		}
		// We shut down the Pool; submitted tasks are executed while no new tasks are accepted.
		executor.shutdown();
		System.out.println("All tasks submitted.");
		try {
			// Wait for the execution of the functions to complete after shutdown().
			executor.awaitTermination(1, TimeUnit.DAYS); // Wait 1 day.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks completed.");

	}

}
