package theGame;

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

	public static final int FPS = 60, WIDTH = 1366, HEIGHT = 695, floorHeight = 500;

	private TheDude dude;
	private JFrame frame;
	private JPanel panel;
	private Timer t;
	private int metInt = 0;
	private ArrayList<Meteor> m = new ArrayList<Meteor>();
	private ArrayList<Meteor> toRemove = new ArrayList<Meteor>();
	private Random r = new Random();
	private int metInterval = 60;
	private ArrayList<Drink> d = new ArrayList<Drink>();
	private int driInt = 900;
	private int drink = 0;
	private int drinkLenMax = 300;
	private int drinkLen = 0;
	private ArrayList<Drink> toRemoveD = new ArrayList<Drink>();
	private ArrayList<Fireball> f = new ArrayList<Fireball>();
	private ArrayList<Fireball> toRemoveF = new ArrayList<Fireball>();
	private int fireInt = 600;
	private int fire = 0;
	private int lives = 3;
	private int score = 0;
	private boolean isPlaying = false;
	private boolean isDead = false;
	

	public void reset() {
		for (int n = m.size() - 1; n >= 0; n--) {
			m.remove(n);
		}
		for (int z = f.size() - 1; z >= 0; z--) {
			f.remove(z);
		}
		dude.reset();
		metInt = 0;
		m = new ArrayList<Meteor>();
		toRemove = new ArrayList<Meteor>();
		metInterval = 60;
		d = new ArrayList<Drink>();
		driInt = 900;
		drink = 0;
		drinkLenMax = 300;
		drinkLen = 0;
		f = new ArrayList<Fireball>();
		toRemoveF = new ArrayList<Fireball>();
		fireInt = 600;
		fire = 0;
		lives = 3;
		panel = new GamePanels(this, dude, m, d, f);
		frame.add(panel);
		frame.setVisible(true);
		frame.addKeyListener(this);
	}

	public static void main(String[] args) {
		new Game().go();
	}

	public boolean getPlaying() {
		return isPlaying;
	}

	public boolean getDead() {
		return isDead;
	}

	public void go() {
		frame = new JFrame("");
		dude = new TheDude();
		panel = new GamePanels(this, dude, m, d, f);
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!isPlaying) {
				score = 0;
			}
			isPlaying = true;
			isDead = false;
		}
	}

	public int getLives() {
		return lives;
	}

	public int getScore() {
		return score;
	}

	public void actionPerformed(ActionEvent e) {
		panel.repaint();
		if (isPlaying) {
			dude.physics();
			if (metInt == metInterval) {
				m.add(new Meteor());
				metInterval = (r.nextInt(6) + 1) * 15;
				metInt = 0;
			}
			if (fire == fireInt) {
				f.add(new Fireball());
				fireInt = (r.nextInt(11) + 10) * 60;
				fire = 0;
			}
			if (drink == driInt) {
				d.add(new Drink(dude.getRect()));
				driInt = (r.nextInt(11) + 10) * 60;
				drink = 0;
			}
			for (Meteor n : m) {
				n.physics();
				if (dude.getRect().intersects(n.getRect())) {
					toRemove.add(n);
					n.y = 1000;
					lives--;
				}
				if (n.y >= 1000) {
					toRemove.add(n);
				}
			}
			for (Fireball z : f) {
				z.physics();
				if (dude.getRect().intersects(z.getRect())) {
					toRemoveF.add(z);
					lives--;
				}
				if (z.x >= 1500) {
					toRemoveF.add(z);
				}

			}
			for (Drink r : d) {
				if (dude.getRect().intersects(r.getRect())) {
					toRemoveD.add(r);
					r.remove();
					dude.setSlow(true);
					drinkLen = 0;
					}
			}
			for (Meteor r:toRemove) {
				m.remove(r);
			}
			for (Fireball fir: toRemoveF) {
				f.remove(fir);
			}
			for (Drink dri:toRemoveD) {
				d.remove(dri);
			}
			if (drinkLen == drinkLenMax) {
				dude.setSlow(false);
			}
			if (dude.isSlow()) {
				drinkLen++;
			}
			metInt++;
			drink++;
			fire++;
			score++;
			if (lives == 0) {
				isDead = true;
				isPlaying = false;
			}
		}

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}