package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TheDude {

	public double x, y, vx, vy;
	private Image img, standImgL,standImgR,crouchImgL,crouchImgR;
	public int w = 50, h = 100;
	public boolean crouch = false;
	public boolean isSlow = false;

	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, w, h);
	}

	public void setSlow(boolean state) {
		isSlow = state;
	}

	public TheDude() {
		x = (Game.WIDTH / 2) - (w / 2);
		y = Game.floorHeight - h;
		vx = 0;
		vy = 0;
		try {
			standImgL = ImageIO.read(new File("standL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchImgL = ImageIO.read(new File("crouchL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standImgR = ImageIO.read(new File("standR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchImgR = ImageIO.read(new File("crouchR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img=standImgL;

	}

	public void physics() {
		if (isSlow == false) {
			y += vy;
			x += vx;
		} else {
			y+=vy/2;
			x+=vx/2;
		}
		vy = vy + .5;
		if ((y + h) > Game.floorHeight) {
			y = Game.floorHeight - h;
		}
		if (x < 0) {
			right();
			x = 0;
		}
		if (x + w > Game.WIDTH) {
			left();
			x = Game.WIDTH - w;
		}
		if(crouch&&img==crouchImgL&&isSlow==false) {
			vx=-3;
		}
		if(crouch&&img==crouchImgR&&isSlow==false) {
			vx=3;
		}
		if(crouch&&img==crouchImgL&&isSlow==true) {
			vx=-1.5;
		}
		if(crouch&&img==crouchImgR&&isSlow==true) {
			vx=1.5;
		}
	}

	public void right() {
		vx = 5;
		if (!crouch) {
			img=standImgR;
		} else {
			img=crouchImgR;
		}
	}

	public void left() {
		vx = -5;
		if (!crouch) {
			img=standImgL;
		} else {
			img=crouchImgL;
		}
	}

	public void jump() {
		crouch = false;
		if (y == Game.floorHeight - h) {
			if (vx > 0) {
				img=standImgR;
			} else {
				img=standImgL;
			}
			h = 100;
			y = Game.floorHeight - h;
			vy = -12;
		}
	}

	public void crouch() {
		if (y + h == Game.floorHeight) {
			crouch = true;
			if (vx > 0) {
				img=crouchImgR;
			} else {
				img=crouchImgL;
			}
			h = 50;
			y = Game.floorHeight-50;
		}
	}

	public void update(Graphics g) {
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}

	public void reset() {
		x = (Game.WIDTH / 2) - w / 2;
		y = Game.floorHeight - h;
		vy = 0;
		vx = 0;
	}

}
