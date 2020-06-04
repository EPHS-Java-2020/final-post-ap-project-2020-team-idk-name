package theGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
		// Returns character hitbox
		return new Rectangle((int) x, (int) y, w, h);
	}
	

	public void setSlow(boolean state) {
		//sets the state to a slower speed
		isSlow = state;
	}
	
	public boolean isSlow() {
		//return if character is slow or not
		return isSlow;
	}
	

	public TheDude() {
		//Changes the characters image based on what he's doing
		x = (Game.WIDTH / 2) - (w / 2);
		y = Game.floorHeight - h;
		vx = 0;
		vy = 0;
		try {
			standL = ImageIO.read(getClass().getClassLoader().getResource("standL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchL = ImageIO.read(getClass().getClassLoader().getResource("crouchL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standR = ImageIO.read(getClass().getClassLoader().getResource("standR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchR = ImageIO.read(getClass().getClassLoader().getResource("crouchR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standRD = ImageIO.read(getClass().getClassLoader().getResource("standRD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchRD = ImageIO.read(getClass().getClassLoader().getResource("crouchRD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			standLD = ImageIO.read(getClass().getClassLoader().getResource("standLD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crouchLD = ImageIO.read(getClass().getClassLoader().getResource("crouchLD.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = standL;

	}
	

	public void physics() {
		//This determines the characters speed, velocity, and acceleration
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
		//Changes his speed based on what he's doing
		vx = 5;
		if (crouch == true) {
			vx = 3;
		}
	}

	public void left() {
		//Changes his speed based on what he's doing
		vx = -5;
		if (crouch == true) {
			vx = -3;
		}
	}

	public void jump() {
		//Changes his speed based on what he's doing
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
		//Changes his speed based on what he's doing
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
		//updates the image based on what he's doing
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
		//resets the character to original state and position
		x = (Game.WIDTH / 2) - (w / 2);
		y = Game.floorHeight - h;
		vx = 0;
		vy = 0;
		isSlow=false;
		crouch=false;
		img = standL;
	}


}
