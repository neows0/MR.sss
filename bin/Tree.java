import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Tree extends GameObject {
    
    public Tree(int x, int y, ID id) {
	super(x, y, id);
	img = null;
	img = Game.images.getImgs().get(7);
	if (img != null){
	    HEIGHT = img.getHeight();
	    WIDTH = img.getWidth();
	}
	else {
	    HEIGHT = 100;
	    WIDTH = 100;
	}
    }

    public Rectangle getBounds(){
	return new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT / 2);
    }

    public void hit(GameObject collided){
        
    }

    @Override
    public void tick() {
	
    }

    public void render(Graphics g) {
	int [] cord = { 0, 0 };
	Game.screenLoc(cord);
	int tempX = x - cord[0];
	int tempY = y - cord[1];
	g.drawImage(img, tempX - WIDTH / 2, tempY - HEIGHT / 2,
		    tempX + WIDTH / 2, tempY + HEIGHT / 2,
		    0, 0, WIDTH, HEIGHT, null);
    }
}
