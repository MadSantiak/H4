package dk.tec.maso;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable {
	private BufferedReader reader;
	
	public ServerListener(BufferedReader reader) {
		this.reader = reader;
	}
	
	public void run() {
		try {
			String srvMsg;
			while ((srvMsg = reader.readLine()) != null) {
				System.out.println(srvMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
