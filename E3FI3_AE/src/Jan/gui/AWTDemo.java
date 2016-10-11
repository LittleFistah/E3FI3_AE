package Jan.gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Aua, das tat weh!");
	}
	
}
public class AWTDemo {
	public static void main(String[] args) {
		// Nimmt Swing nicht AWT... AWT suxxs
		
		MyListener myL = new MyListener();
		
		
		Frame fenster = new Frame("Testfenster");
		fenster.setSize(300, 200);
		
		
		Button btn = new Button("Lololo");
		btn.addActionListener(myL);
		
		fenster.add(btn);
		fenster.setVisible(true);
		
		System.out.println("Ende!");
	}
}
