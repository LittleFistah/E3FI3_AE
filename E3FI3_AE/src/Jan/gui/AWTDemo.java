package Jan.gui;

import java.awt.Button;
import java.awt.Frame;

public class AWTDemo {
	public static void main(String[] args) {
		// Nimmt Swing nicht AWT... AWT suxxs
		Frame fenster = new Frame("Testfenster");
		fenster.setSize(300, 200);
		
		
		Button btn = new Button("Lolo");
		
		fenster.add(btn);
		fenster.setVisible(true);
		
		System.out.println("Ende!");
	}
}
