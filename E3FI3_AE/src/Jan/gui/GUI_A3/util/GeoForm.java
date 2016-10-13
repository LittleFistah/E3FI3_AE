package Jan.gui.GUI_A3.util;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GeoForm {

	protected int x;
	protected int y;
	protected Color color;

	public GeoForm(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public abstract boolean isInGeoForm(int x, int y);
	public abstract void draw(Graphics g);
}
