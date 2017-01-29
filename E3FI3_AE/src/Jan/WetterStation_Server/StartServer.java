package Jan.WetterStation_Server;

import java.awt.EventQueue;

public class StartServer {	

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Server server = new Server();
			}
		});
	}
}
