package dk.tec.maso.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SocketClient extends JFrame {
	JLabel lblSend, lblReceived, lblUsername, lblWarning;
	JTextField txtSend, txtUsername;
	JTextArea txtReceived;
	JButton btnSend, btnExit;
	Socket sock;
	BufferedReader in;
	PrintWriter out;

	private static SocketClient client;

	public static void main(String[] args) {
		client = new SocketClient();
		client.setBounds(200, 200, 800, 600);
		client.setDefaultCloseOperation(EXIT_ON_CLOSE);
		client.setVisible(true);
	}

	public SocketClient() {
		lblSend = new JLabel("Send");
		lblReceived = new JLabel("Received");
		lblUsername = new JLabel("Username");
		lblWarning = new JLabel("Please enter a username!");
		txtSend = new JTextField(20);
		txtReceived = new JTextArea(50, 50);
		txtUsername = new JTextField(20);
		btnSend = new JButton("Send");
		btnExit = new JButton("Exit");

		JPanel pnl0 = new JPanel();
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();

		pnl0.add(lblUsername);
		pnl0.add(txtUsername);
		pnl1.add(lblSend);
		pnl1.add(txtSend);
		pnl2.add(lblReceived);
		pnl2.add(txtReceived);
		pnl3.add(btnSend);
		pnl3.add(btnExit);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(pnl0);
		add(pnl1);
		add(pnl2);
		add(pnl3);

		try {
			sock = new Socket("localhost", 2000);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			Thread t1 = new Thread(new ServerListener(in, this));
			t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (txtUsername.getText().equals("")) {
					pnl0.add(lblWarning);
				} else {
					pnl0.remove(lblWarning);
					if (!txtSend.getText().equals("")) {
						

							String username = txtUsername.getText();
							String msg = txtSend.getText();
							out.println(username + ": " + msg);
							txtSend.setText("");

					}
				}
				pnl0.revalidate();
				pnl0.repaint();
			}
		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					client.setVisible(false);
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		});
	}
}