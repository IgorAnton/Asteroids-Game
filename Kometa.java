package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Kometa extends NebeskoTelo {
	
	
	private double angle = Math.toRadians(Math.random() * 360)  ;
	public Kometa(int x, int y, Color c, int r) {
		super(x, y, c, r);
		
	}

	@Override
	public synchronized void paint(Graphics g) {
		
		super.paint(g);
		g.translate(getX(), getY());
		Color prevColor = g.getColor();
		g.setColor(this.getColor());
		int xCords[] = new int[5];
		int yCords[] = new int[5];
		
		
		
		//double angle = 2*Math.PI / 5;
		
		double ang = angle;
		
		for(int i = 0 ; i < 5 ; i++) {
			xCords[i] = (int)(this.getRadius() * Math.cos(ang ));
			yCords[i] = (int)(this.getRadius() * Math.sin(ang  ));
			ang+=Math.toRadians(360/5);
		}
		
		g.drawPolygon(xCords,yCords, 5);
		g.fillPolygon(xCords,yCords,5);
		
		g.setColor(prevColor);
		
		g.translate(-getX(), -getY());
		
	}

	@Override
	public boolean preklapanje(int x, int y) {
		
		return Math.sqrt(Math.pow(this.getX() - x,2) + Math.pow(this.getY() - y, 2)) <= this.getRadius();
	}
	
}
