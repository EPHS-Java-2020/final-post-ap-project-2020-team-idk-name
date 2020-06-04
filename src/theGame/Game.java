package theGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game implements KeyListener, ActionListener {

	public static final int FPS = 60, WIDTH = 1366, HEIGHT = 695, floorHeight = 500; //creates the background dimensions and sets a frame rate per second for the game to run at
	
	//all instance variable
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
	private int highscore=0; 

	public void reset() { //resets all aspects of the game to their original state
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
	

	public static void main(String[] args) { //starts the game
		new Game().go();
	}

	public boolean getPlaying() { //returns a boolean of whether the game is being played or not
		return isPlaying;
	}

	public boolean getDead() { //returns a boolean if the player is dead or not
		return isDead;
	}

	public void go() { //makes frames for the background, player, and all the game panels, and exports the high score to an external file on the device so that it can be tracked
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
		try {
			PrintStream output= new PrintStream(new File("highscore.txt"));
			output.println(0);
			output.close();
		}catch(Exception e3) {
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) { //defines all the key presses you can make including space to start/restart the game
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

	public int getLives() { //returns the number of lives remaining for the player
		return lives;
	}
	public int getHighScore() { //returns the local high score
		return highscore;
	}
	public int getScore() { //returns the current score of the player
		return score;
	}

	public void actionPerformed(ActionEvent e) { //manages all the game's physics (movement of player/meteors/fire balls, slow drink spawns, hit box interference leading to losing lives, high score tracker, etc.)
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
			
				try {
					Scanner input = new Scanner(new File("highscore.txt"));
					while (input.hasNext()) {
						String word= input.next();
						int num = Integer.parseInt(word);
						highscore = num;
						if (score>num) {
							highscore= score;
							try {
								PrintStream output= new PrintStream(new File("highscore.txt"));
								output.println(score);
								output.close();
							}catch(Exception e3) {
								
							}
						}
						input.close();
					}
				} catch (Exception e2) {
					
				}
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
