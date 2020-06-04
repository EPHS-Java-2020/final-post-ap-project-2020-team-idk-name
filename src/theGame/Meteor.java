package theGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Meteor {
	
	public double x, y, vy;
	private Image img;
	public int w, h;
	
	public Rectangle getRect() {
		// Returns character hitbox
		return new Rectangle((int)x,(int)y,w,h);
	}
	
	public Meteor() {
		//Creates a new meteor object with a random size and position
		Random r=new Random();
		w=r.nextInt(101)+50;
		h=w;
		x = r.nextInt(Game.WIDTH-w+1);
		y = 0;
		vy = (r.nextInt(21)+10)/10;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("meteor-cutout.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void physics() {
		//determines the meteors velocity
		y += vy;
		if ((y + h) > Game.floorHeight) {
			y = 1000;
		}
	}
	
	public void update(Graphics g) {
		//updates the meteors position at the current framerate
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}

}
