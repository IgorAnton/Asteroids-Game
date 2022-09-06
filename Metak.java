package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Metak {

	private int xPoc,yPoc;
	private int xKraj,yKraj;
	private int duzina;
	
	public Metak(int xP,int yP,int xK,int yK,int d) {
	
		this.xPoc = xP;
		this.yPoc = yP;
		this.xKraj = xK;
		this.yKraj = yK;
		this.duzina = d;
	}
	
	public int dohvYKraj() {
		return this.yKraj;
	}
	public int dohvXKraj() {
		return this.xKraj;
	}
	
	public int getDuzina() {
		return this.duzina;
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.drawLine(xPoc, yPoc, xKraj, yKraj);

	}
	
	public synchronized void pomeriY(int br) {
		this.yPoc+=br;
		this.yKraj+=br;
	}
	
}
