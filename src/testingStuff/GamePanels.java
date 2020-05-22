package testingStuff;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanels extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TheDude dude;
	private Game game;
	private Image background;
	
	public GamePanels(Game game, TheDude dude) {
		this.dude=dude;
		this.game=game;
		try {
        	background=ImageIO.read(new File("super-smash-bros-ultimate-switch-hero.jpg"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void paintComponent(Graphics g1) {
        g1.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
        g1.drawImage(background, 0, 0, Game.WIDTH, Game.floorHeight, null);
        g1.setColor(Color.green);
        g1.fillRect(0, Game.floorHeight, Game.WIDTH, Game.HEIGHT-Game.floorHeight);
        dude.update(g1);
    }

}
