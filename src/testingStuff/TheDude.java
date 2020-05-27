package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TheDude {

	public double x, y, vx, vy;
	private Image img;
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
			img = ImageIO.read(new File("character-cutout.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

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
			x = 0;
		}
		if (x + w > Game.WIDTH) {
			x = Game.WIDTH - w;
		}
	}

	public void right() {
		vx = 5;
		if (!crouch) {
			try {
				img = ImageIO.read(new File("character-cutout.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(new File("dude.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void left() {
		vx = -5;
		if (!crouch) {
			try {
				img = ImageIO.read(new File("character-cutout.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(new File("dude.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void jump() {
		crouch = false;
		if (y == Game.floorHeight - h) {
			if (vx >= 0) {
				try {
					img = ImageIO.read(new File("character-cutout.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					img = ImageIO.read(new File("character-cutout.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
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
				try {
					img = ImageIO.read(new File("dude.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					img = ImageIO.read(new File("dude.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			h = 50;
			y = y - h;
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
