package dk.tec.maso;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Input extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblPrompt;
	private JTextField txtInput;
	private JButton btnOK, btnCANCEL;
	private String text;
	public Input(String prompt)
	{
	setModal(true);
	setTitle("Input Text");
	setSize(500, 150);
	setLocationRelativeTo(null);

	lblPrompt = new JLabel(prompt);
	txtInput = new JTextField(40);
	btnOK = new JButton("OK");
	btnCANCEL = new JButton("CANCEL");
	
	btnOK.addActionListener(new ActionListener()
		{
		@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Input.this.text = txtInput.getText();
			}
		});
	
	btnCANCEL.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Input.this.setVisible(false);
		}
	});
	
	setLayout(new FlowLayout());
	add(lblPrompt);
	add(txtInput);
	add(btnOK);
	add(btnCANCEL);
	}
	public String getInputText()
	{	
		return this.text;
	}
}
