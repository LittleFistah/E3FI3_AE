package Jan.map_bsp;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Gui gui = new Gui();
			}
		});
	}

}
