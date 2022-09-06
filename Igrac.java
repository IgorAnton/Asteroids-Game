package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends NebeskoTelo {
	
	private int[] xKord = new int[3];
	private int[] yKord = new int[3];
	
	public Igrac(int x, int y) {
		super(x, y, Color.RED, 10);
		
	}
	
	public int vrhX() {
		return xKord[1];
	}
	public int vrhY() {
		return yKord[1];
	}
	
	
	
	@Override
	public boolean preklapanje(int x, int y) {
		
		return false;
	}

	
	@Override
	public synchronized void paint(Graphics g) {
		//super.paint(g);
		g.setColor(Color.RED);
		for(int i = 0 ; i < 3 ; i++) {
			xKord[i] = (int)(getX() + getRadius()*Math.cos(i*2*(Math.PI/3) -22.5 ));
			yKord[i] = (int)(getY() + getRadius()*Math.sin(i*2*(Math.PI/3) -22.5 ));
		}
		
		
		g.drawPolygon(xKord,yKord,3);
		g.fillPolygon(xKord,yKord,3);
		
		
		
	}
}
