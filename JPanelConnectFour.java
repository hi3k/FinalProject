import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JPanelConnectFour extends JPanel{
	private BufferedImage cat;
	private BufferedImage dog; 
	private BufferedImage blank;
	private boolean playercat = true; 
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagLayout gbl = new GridBagLayout();
	private Point[][] points = new Point[6][7];
	private JLabel player;
	private BufferedImage catend; 
	private BufferedImage dogend;
	
	JPanelConnectFour(){
		this.setLayout(gbl);
			
		try {
			cat = ImageIO.read(new File("catsmolsmol.png"));
			dog = ImageIO.read(new File("dogsmolsmol.png"));
			blank = ImageIO.read(new File("nothingsmol.png"));
			catend = ImageIO.read(new File("catsmolsmolnya.png"));
			dogend = ImageIO.read(new File("dogsmolsmolblep.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
			
		JLabel title = new JLabel("Connect Four");
		JButton col1 = new JButton();
		JButton col2 = new JButton();
		JButton col3 = new JButton();
		JButton col4 = new JButton();
		JButton col5 = new JButton();
		JButton col6 = new JButton();
		JButton col7 = new JButton();
		player = new JLabel("Player Cat");
			
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		this.add(title, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(col1, gbc);
		gbc.gridx = 1; 
		this.add(col2, gbc);
		gbc.gridx = 2; 
		this.add(col3, gbc);
		gbc.gridx = 3; 
		this.add(col4, gbc);
		gbc.gridx = 4;
		this.add(col5, gbc);
		gbc.gridx = 5;
		this.add(col6, gbc);
		gbc.gridx = 6; 
		this.add(col7, gbc);
			
		gbc.insets = new Insets(0,0,0,0);
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[0].length; j++) {
				gbc.gridx = j; 
				gbc.gridy = i + 2; 
				points[i][j] = new Point(i,j,"blank", new JLabel(new ImageIcon(blank)));
				this.add(points[i][j].returnImage(), gbc);
			}
		}
			
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 7;
		this.add(player, gbc);
			
		gbc.insets = new Insets(0,0,0,0);
		
		col1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playingGame(1);
			}
				
		});
		
		col2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playingGame(2);
			}
			
		});
		
		col3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playingGame(3);
			}
		
		});
		
		col4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playingGame(4);
			}
			
		});
		
		col5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playingGame(5);
			}
			
		});
		
		col6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playingGame(6);
			}
			
		});
		
		col7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playingGame(7);
			}
			
		});
			
	}
		
	public void playingGame(int col) {
		int row = -1; 
		for (int i = 0; i < points.length; i++) {
			if (points[i][col-1].returnAnimal().equals("blank")) {
				row = i; 
			}
		}
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "You can't place a piece here");
		}
		
		else if (playercat) {
			player.setText("Player Dog");
			points[row][col-1].setImage(cat, "cat");
			repaint();
			playercat = false; 
		}
		else {
			player.setText("Player Cat");
			points[row][col-1].setImage(dog, "dog");
			repaint();
			playercat = true;
		}
		if (checkRow()||checkCol()||checkDiaLeft()||checkDiaRight()) {
			repaint();
			if (playercat) {
				player.setText("Player Dog wins");
				JOptionPane.showMessageDialog(null, "Player Dog win");
				MainGUI.changeWelcome();
			}
			else {
				player.setText("Player Cat wins");
				JOptionPane.showMessageDialog(null, "Player Cat win");
				MainGUI.changeWelcome();
			}
		}
	}
	
	public boolean checkRow() {
		for(int i = 0; i < points.length; i++) {
			for (int j = 0; j <= 3; j++) {
				if (points[i][j].returnAnimal().equals("cat")) {
					if (points[i][j+1].returnAnimal().equals("cat") &&
							points[i][j+2].returnAnimal().equals("cat") &&
							points[i][j+3].returnAnimal().equals("cat")) {
						points[i][j].setImage(catend, "catend");
						points[i][j+1].setImage(catend, "catend");
						points[i][j+2].setImage(catend, "catend");
						points[i][j+3].setImage(catend, "catend");
						return true;
					}
				}
				if (points[i][j].returnAnimal().equals("dog")) {
					if (points[i][j+1].returnAnimal().equals("dog") &&
							points[i][j+2].returnAnimal().equals("dog")&&
							points[i][j+3].returnAnimal().equals("dog")){
						points[i][j].setImage(dogend, "dogend");
						points[i][j+1].setImage(dogend, "dogend");
						points[i][j+2].setImage(dogend, "dogend");
						points[i][j+3].setImage(dogend, "dogend");
						return true;
					}
					
				}
			}
		}
		return false;
	}
	
	public boolean checkCol() {
		for(int i = 0; i <= 2; i++) {
			for (int j = 0; j < points[0].length; j++) {
				if (points[i][j].returnAnimal().equals("cat")) {
					if (points[i+1][j].returnAnimal().equals("cat") &&
							points[i+2][j].returnAnimal().equals("cat") &&
							points[i+3][j].returnAnimal().equals("cat")) {
						points[i][j].setImage(catend, "catend");
						points[i+1][j].setImage(catend, "catend");
						points[i+2][j].setImage(catend, "catend");
						points[i+3][j].setImage(catend, "catend");
						return true;
					}
				}
				if (points[i][j].returnAnimal().equals("dog")) {
					if (points[i+1][j].returnAnimal().equals("dog") &&
							points[i+2][j].returnAnimal().equals("dog")&&
							points[i+3][j].returnAnimal().equals("dog")){
						points[i][j].setImage(dogend, "dogend");
						points[i+1][j].setImage(dogend, "dogend");
						points[i+2][j].setImage(dogend, "dogend");
						points[i+3][j].setImage(dogend, "dogend");
						return true;
				}
			}
		}
		
		}
		return false;
	}
	
	public boolean checkDiaLeft() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 3; j++) {
				if (points[i][j].returnAnimal().equals("cat")) {
					if (points[i+1][j+1].returnAnimal().equals("cat") &&
							points[i+2][j+2].returnAnimal().equals("cat") &&
							points[i+3][j+3].returnAnimal().equals("cat")) {
						points[i][j].setImage(catend, "catend");
						points[i+1][j+1].setImage(catend, "catend");
						points[i+2][j+2].setImage(catend, "catend");
						points[i+3][j+3].setImage(catend, "catend");
						return true;
					}
				}
				if (points[i][j].returnAnimal().equals("dog")) {
					if (points[i+1][j+1].returnAnimal().equals("dog") &&
							points[i+2][j+2].returnAnimal().equals("dog")&&
							points[i+3][j+3].returnAnimal().equals("dog")){
						points[i][j].setImage(dogend, "dogend");
						points[i+1][j+1].setImage(dogend, "dogend");
						points[i+2][j+2].setImage(dogend, "dogend");
						points[i+3][j+3].setImage(dogend, "dogend");
						return true;
				}
			}
			}
		}
		return false;
	}
	
	public boolean checkDiaRight() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 3; j < points[0].length; j++) {
				{
					if (points[i][j].returnAnimal().equals("cat")) {
						if (points[i+1][j-1].returnAnimal().equals("cat") &&
								points[i+2][j-2].returnAnimal().equals("cat") &&
								points[i+3][j-3].returnAnimal().equals("cat")) {
							points[i][j].setImage(catend, "catend");
							points[i+1][j-1].setImage(catend, "catend");
							points[i+2][j-2].setImage(catend, "catend");
							points[i+3][j-3].setImage(catend, "catend");
							return true;
						}
					}
					if (points[i][j].returnAnimal().equals("dog")) {
						if (points[i+1][j-1].returnAnimal().equals("dog") &&
								points[i+2][j-2].returnAnimal().equals("dog")&&
								points[i+3][j-3].returnAnimal().equals("dog")){
							points[i][j].setImage(dogend, "dogend");
							points[i+1][j-1].setImage(dogend, "dogend");
							points[i+2][j-2].setImage(dogend, "dogend");
							points[i+3][j-3].setImage(dogend, "dogend");
							return true;
					}
				}
			}
		}
	}
		return false;
}
}
