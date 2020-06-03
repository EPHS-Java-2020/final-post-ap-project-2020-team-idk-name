package theGame;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import java.awt.Rectangle;

public class Drink {
	
	//all instance variables
	public int w=25,h=50;
	public int x,y;
	private Image img;
	
	public Rectangle getRect() { //returns the hit box of the slow drink
		return new Rectangle(x,y,w,h);
	}
	
	public Drink(Rectangle dudeRect) { //slow drink object: spawns an energy drink at a random x position at a fixed y position over a random spawn interval, and generates the image for the slow drink
		Random r=new Random();
		y=Game.floorHeight-h;
		do {
		x=r.nextInt(Game.WIDTH+1);
		}while(dudeRect.intersects(getRect()));
		try {
			img=ImageIO.read(new File("drink.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Graphics g) { //updates the image of the slow drink at the current frame rate
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}
	
	public void remove() { //removes the slow drink
		y=1000;
	}
	
	

}
