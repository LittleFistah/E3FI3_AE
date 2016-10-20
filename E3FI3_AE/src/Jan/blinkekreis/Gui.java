package Jan.blinkekreis;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Gui extends JFrame{

	private Color color;
	private Thread blinker;
	private boolean isBlinking = true;
	
	public Gui() {
		init();
	}
	
	private void init(){
		super.setTitle("Blinkekreis");
		super.setSize(320, 320);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
		blinker = new Thread() {
			@Override
			public void run() {
				do{
					if(color != Color.YELLOW){
						color = Color.YELLOW;
					}else {
						color = Color.BLUE;
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}while(isBlinking);
				super.run();
			}
		};
		blinker.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillOval(10, 20, 300, 300);
	}
}
