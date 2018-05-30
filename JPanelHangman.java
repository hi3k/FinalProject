import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JPanelHangman extends JPanel{
	
	private ArrayList<String> list;
	private String[] wordArray;
	private String[] showWord;
	int numGuesses = 9;
	private BufferedImage img1;
	private BufferedImage img2;
	private BufferedImage img3;
	private BufferedImage img4;
	private BufferedImage img5;
	private BufferedImage img6;
	private BufferedImage img7;
	private BufferedImage img8;
	private BufferedImage img9;

	JPanelHangman(){
		Scanner sc = null;
		try {
			sc = new Scanner(new File("HangmanList.txt"));
			img1 = ImageIO.read(new File("1 Hey.png"));
			img2 = ImageIO.read(new File("2 dude.png"));
			img3 = ImageIO.read(new File("3 how.png"));
			img4 = ImageIO.read(new File("4 is.png"));
			img5 = ImageIO.read(new File("5 it.png"));
			img6 = ImageIO.read(new File("6 hanging.png"));
			img7 = ImageIO.read(new File("7 you.png"));
			img8 = ImageIO.read(new File("8 OK.png"));
			img9 = ImageIO.read(new File("9 bro.png"));
		}
		catch (FileNotFoundException e ) {
			e.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	
		list = new ArrayList<String>();
		while (sc.hasNext()) {
			String word = sc.next();
			list.add(word);
		}
			
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		
		this.setLayout(gbl);

		String word = list.get((int)(Math.random() * list.size()));
		String space = "";
		for (int i = 0; i < word.length(); i++) {
			if (i == word.length() - 1) {
				space += "_";
			}
			else {
				space += "_ ";
			}
		}
		 
		
		JLabel main = new JLabel(space);
		JLabel guesses = new JLabel("");
		JTextField textField = new JTextField(10);
		JButton enter = new JButton("Enter");
		JLabel title = new JLabel("HangMan");
		JLabel picture = new JLabel(new ImageIcon(img1));
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		this.add(title, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 1;
		this.add(picture, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(main, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(guesses, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add (textField, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		this.add (enter, gbc);
		
		//Instantiates both arrays and makes showWord an array of blank spaces equal in size to WordArray
		 showWord = new String[word.length()];
		 wordArray = new String[word.length()];
	        for(int i = 0; i < word.length(); i++){
	            showWord[i] = "_ ";
	        }	
	
		
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//starts actionListener if entered guess is a valid character and has not been guessed before
		if(isLetter(textField.getText())&&!guesses.getText().contains(textField.getText()))
		{
			String guess = textField.getText().toLowerCase();
			String temp = "";
			//updates previous guesses label
			guesses.setText(guesses.getText()+ " " + guess);
		
			for(int i = 0; i < wordArray.length; i++)
	        { //turns string into array of chars
	            wordArray[i] = word.substring(i, i+1);
	        }
			//changes guessed letter (if present in the word) from _ to the letter in showWord array 
	        for(int i = 0; i < wordArray.length; i++){ 
	            if(guess.equalsIgnoreCase(wordArray[i])){
	            	 showWord[i] = wordArray[i];
	            }
	        }
	        // copies showWord array into temp String to put into main
	        for(int i = 0; i < showWord.length; i++)
	        {
	        	temp += showWord[i];
	        }
	        //sets main to new version of word
	        main.setText(temp);
	   
	        // if word does not contain guessed letter, decreases numGuesses to move to next image 
	        if(!Arrays.asList(wordArray).contains(guess))
	        {
	            numGuesses--;
	        }
	        // switch case to cycle through the images, when numGuesses reaches 1, lose condition triggers
	        switch (numGuesses) 
	        {
	            case 8:  picture.setIcon(new ImageIcon(img2));
	                break;
	            case 7:  picture.setIcon(new ImageIcon(img3));
	            	break;
	            case 6:  picture.setIcon(new ImageIcon(img4));
        			break;
	            case 5:  picture.setIcon(new ImageIcon(img5));
        			break;
	            case 4:  picture.setIcon(new ImageIcon(img6));
        			break;
	            case 3:  picture.setIcon(new ImageIcon(img7));
        			break;
	            case 2:  picture.setIcon(new ImageIcon(img8));
        			break;
	            case 1:  picture.setIcon(new ImageIcon(img9));
	            JOptionPane.showMessageDialog(null, "You Lose\nThe word was " + word);
	            MainGUI.changeWelcome();
        			break;
	        }
		}
		//pops up if something other than a new, valid character is guessed
		else
		{
			if (isLetter(textField.getText())&& guesses.getText().contains(textField.getText())) {
				JOptionPane.showMessageDialog(null, "You already enter that letter");
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter a valid letter");
			}
		}
		textField.setText("");
		
		//win condition, opens win dialogue
		if(Arrays.equals(showWord, wordArray))
		{
				JOptionPane.showMessageDialog(null, "You Win");
				MainGUI.changeWelcome();
		}
			}
		});
	         
	}
//check if entered guess is a character	
	private static boolean isLetter(String str) {
		try {
			char guess = str.charAt(0);
			if (Character.isLetter(guess)&& str.substring(0,1).equals(str)) {
				return true;
			}
			return false;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}
}
