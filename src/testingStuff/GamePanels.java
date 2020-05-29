package testingStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanels extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TheDude dude;
	@SuppressWarnings("unused")
	private Game game;
	private Image background;
	private ArrayList<Meteor> m = new ArrayList<>();
	private ArrayList<Drink> d=new ArrayList<>();
	private ArrayList<fireball> f = new ArrayList<>();

	public GamePanels(Game game, TheDude dude, ArrayList<Meteor> m, ArrayList<Drink> d,ArrayList<fireball>f) {
		this.dude = dude;
		this.game = game;
		this.m = m;
		this.d=d;
		this.f=f;
		try {
			background = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g1) {
		g1.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g1.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g1.setColor(new Color(56,24,0));
		g1.fillRect(0, Game.floorHeight, Game.WIDTH, Game.HEIGHT - Game.floorHeight);
		dude.update(g1);
		for(Drink r:d) {
			r.update(g1);
		}
		for (Meteor e : m) {
			e.update(g1);
		}
		for (fireball x:f) {
			x.update(g1);
		}
	
	}

}
