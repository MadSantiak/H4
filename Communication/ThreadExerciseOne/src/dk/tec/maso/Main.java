package dk.tec.maso;

public class Main {

	public static void main(String[] args) {
		
		Thread slow = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": " + i);
				}
			}
		}, "Slow-count-Thread");
		
		Thread medium = new Thread(new Runnable() {
			
			String[] names = new String[] {"Mads", "Simone", "Ginko", "Jytte", "Lars", "Orla", "Lise", "Sus", "Sassi", "Michael"};
			
			@Override
			public void run() {
				for (int i = 0; i < names.length; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": " + names[i]);
				}
			}
		}, "Medium-name-Thread");
		
		Thread input = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Input input = new Input("Please enter your name");
				input.setVisible(true);
				System.out.println("Name: " + input.getInputText());
			}
		});
		
		slow.start();
		medium.start();
		input.start();
		
		try {
			input.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nEnd of program...");
	
	}

}
