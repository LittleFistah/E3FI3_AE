package Jan.WetterStation_Server.server.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Jan.WetterStation_Server.Server;
import Jan.WetterStation_Server.server.util.Cfg;

public class ListenerClient extends Thread{

	ServerSocket server;
	
	public ListenerClient() {
		try {
			server = new ServerSocket(0);
			server.setSoTimeout(Cfg.defaultTimeout);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		debug("ClientListener is Running ...");
		Socket tmp;
		while(Server.isRunning()){
			try {
				tmp = server.accept();
				if (tmp.getInputStream().read() != -1) {
					debug("Neuer Client angemeldet ...");
					reportClient(tmp);
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
	private void reportClient(Socket client){
		Server.acceptClient(client);
	}
	private void debug(String x){
		if (Cfg.isDebug) {
			Server.printLog("[ListenerClient] "+x);
		}
	}
}
