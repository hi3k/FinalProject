import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelTest extends JPanel{
	private BufferedImage cat = null;
	private BufferedImage dog = null;
	JPanelTest(){
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		
		this.setLayout(gbl);
		
		try {
			cat = ImageIO.read(new File("catsmol.png"));
			dog = ImageIO.read(new File("dogsmol.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage newcat = this.resizeImage(cat, 8, 400, 400);
		JLabel catlbl = new JLabel(new ImageIcon(newcat));
		JLabel doglbl = new JLabel(new ImageIcon(dog));
		
		gbc.insets = new Insets(10,10,10,10);
		this.add(catlbl, gbc);
		gbc.gridx = 1;
		this.add(doglbl, gbc);
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type,
            int img_width, int img_height)
	{
		BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, img_width, img_height, null);
		g.dispose();

		return resizedImage;
}
}
