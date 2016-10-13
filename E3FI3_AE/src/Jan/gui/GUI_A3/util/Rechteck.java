package Jan.gui.GUI_A3.util;

import java.awt.Graphics;

public class Rechteck extends GeoForm{

	protected int width;
	protected int height;
	
	public Rechteck(int x, int y, int w, int h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}
	
	public void setRechteck(int x, int y, int w, int h){
		super.setPosition(x, y);
		this.width = w;
		this.height = h;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	@Override
	public boolean isInGeoForm(int x, int y) {
		if (x > super.getX() && x < super.getX()+getHeight() &&
				y > super.getY() && y < super.getY()+getWidth()) {
			return true;
		}
		return false;
		
	}

	@Override
	public void draw(Graphics g ) {
		g.setColor(super.getColor());
		g.fillRect(x, y, width, height);
		
	}

}
