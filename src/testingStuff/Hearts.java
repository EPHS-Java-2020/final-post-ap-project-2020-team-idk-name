package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;

public class Hearts {
	
	private Image img;
	private int w=30,h=30;
	private Game game;
	
	public Hearts(Game game) {
		this.game=game;
		try {
			img=ImageIO.read(new File("Heart-Icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Graphics g) {
		g.drawImage(img,1200, 20, w,h,null);
		g.setFont(new Font("COMIC SANS", Font.PLAIN, 20));
		g.drawString("x"+game.getLives(), 1230, 50);
	}

}
