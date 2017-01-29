package Jan.WetterStation_Server.server.UI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Jan.WetterStation_Server.Server;
import Jan.WetterStation_Server.server.handler.HandlerStation;
import Jan.WetterStation_Server.server.util.WetterData;

public class ServerUI extends JFrame {

	private ArrayList<WetterData> dataWetterData;
	private ArrayList<HandlerStation> handlerStation;

	private JTabbedPane tp;

	private JPanel dataPanel, logPanel, clientPanel, stationPanel, cfgPanel;

	private JTable dataTable;
	private DefaultTableModel dataTableModel = null;
	private JScrollPane dataTblScroll, logScroll;
	private final String[] dataHeader = { "Hauptregion", "Region", "Temperatur", "Status", "Windstärke" };
	
	private JTable stationTable;
	private DefaultTableModel stationTableModel = null;
	private JScrollPane stationTblScroll;
	private final String[] stationHeader = { "Station ID","Updates","Anmeldung"};

	private JTextArea logText;

	private boolean isDataUpd = true;
	private boolean isStationUpd = true;

	private int updIntervall = 5000;
	private Thread dataTbl = new Thread() {
		public void run() {
			while (isDataUpd) {
				dataWetterData = Server.getAllData();
				dataTableModel.setRowCount(0);
				for (WetterData x : dataWetterData) {
					Object[] dataObj = { x.getHauptregion() + "", x.getRegion() + "", x.getTemperatur() + "",
							x.getStatus() + "", x.getWindstaerke() + "" };
					dataTableModel.addRow(dataObj);
				}
				try {
					sleep(updIntervall);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	};
	private Thread stationTbl = new Thread(){
		public void run() {
			while(isStationUpd){
				handlerStation = Server.getHandler();
				stationTableModel.setRowCount(0);
				for(HandlerStation x : handlerStation){
					Object[] stationObj = {x.getMyId()+"",x.getUpdateCount()+"",x.getFirstContact()+""};
					stationTableModel.addRow(stationObj);
				}
				try {
					sleep(updIntervall);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	};

	public ServerUI() {
		init();
	}

	private void init() {

		tp = new JTabbedPane();
		// WETTERDATEN
		dataPanel = new JPanel();

		dataTableModel = new DefaultTableModel(dataHeader, 0);
		dataTable = new JTable(dataTableModel);

		dataTblScroll = new JScrollPane(dataTable);

		dataPanel.add(dataTblScroll);

		tp.add("WetterDaten", dataPanel);

		// LOG
		logPanel = new JPanel();

		logText = new JTextArea();
		logText.setColumns(45);
		logText.setRows(30);
		logText.setEditable(false);

		logScroll = new JScrollPane(logText);

		logPanel.add(logScroll);

		tp.add("Log", logPanel);

		// STATIONEN
		stationPanel = new JPanel();
		
		stationTableModel = new DefaultTableModel(stationHeader, 0);
		stationTable = new JTable(stationTableModel);
		
		stationTblScroll = new JScrollPane(stationTable);
		
		stationPanel.add(stationTblScroll);
		
		tp.add("Stationen", stationPanel);

		super.add(tp);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);

		dataTbl.start();
		stationTbl.start();
	}

	public void writeLog(String s) {
		logText.append(s);
	}
}
