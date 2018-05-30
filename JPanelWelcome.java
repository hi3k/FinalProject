import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelWelcome extends JPanel{
	
	JPanelWelcome(){
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		
		this.setLayout(gbl);
		
		JLabel l1 = new JLabel("Welcome");
		JButton b1 = new JButton("Hangman");
		JButton b2 = new JButton("Connect Four");
		//JButton b3 = new JButton("test");
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(l1, gbc);
		gbc.gridx = 0; 
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(b1, gbc);
		gbc.gridx = 1; 
		this.add(b2, gbc);
		//gbc.gridy = 2;
		//this.add(b3, gbc);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainGUI.changeHangman();
			}
			
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.changeConnectFour();
				
			}
			
		});
		
		/*b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainGUI.changeTest();
			}
			
		});
		*/
	}
	
}
