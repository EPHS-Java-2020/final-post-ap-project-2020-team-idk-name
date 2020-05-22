package testingStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
//Hello This IS SAI i gool hai hia 

public class Game implements KeyListener, ActionListener {

	public static final int FPS = 60, WIDTH = 1366, HEIGHT = 695,floorHeight=500;

	private TheDude dude;
	private JFrame frame;
	private JPanel panel;
	private Timer t;

	public static void main(String[] args) {
		new Game().go();
	}

	public void go() {
		frame = new JFrame("");
		dude = new TheDude();
		panel = new GamePanels(this, dude);
		frame.add(panel);

		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(this);

		t = new Timer(1000 / FPS, this);
		t.start();
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.repaint();
		dude.physics();
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
