package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulator extends Frame {
	
	private Panel bottomPanel = new Panel();
	
	Panel centerPanel = new Panel();
	
	Svemir svemir = new Svemir(this);
	
	Generator generator = new Generator(svemir);
	
	private void populateWindow() {
		
		Igrac igrac = new Igrac(getWidth()/2, getHeight()-75);
		svemir.dodajIgraca(igrac);
		Button pokreni = new Button("Pokreni!");
		
		bottomPanel.add(pokreni);
	
		svemir.setBackground(Color.BLACK);
		svemir.setPreferredSize(new Dimension(200,400));
		
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		centerPanel.add(svemir);
		
		pokreni.addActionListener((ae)->{
			svemir.pokreni();
			generator.start();
			
			
			pokreni.setEnabled(false);
			centerPanel.requestFocus();
			
		});
		
		add(centerPanel,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
	
	
		
	}
	
	public Simulator() {
		setBounds(700,200,200,400);
		setResizable(false);
		setTitle("");

		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			
			public synchronized void windowClosing(WindowEvent e ) {
			
				dispose();
				
				svemir.finish();
				generator.finish();
			}
			
		});
		
		setVisible(true);
		
	}
	

	public static void main(String[] args) {
		new Simulator();
	}

}
