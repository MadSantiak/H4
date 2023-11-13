package dk.tec.maso;

public class MyThread extends Thread 
{
	public MyThread(String string) {
		setName(string);
	}
	
	@Override
	public void run()
	{
		for(int i = 0; i < 5; i++)
		{
			System.out.println(Thread.currentThread().getName() + ": " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
