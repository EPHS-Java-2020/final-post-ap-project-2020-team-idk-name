package testingStuff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanels extends JPanel {

	private static final long serialVersionUID = 1L;
	private TheDude dude;
	private Game game;
	private Image background;
	private Image heart;
	private ArrayList<Meteor> m = new ArrayList<>();
	private ArrayList<Drink> d = new ArrayList<>();
	private ArrayList<Fireball> f = new ArrayList<>();

	public GamePanels(Game game, TheDude dude, ArrayList<Meteor> m, ArrayList<Drink> d, ArrayList<Fireball> f) {
		this.dude = dude;
		this.game = game;
		this.m = m;
		this.d = d;
		this.f = f;
		try {
			background = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			heart = ImageIO.read(new File("Heart-Icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g1) {
		g1.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g1.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g1.setColor(new Color(56, 24, 0));
		g1.fillRect(0, Game.floorHeight, Game.WIDTH, Game.HEIGHT - Game.floorHeight);
		if (game.getDead()) {
			g1.setFont(new Font("Super Mario 256", Font.PLAIN, 40));
			g1.setColor(Color.black);
			g1.drawString("You died. Your score was "+game.getScore(), 400, 200);
			g1.drawString("Press space to play again", 400, 250);
			game.setLives(3);
		} else if (game.getPlaying()) {
			dude.update(g1);
			for (Drink r : d) {
				r.update(g1);
			}
			for (Meteor e : m) {
				e.update(g1);
			}
			for (Fireball x : f) {
				x.update(g1);
			}
			g1.setFont(new Font("Super Mario 256", Font.PLAIN, 40));
			g1.setColor(Color.black);
			g1.drawString("Score: " + game.getScore(), 30, 50);
			g1.drawImage(heart, 1250, 20, 30, 30, null);
			g1.drawString("x" + game.getLives(), 1280, 50);
		} else {
			g1.setFont(new Font("Super Mario 256", Font.PLAIN, 40));
			g1.setColor(Color.black);
			g1.drawString("WELCOME TO GAME NAME", 400, 200);
			g1.drawString("PRESS SPACE TO START", 400, 250);

		}
	}

}
