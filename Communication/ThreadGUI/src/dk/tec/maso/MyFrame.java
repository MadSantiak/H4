package dk.tec.maso;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class MyFrame extends JFrame {
	private JLabel lblCount = new JLabel("0");
	private JLabel lblStatus = new JLabel("Task not completed");
	private JButton btnStart = new JButton("Start");

	public MyFrame(String title) {
		super(title);
		Font font = new Font(lblStatus.getFont().getName(), Font.PLAIN, lblStatus.getFont().getSize() * 2);
		lblStatus.setFont(font);
		lblCount.setFont(font);
		btnStart.setFont(font);
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(lblCount, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(lblStatus, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(btnStart, gc);
		
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		setBounds(700, 200, 300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	protected void start() {
		SwingWorker<String, Integer> worker = new SwingWorker<String, Integer>() {

			@Override
			protected String doInBackground() throws Exception {
				for (int i = 1; i <= 30; i++) {
					Thread.sleep(100);
					System.out.println("Hello " + i);
					publish(i);
				}
				return "Now we finished!";
			}

			@Override
			protected void process(List<Integer> chunks) {
				Integer value = chunks.get(chunks.size() - 1);
				lblCount.setText("Counting " + value);
			}

			@Override
			protected void done() {
				try {
					lblStatus.setText("Completed with status: " + get().toString());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		// Start det hele
		worker.execute();
	}
}
