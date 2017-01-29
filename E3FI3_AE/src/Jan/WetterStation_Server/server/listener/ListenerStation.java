package Jan.WetterStation_Server.server.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Jan.WetterStation_Server.Server;
import Jan.WetterStation_Server.server.util.Cfg;

public class ListenerStation extends Thread{

	ServerSocket server;
	
	public ListenerStation() {
		try {
			server = new ServerSocket(5555);
			server.setSoTimeout(Cfg.defaultTimeout);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		debug("StationListener is Running ...");
		Socket tmp;
		while(Server.isRunning()){
			try {
				tmp = server.accept();
				if (tmp.isConnected()) {
					debug("Neue Station angemeldet ...");
					reportStation(tmp);
				}
			} catch (IOException e) {
				if(!e.getMessage().equals("Accept timed out")){
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getPort(){
		return server.getLocalPort();
	}
	private void reportStation(Socket station){
		Server.acceptStation(station);
	}
	private void debug(String x){
		if (Cfg.isDebug) {
			Server.printLog("[ListenerStation] "+x);
		}
	}
}
