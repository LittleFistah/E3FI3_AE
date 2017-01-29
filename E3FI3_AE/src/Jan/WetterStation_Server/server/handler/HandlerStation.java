package Jan.WetterStation_Server.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

import Jan.WetterStation_Server.Server;
import Jan.WetterStation_Server.server.util.Cfg;

public class HandlerStation extends Thread {

	private Socket station;
	private int id, updateCount;
	private Date firstContact;

	public HandlerStation(Socket station, int id) {
		this.station = station;
		this.id = id;
		this.firstContact = new Date();
	}

	@Override
	public void run() {
		while (Cfg.isHandlingStation) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(station.getInputStream()));
				String line = br.readLine();
				while (line.compareTo("<e>") != 0) {
					debug(line);
					if(line.contains("<u>")){
						performUpdate(line.substring(3));
						updateCount++;
					}
					line = br.readLine();
				}
				Server.rejectStation(id);
				
			} catch (IOException e) {
				if (e.getMessage().equals("Connection reset")) {
					Server.rejectStation(id);
					break;
				} else {
					e.printStackTrace();
				}
			}
		}
	}
	private void performUpdate(String u){
		debug(u);
		String[] subs = u.split(":");
		int hr = 0;
		int r = 0;
		try{
			hr = Integer.valueOf(subs[0].substring(0, 1));
			r = Integer.valueOf(subs[0].substring(1));
		}catch(Exception e){
			if(!e.getMessage().contains("For input string:")){
				e.printStackTrace();
			}else{
				r = Integer.valueOf(subs[0].substring(0));
				hr = 0;
			}
		}
		char typ = subs[1].toCharArray()[0];
		int value = 0;
		double value1 = 0;
		if(typ == 'T'){
			value1 = Double.valueOf(subs[2]);
		}else{
			value = Integer.valueOf(subs[2]);
		}
		debug(""+hr+" "+r+" "+typ+" "+value+" "+value1);
		boolean isUpdated = false;
		while(!isUpdated){
			if(Server.requestPower()){
				Server.setData(hr, r, typ, value, value1);
				Server.refusePower();
				isUpdated = true;
			}
		}
	}

	private void debug(String string) {
		if (Cfg.isDebugHandlerStation) {
			Server.printLog("[HandlerStation:" + id + "]" + string);
		}
	}

	public int getMyId() {
		return id;
	}
	public int getUpdateCount(){
		return updateCount;
	}
	public Date getFirstContact() {
		return firstContact;
	}
}
