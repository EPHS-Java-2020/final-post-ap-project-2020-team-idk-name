package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Meteor {
	
	public double x, y, vy;
	private Image img;
	public int w, h;
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,w,h);
	}
	
	public Meteor() {
		Random r=new Random();
		w=r.nextInt(101)+50;
		h=w;
		x = r.nextInt(Game.WIDTH-w+1);
		y = 0;
		vy = (r.nextInt(21)+10)/10;
		try {
			img = ImageIO.read(new File("meteor-cutout.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void physics() {
		y += vy;
		if ((y + h) > Game.floorHeight) {
			y = 1000;
		}
	}
	
	public void update(Graphics g) {
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}

}
