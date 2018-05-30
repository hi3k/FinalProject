import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGUI extends JFrame{
	private static JPanelWelcome welcome;
	private static JPanelHangman hangman;
	private static CardLayout cl;
	private static JPanel overall;
	private static JPanelConnectFour connectfour;
	private JPanelTest test;
	MainGUI(){
		cl = new CardLayout();
		overall = new JPanel();
		overall.setLayout(cl);
		
		welcome = new JPanelWelcome();
		hangman = new JPanelHangman();
		connectfour = new JPanelConnectFour();
		test = new JPanelTest();
		overall.add(welcome, "welcome");
		overall.add(hangman, "hangman");
		overall.add(connectfour, "connectfour");
		overall.add(test, "test");
		
		cl.show(overall, "welcome");
		
		this.add(overall);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Games!!!");
		this.setVisible(true);
	}
	
	public static void changeHangman() {
		overall.remove(hangman);
		hangman = new JPanelHangman();
		overall.add(hangman, "hangman");
		cl.show(overall, "hangman");
	}
	
	public static void changeConnectFour() {
		overall.remove(connectfour);
		connectfour = new JPanelConnectFour();
		overall.add(connectfour, "connectfour");
		cl.show(overall, "connectfour");
	}
	
	public static void changeTest() {
		cl.show(overall, "test");
	}
	
	public static void changeWelcome() {
		cl.show(overall, "welcome");
	}
	
	public static void main(String[] args) {
		MainGUI ja = new MainGUI();
	}
}
