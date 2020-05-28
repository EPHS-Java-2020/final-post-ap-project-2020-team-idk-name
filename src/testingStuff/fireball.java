package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class fireball {
	public double x, y, vx;
	private Image img;
	public int w, h;
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,w,h);
	}
	
	public fireball() {
		Random r=new Random();
		w=20;
		h=10;
		x =0;
		y = Game.floorHeight-50;
		vx = (r.nextInt(21)+10)/10;
		try {
			img = ImageIO.read(new File("fire-BallL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void physics() {
		x += vx;
		if ((x + w) > Game.WIDTH) {
			x = 1500;
		}
	}
	
	public void update(Graphics g) {
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}

}
