package testingStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Game implements KeyListener, ActionListener {

	public static final int FPS = 60, WIDTH = 1366, HEIGHT = 695,floorHeight=500;

	private TheDude dude;
	private JFrame frame;
	private JPanel panel;
	private Timer t;
	private int met;
	private ArrayList<Meteor> m=new ArrayList<Meteor>();
	private Random r=new Random();
	private int interval=60;
	private ArrayList<Drink> d=new ArrayList<Drink>();	
	private int driInt=600;
	private int drink=0;
	private int drinkLenMax=300;
	private int drinkLen;
	public static void main(String[] args) {
		new Game().go();
	}

	public void go() {
		frame = new JFrame("");
		dude = new TheDude();
		panel = new GamePanels(this, dude, m, d);
		frame.add(panel);

		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(this);

		t = new Timer(1000 / FPS, this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			dude.jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			dude.left();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			dude.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			dude.crouch();
		}
	}


	public void actionPerformed(ActionEvent e) {
		panel.repaint();
		dude.physics();
		if (met==interval) {
			m.add(new Meteor());
			interval=(r.nextInt(6)+1)*15;
			
			met=0;
		}
		if(drink==driInt) {
			d.add(new Drink(dude.getRect()));
			driInt= (r.nextInt(11)+10)*60;
			drink=0;
		}
		ArrayList<Meteor> toRemove=new ArrayList<Meteor>();
		for (Meteor n:m) {
			n.physics();
			if (dude.getRect().intersects(n.getRect())) {
				toRemove.add(n);
				n.y=1000;
			}
			if(n.y>=1000) {
				toRemove.add(n);
			}
		}
		for (Meteor r:toRemove) {
			m.remove(r);
		}
		for(Drink r:d) {
			if (dude.getRect().intersects(r.getRect())) {
				r.remove();
				dude.setSlow(true);
				drinkLen=0;
			}
		}
		if(drinkLen==drinkLenMax) {
			dude.setSlow(false);
		}
		if(dude.isSlow()) {
			drinkLen++;
		}
		met++;
		drink++;
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
