package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class NebeskoTelo extends Objekat {

	private int radiusInPixels;
	
	public NebeskoTelo(int x, int y, Color c, int r) {
		super(x, y, c);
		this.radiusInPixels = r;
	}
	
	public int getRadius() {
		return this.radiusInPixels;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

}
