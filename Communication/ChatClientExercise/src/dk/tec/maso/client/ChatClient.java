package dk.tec.maso.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import dk.tec.maso.server.ChatServer;

public class ChatClient extends JFrame {
	JLabel lblSend, lblReceived, lblUsername, lblUsers;
	JTextField txtSend, txtUsername;
	JTextArea txtReceived;
	JButton btnSend, btnExit, btnConnect;

	Socket sock;
	BufferedReader in;
	PrintWriter out;
	boolean connected;
	
	static ChatClient client;

	public static void main(String[] args) {
		client = new ChatClient();
		client.setTitle("Chat client");
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setBounds(200, 200, 550, 450);
		client.setVisible(true);
	}

	public ChatClient() {
		lblSend = new JLabel("Send");
		lblReceived = new JLabel("Received");
		lblUsername = new JLabel("Username");
		lblUsers = new JLabel("Users: 0");
		txtSend = new JTextField(20);
		txtSend.setMaximumSize(txtSend.getPreferredSize());// At den ikke fylder hele højden
		txtReceived = new JTextArea(20, 20);
		txtReceived.setLineWrap(true);
		txtUsername = new JTextField(20);
		txtUsername.setMaximumSize(txtUsername.getPreferredSize());// At den ikke fylder hele højden

		btnConnect = new JButton("Connect");
		btnSend = new JButton("Send");
		btnExit = new JButton("Exit");
		btnExit.setPreferredSize(btnSend.getPreferredSize());

		JScrollPane sp = new JScrollPane(txtReceived);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new GridBagLayout());
		GridBagConstraints gs = new GridBagConstraints();
		gs.insets = new Insets(20, 20, 20, 20);
		// Indsætter margin omkring cellen, hvilket påvirker hele rækken og kolonnen.
		// Når den bruges på alle bliver det pænt.
		gs.gridx = 1;
		gs.gridy = 1;
		pnl1.add(lblSend, gs);
		gs.gridx = 1;
		gs.gridy = 2;
		gs.gridwidth = 3;
		pnl1.add(txtSend, gs);
		gs.gridwidth = 1;
		gs.gridx = 1;
		gs.gridy = 3;
		pnl1.add(btnSend, gs);
		gs.gridx = 1;
		gs.gridy = 4;
		pnl1.add(btnExit, gs);

		gs.gridx = 1;
		gs.gridy = 6;
		gs.insets = new Insets(10, 10, 10, 10);
		pnl1.add(lblUsername, gs);
		gs.gridx = 1;
		gs.gridy = 7;
		pnl1.add(txtUsername, gs);
		gs.gridx = 1;
		gs.gridy = 8;
		pnl1.add(btnConnect, gs);

		JPanel pnl2 = new JPanel();

		pnl2.setLayout(new GridBagLayout());
		gs.gridx = 1;
		gs.gridy = 2;
		gs.insets = new Insets(10, 30, 10, 10);
		pnl2.add(lblReceived, gs);
		gs.gridx = 1;
		gs.gridy = 3;
		gs.gridwidth = 2;
		gs.gridheight = 15;
		gs.insets = new Insets(10, 10, 10, 10);
		pnl2.add(sp, gs);
		
		gs.gridx = 1;
		gs.gridy = 1;
		pnl2.add(lblUsers, gs);

		JLabel lblWarning = new JLabel("Please enter username!");
		gs.gridx = 1;
		gs.gridy = 9;
		pnl1.add(lblWarning, gs);
		lblWarning.setVisible(false);

		JLabel lblConnected = new JLabel("CONNECTED");
		gs.gridx = 1;
		gs.gridy = 9;
		pnl1.add(lblConnected, gs);
		lblConnected.setVisible(false);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		add(pnl1);
		add(pnl2);

		try {
			sock = new Socket("localhost", 2000);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!txtUsername.getText().equals(""))

				{
					String username = txtUsername.getText();
					out.println(username);
					out.println("joined the chat");
					connected = true;
					lblWarning.setVisible(false);
					lblConnected.setVisible(true);
				} else {
					lblWarning.setVisible(true);

				}
			}
		});

		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(connected);
				if (connected) {
					System.out.println("Sending..");
					String msg = txtSend.getText();
					out.println(msg); // + "\n"); // med den kommer der en ekstra tom linie

				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (sock != null && !sock.isClosed() ) {
						out.println("left the chat");
						sock.close();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					dispose();
					System.exit(0);
				}
			}
			
		});
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				String message;
				while (true) {
					try {
						message = in.readLine();
						if (message.startsWith("USERS ")) {
							String[] splits = message.split(" ");
							if (splits.length == 2) {
								lblUsers.setText("Users: " + splits[1]);
							}
						} else {
							System.out.println(message);
							txtReceived.setText(txtReceived.getText() + message + "\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
}