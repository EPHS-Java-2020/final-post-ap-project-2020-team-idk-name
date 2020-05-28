package testingStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TheDude {

	public double x, y, vx, vy;
	private Image img, standL, standR, crouchL, crouchR;
	private Image standLD, standRD, crouchLD, crouchRD;
	public int w = 50, h = 100;
	public boolean crouch = false;
	public boolean isSlow = false;
	public boolean didJump = false;

	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, w, h);
	}

	public void setSlow(boolean state) {
		isSlow = state;
	}

	public boolean isSlow() {
		return isSlow;
	}

	public TheDude() {
		x = (Game.WIDTH / 2) - (w / 2);
		y = Game.floorHeight - h;
		vx = 0;
		vy = 0;
		try {
			standL = ImageIO.read(new File("standL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchL = ImageIO.read(new File("crouchL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standR = ImageIO.read(new File("standR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchR = ImageIO.read(new File("crouchR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standRD = ImageIO.read(new File("standRD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchRD = ImageIO.read(new File("crouchRD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standLD = ImageIO.read(new File("standLD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchLD = ImageIO.read(new File("crouchLD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = standL;

	}

	public void physics() {
		if (isSlow == false) {
			y += vy;
			x += vx;
		} else {
			y += vy / 2;
			x += vx / 2;
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
	}

	public void right() {
		vx = 5;
		if (crouch == true) {
			vx = 3;
		}
	}

	public void left() {
		vx = -5;
		if (crouch == true) {
			vx = -3;
		}
	}

	public void jump() {
		didJump = true;
		if (Math.abs(vx) == 3) {
			vx = 5 * vx / 3;
		}
		if (y == Game.floorHeight - h) {
			h = 100;
			y = Game.floorHeight - h;
			if (crouch == false) {
				vy = -12;
			}
			crouch = false;
		}

	}

	public void crouch() {
		if (y + h == Game.floorHeight) {
			crouch = true;
			if (Math.abs(vx) == 5) {
				vx = 3 * vx / 5;
			}
			h = 50;
			y = Game.floorHeight - 50;
		}
	}

	public void update(Graphics g) {
		if (crouch == true) {
			if (isSlow == false) {
				if (vx > 0) {
					img = crouchR;
				} else {
					img = crouchL;
				}
			} else {
				if (vx > 0) {
					img = crouchRD;
				} else {
					img = crouchLD;
				}
			}
		} else {
			if (isSlow == false) {
				if (vx > 0) {
					img = standR;
				} else {
					img = standL;
				}
			} else {
				if (vx > 0) {
					img = standRD;
				} else {
					img = standLD;
				}
			}
		}
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}

	public void reset() {
		x = (Game.WIDTH / 2) - w / 2;
		y = Game.floorHeight - h;
		vy = 0;
		vx = 0;
	}

}
