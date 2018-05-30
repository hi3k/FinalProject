import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Point {
	private int x; 
	private int y;
	private String animal;
	private JLabel image; 
	
	Point(int x, int y, String animal, JLabel image){
		this.x = x;
		this.y = y;
		this.animal = animal;
		this.image = image;
	}
	
	public int returnX() {
		return x; 
	}
	
	public int returnY() {
		return y;
	}
	
	public String returnAnimal() {
		return animal;
	}
	
	public JLabel returnImage() {
		return image;
	}
	
	public void setImage(BufferedImage photo, String word) {
		image.setIcon(new ImageIcon(photo));
		animal = word;
	}
 
}
