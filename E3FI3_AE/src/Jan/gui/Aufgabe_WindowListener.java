package Jan.gui;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class myWindowListener implements WindowListener{
	
	protected int activations = 0;
	protected Label lbl = new Label(0+"");
	
	@Override
	public void windowActivated(WindowEvent e) {
		activations++;
		System.out.println("Aktivierungen: "+activations);
		lbl.setText(activations+"");
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("Ich wurde geöffnet");
	}
	
}
public class Aufgabe_WindowListener {

	public static void main(String[] args) {
	
		Frame wind = new Frame("WindowListener");
		wind.setSize(640, 320);
		// WindowListener hinzufügen
		myWindowListener myW = new myWindowListener();
		wind.addWindowListener(myW);
		// Label hinzufügen
		wind.add(myW.lbl);
		wind.setVisible(true);	
		
	}	
}
