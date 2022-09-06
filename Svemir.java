package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Svemir extends Canvas implements Runnable{
	
	private ArrayList<NebeskoTelo> tela = new ArrayList<>();
	
	private ArrayList<Metak> meci = new ArrayList<>();
	
	private Simulator owner;
	
	
	private int poeni = 0;
	
	private long sleepTime = 100;
	
	protected Thread thread;
	
	private Color c = Color.BLACK;
	
	private Igrac igrac ;
	
	public Svemir(Simulator owner) {
		this.owner = owner;
		//this.igrac = i;
		//igrac.paint(getGraphics());
		addKeyListener(new KeyAdapter() {
		
			public void keyTyped(KeyEvent e) {
				char key = Character.toUpperCase(e.getKeyChar());
				
				switch(key) {
					
				case KeyEvent.VK_A:{
					
					synchronized (this) {
						pomeriIgraca(-5);
					}
					
					break;
				}
				case KeyEvent.VK_D:{
					
					synchronized (this) {
						pomeriIgraca(5);
					}
					
					break;
				}
				case KeyEvent.VK_SPACE:{
					
					ispaliMetak();
					break;
				}
				}
			}
			
		
		
		});
		
		//setBackground(c);
		
		//repaint();
	}
	
	public int getPoeni() {
		return poeni;
	}
	
	public synchronized void ispaliMetak() {
		meci.add(new Metak(igrac.getX(),igrac.vrhY(),igrac.getX(),igrac.vrhY()+8,8));
	}
	
	public synchronized void pomeriIgraca(int br) {
		igrac.setX(igrac.getX()+br);
	}
	
	public void dodajIgraca(Igrac i) {
		this.igrac = i;
		
	}
	
	public synchronized void pokreni() {
		
		//finish();
		thread = new Thread(this);
		
		thread.start();
		
		
	}
	
	public synchronized  void dodajNebeskoTelo(NebeskoTelo nt) {
		tela.add(nt);
	}
	
	public synchronized void finish() {
		
		if(thread != null) {
			thread.interrupt();
		}
		/*
		while (thread != null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		*/
		
	}
	
	@Override
	public synchronized void paint(Graphics g) {
		
	try {
			//super.paint(g);
			igrac.paint(g);
			String str = poeni+"";
			g.drawString(str, getWidth()/2, 20);
				for(NebeskoTelo nt : tela) {
					nt.paint(g);
					}
			//pomeriZa5Pixela(g);
			
				ArrayList<Metak> kolizijaMetak = new ArrayList<>();				
				for(Metak m :meci) {
					m.paint(g);
					m.pomeriY(-m.getDuzina());
					
					ArrayList<NebeskoTelo> zaizbaciti = new ArrayList<>();
					
					for(int i = 0 ; i < tela.size(); i++) {
						if(tela.get(i).preklapanje(m.dohvXKraj(), m.dohvYKraj())) {
							if(tela.get(i).getRadius() <=20) {
								zaizbaciti.add(tela.get(i));
								
								poeni+=10;
								g.drawString("Osvojeni poeni: ", getWidth()-50, getHeight()-10);
							
								//repaint(); // ? 
							}else {
								int r = tela.get(i).getRadius();
								int x = tela.get(i).getX();
								int y = tela.get(i).getY();
								Color c1 = tela.get(i).getColor();
								zaizbaciti.add(tela.get(i));
								Kometa nt1 = new Kometa(x - r/2, y, c1, 10);
								Kometa nt2 = new Kometa(x+r/2, y, c1, 10);
								tela.add(nt1);
								tela.add(nt2);
								//repaint();// ?
							}
							
							try {
							kolizijaMetak.add(m);
							}
							catch (Exception e) {
								// TODO: handle exception
							}
							
						}
						
						if(!zaizbaciti.isEmpty()) {
							tela.removeAll(zaizbaciti);
						}
						if(!kolizijaMetak.isEmpty()) {
							
							try {
								meci.removeAll(kolizijaMetak);
								}
								catch (Exception e) {
									
								}
								
						}
						
						try {
						if(tela.get(i).preklapanje(igrac.vrhX(), igrac.vrhY())){
							this.finish();
						}}catch (Exception e) {
							
						}
						
						}
					}
					
				
				
			ArrayList<Metak> meciZaIzbaciti = new ArrayList<>();	
			ArrayList<NebeskoTelo> zaIzbaciti = new ArrayList<>();
		
				for(NebeskoTelo nt : tela) {
					if(nt.getY() == owner.getHeight()) {
						zaIzbaciti.add(nt);
					}
				}
				if(!zaIzbaciti.isEmpty()) {
					tela.removeAll(zaIzbaciti);
					
				}
				
				for(Metak m : meci) {
					if(m.dohvYKraj() == 0) {
						meciZaIzbaciti.add(m);
					}
				}
				if(!meciZaIzbaciti.isEmpty()) {
					meci.removeAll(meciZaIzbaciti);
				}
					
	}
	catch(Exception e1) {}
			
		
	}

	
	
	@Override
	public void run() {
		try {
		while( !Thread.interrupted()) {
				igrac.paint(getGraphics());
				Thread.sleep(sleepTime);
				repaint();
				pomeriZa5Pixela(getGraphics());
				
				
		}
			} catch (InterruptedException e) {
			}
			
		
			
		synchronized (this) {
			thread = null;
			notify();
		}
	
		
	}
	
	public synchronized void pomeriZa5Pixela(Graphics g) {
		
		for(NebeskoTelo nt : tela)
		{
			nt.setY(nt.getY()+5);
		}
	}

}
