package gui;

import java.awt.Color;
import java.util.Random;

public class Generator extends Thread{

	private Simulator owner;
	
	private Svemir svemir ;
	
	private long sleepTime = 900;
	
	
	public Generator(Svemir s) {
		svemir  = s;
		
	}
	
	@Override
	public void run() {
		
		
			try {
				while(!this.isInterrupted()) {
				Thread.sleep(sleepTime);
			
				int x = (int)(Math.random()*200) ;
				int y = 0;
				int radius = (int)(Math.random()*20) + 10	;
				
				NebeskoTelo nt = new Kometa(x,y,Color.GRAY,radius);
				
				svemir.dodajNebeskoTelo(nt);
			
				
				
				}
			} catch (InterruptedException e) {}
		
		
		
		synchronized (this) {
			
			notifyAll();
		}
		
	}
	
	public synchronized void finish() {
		
		
		this.interrupt();
		
		
		
	}
	
}
