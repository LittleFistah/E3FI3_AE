package Jan.gui.GUI_A3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import Jan.gui.GUI_A3.util.Rechteck;

public class Gui extends JFrame implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private Rechteck rect1;
	private Rechteck rect2;
	private int width = 20;
	private int height = 20;

	public Gui() {
		super("Aufgabe3");
		rect1 = new Rechteck(200, 200, width, height);
		rect2 = new Rechteck(100, 100, width, height);
		super.setSize(640, 320);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.addMouseMotionListener(this);
		super.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		rect1.draw(g);
		rect2.draw(g);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (rect1.isInGeoForm(this.getMousePosition().x, this.getMousePosition().y)) {
			rect1.setRechteck(this.getMousePosition().x-width/2, this.getMousePosition().y-height/2, width, height);
		}
		if (rect2.isInGeoForm(this.getMousePosition().x, this.getMousePosition().y)) {
			rect2.setRechteck(this.getMousePosition().x-width/2, this.getMousePosition().y-height/2, width, height);
		}		
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (rect1.isInGeoForm(this.getMousePosition().x, this.getMousePosition().y)) {
			rect1.setColor(Color.green);
		}else rect1.setColor(Color.black);
		
		if (rect2.isInGeoForm(this.getMousePosition().x, this.getMousePosition().y)) {
			rect2.setColor(Color.green);
		}else rect2.setColor(Color.black);
		
		repaint();
	}

}
