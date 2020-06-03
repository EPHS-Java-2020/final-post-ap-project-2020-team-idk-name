package theGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fireball {
	//all instance variables
	public double x, y, vx;
	private Image img;
	public int w, h;
	private int k;

	public Rectangle getRect() { //returns the hit box of the fire ball
		return new Rectangle((int) x, (int) y, w, h);
	}

	public Fireball() { //fire ball object: spawns a fire ball into the game with a random size, speed, and spawn interval; and generates the image based on that
		Random r = new Random();
		k = r.nextInt(2);
		int s=r.nextInt(2)+1;
		w = s*20;
		h = s*10;
		vx = ((r.nextInt(41) + 10) / 10);
		y=(r.nextInt(2)*30)+420;
		if (k == 0) {
			x = 0;
			try {
				img = ImageIO.read(new File("fire-BallL.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			x = Game.WIDTH - w;
			try {
				img = ImageIO.read(new File("fire-BallR.png"));
			} catch (IOException f) {
				f.printStackTrace();
			}
		}

	}

	public void physics() { //determines the velocity of the fire ball

		if (k == 0) {
			x += vx;
			if ((x + w) > Game.WIDTH) {
				x = 1500;
			}
		} else {
			x -= vx;
			if ((x) < 0) {
				x = 1500;
			}
		}
	}

	public void update(Graphics g) { //updates the image of the fire ball based at the current frame rate
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}

}
