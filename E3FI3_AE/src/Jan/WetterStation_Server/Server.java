package Jan.WetterStation_Server;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Jan.WetterStation_Server.server.UI.ServerUI;
import Jan.WetterStation_Server.server.handler.HandlerStation;
import Jan.WetterStation_Server.server.listener.ListenerClient;
import Jan.WetterStation_Server.server.listener.ListenerStation;
import Jan.WetterStation_Server.server.manager.DataManager;
import Jan.WetterStation_Server.server.util.Cfg;
import Jan.WetterStation_Server.server.util.WetterData;

public class Server {

	private String ipServer;
	private static int cntStation, cntClient, portStation, portClient, talkPower;
	private static boolean isRunning = true;
	private static ArrayList<HandlerStation> handlers = new ArrayList<HandlerStation>();
	private static DataManager dm = new DataManager();
	private static ServerUI ui = new ServerUI();

	public Server() {
		init();
	}

	private void init() {
		cntClient = 0;
		cntStation = 0;
		talkPower = 1;
		try {
			ipServer = InetAddress.getLocalHost().getHostAddress();
			ListenerClient lc = new ListenerClient();
			portClient = lc.getPort();
			ListenerStation ls = new ListenerStation();
			portStation = ls.getPort();
			debug("IP des Servers: " + ipServer);
			debug("Port für Clients: " + portClient);
			debug("Port für Stationen: " + portStation);
			lc.start();
			ls.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static  boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		Server.isRunning = isRunning;
	}

	public static void acceptClient(Socket client) {
		debug("ClientReport incoming.");
		cntClient++;
	}

	public static void acceptStation(Socket station) {
		debug("StationReport incoming.");
		cntStation++;
		HandlerStation handler = new HandlerStation(station, cntStation);
		handler.start();
		handlers.add(handler);
	}

	private static void debug(String string) {
		if (Cfg.isDebug) {
			printLog("[Server] " + string);
		}
	}

	public static synchronized void rejectStation(int id) {
		for (int i = 0; i < handlers.size(); i++) {
			if (handlers.get(i).getMyId() == id) {
				handlers.get(i).interrupt();
				handlers.remove(i);
				debug("HandlerStation:" + id + " beendet.");
			}
		}
	}

	public synchronized static void setData(int hauptregion, int region, char typ, int value, double value1) {
		dm.setData(hauptregion, region, typ, value, value1);
	}
	
	public synchronized static ArrayList<WetterData> getAllData(){
		return dm.getAllData();
	}

	public synchronized static boolean requestPower() {
		if (talkPower == 1) {
			talkPower--;
			return true;
		} else
			return false;
	}

	public synchronized static void refusePower() {
		talkPower++;
	}
	public static void printLog(String s){
		ui.writeLog(s+"\n");
	}
	public static ArrayList<HandlerStation> getHandler(){
		return handlers;
	}
}
