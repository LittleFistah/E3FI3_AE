package Jan.gui.pizza;

import java.awt.EventQueue;


public class Main {

	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					Gui gui = new Gui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}