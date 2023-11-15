package dk.tec.maso;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		try
		(
				Socket ioSocket = new Socket("localhost", 2000)
		)
		{
			InputStream in = ioSocket.getInputStream();
			OutputStream out = ioSocket.getOutputStream();
			
			String str = "Hej fra Client\n";
			byte[] bytes = str.getBytes();
			out.write(bytes);
			
			int ch;
			while((ch = in.read()) != -1)
			{
				System.out.print((char)ch);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
