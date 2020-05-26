package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import java.awt.Rectangle;

public class Drink {
	
	public int w=50,h=100;
	public int x,y;
	private Image img;
	
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
	
	public Drink() {
		Random r=new Random();
		y=Game.floorHeight-h;
		x=r.nextInt(Game.WIDTH+1);
		try {
			img=ImageIO.read(new File("drink.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Graphics g) {
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}
	
	public void remove() {
		y=1000;
	}
	
	

}
