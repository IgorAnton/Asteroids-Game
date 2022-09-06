package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {

	private int x,y;
	private Color color;
	
	public Objekat(int x , int y , Color c) {
		
		this.x = x;
		this.y = y;
		this.color = c;
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract boolean preklapanje(int x , int y);
	
/*	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Objekat))
			return false;
		
		Objekat o = (Objekat)obj;
		//TODO verovatno ce zatrebati za kasnije nek stoji
		return super.equals(obj);
	}*/
	
	
	public abstract void paint(Graphics g);
}
