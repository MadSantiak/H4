package dk.tec.maso;

import java.util.Scanner;

public class Main {
	Data data = new Data();;
	Scanner scanner = new Scanner(System.in);
	Object nameLock = new Object();
	Object countLock = new Object();;
	
	void nameOperation() {
		synchronized(nameLock) {
			while (!data.stop) {
				System.out.println("Enter name: ");
				String strName = scanner.nextLine();
				data.name = strName;
				
				if (strName.toLowerCase().equals("jan"))
				{
					data.stop = true;
				}
				else {
					System.out.println("Continue? [Y/N]:");
					String strResp = scanner.nextLine();
					if (strResp.toLowerCase().equals("n"))
					{
						data.stop = true;
					}
				}
				
			}
		}
	}
	
	void countOperation() {
		synchronized(countLock) {
			while (!data.stop) {
				if (data.name != null) {
					data.counter++;
					data.name = null;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		final Main main = new Main();
		Thread nameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				main.nameOperation();
			}
		}, "Name Thread");
		Thread countThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				main.countOperation();
			}
		}, "Count Thread");
		
		nameThread.start();
		countThread.start();
		
		try {
			nameThread.join();
			countThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Total names typed: " + main.data.counter);
	}
}
