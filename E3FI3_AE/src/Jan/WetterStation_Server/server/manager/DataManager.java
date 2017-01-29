package Jan.WetterStation_Server.server.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Jan.WetterStation_Server.Server;
import Jan.WetterStation_Server.server.util.Cfg;
import Jan.WetterStation_Server.server.util.WetterData;

public class DataManager {

	ArrayList<WetterData> data = new ArrayList<WetterData>();

	public DataManager() {
		init();
	}

	public void readAll() {
		for (int i = 0; i < data.size(); i++) {
			debug("Hauptregion:	" + data.get(i).getHauptregion());
			debug("Region:	" + data.get(i).getRegion());
			debug("Temperatur:	" + data.get(i).getTemperatur());
			debug("Status:	" + data.get(i).getStatus());
			debug("Windstärke:	" + data.get(i).getWindstaerke());
			debug("----------");
		}
	}

	private void init() {
		for (int hauptregion = 0; hauptregion < 10; hauptregion++) {
			for (int region = 0; region < 10; region++) {
				WetterData tmp = new WetterData(hauptregion, region, 0, 0, 0);
				data.add(tmp);
			}
		}
	}

	public synchronized void setData(int hauptregion, int region, char typ, int value, double value1) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getHauptregion() == hauptregion && data.get(i).getRegion() == region) {
				switch (typ) {
				case 'T':
					data.get(i).setTemperatur(value1);
					break;
				case 'S':
					data.get(i).setStatus(value);
					break;
				case 'W':
					data.get(i).setWindstaerke(value);
					break;
				}
			}
		}
	}

	private void debug(String string) {
		if (Cfg.isDebug) {
			Server.printLog("[DataManager] " + string);
		}
	}

	public ArrayList<WetterData> getAllData() {
		sort();
		return data;
	}
	private void sort(){
		Collections.sort(data, new Comparator<WetterData>() {
			@Override
			public int compare(WetterData o1, WetterData o2) {
				String a = String.valueOf(o1.getHauptregion());
				String b = String.valueOf(o2.getHauptregion());
				return a.compareTo(b);
			}
		});
	}
}
