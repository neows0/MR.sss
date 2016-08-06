import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    BufferedImage img;
    
    public Player(int x, int y, ID id, Handler handler) {
	super(x, y, id, handler);
	img = null;
	img = Game.images.imageList.get(9);
	if (img != null){
	    HEIGHT = img.getHeight();
	    WIDTH = img.getWidth();
	}
	else {
	    HEIGHT = 110;
	    WIDTH = 72;
	}
	
    }

    public Rectangle getBounds(){
	return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void hit(GameObject collided){
	//HUD.HEALTH -= 2;
	//HUD.HEALTH = Game.clamp(HUD.HEALTH, 0, 100);
    }

    @Override
    public void tick() {
	if (Game.input.getW() && Game.input.getS())
	    dY = 0;
	else if (Game.input.getW())
	    dY = -5;
	else if (Game.input.getS())
	    dY = 5;
	else
	    dY = 0;

	if (Game.input.getA() && Game.input.getD())
	    dX = 0;
	else if (Game.input.getA())
	    dX = -5;
	else if (Game.input.getD())
	    dX = 5;
	else
	    dX = 0;
	
	x += dX;
	y += dY;

	x = Game.clamp(x, WIDTH / 2, Game.LVLWIDTH - WIDTH / 2);
	y = Game.clamp(y, HEIGHT / 2, Game.LVLHEIGHT - HEIGHT / 2);
	collision();
    }

    public void render(Graphics g) {
	//g.setColor(Color.white);
	//g.drawImage(img, x, y, null);
	//g.drawImage(img, x, y, x + 32, y + 32, 0, 0, 64, 64, null);
	//g.drawImage(img, x, y, x + WIDTH, y + HEIGHT, 0, 0, WIDTH, HEIGHT, null);
	System.out.println(Integer.toString(x) + " " + Integer.toString(y));
	
	int tempX = Game.WIDTH / 2;
	int tempY = Game.HEIGHT / 2;
	if (x - Game.WIDTH / 2 < 0)
	    tempX = x;
	else if (x + Game.WIDTH / 2 > Game.LVLWIDTH )
	    tempX = x - (Game.LVLWIDTH - Game.WIDTH);
	if (y < Game.HEIGHT / 2)
	    tempY = y;
	else if (y > Game.LVLHEIGHT - Game.HEIGHT / 2)
	    tempY = y - (Game.LVLHEIGHT - Game.HEIGHT);
	
	g.drawImage(img, tempX - WIDTH / 2,
		    tempY - HEIGHT / 2, tempX + WIDTH /2,
		    tempY + HEIGHT / 2, 0, 0, WIDTH, HEIGHT, null);
    }
}
