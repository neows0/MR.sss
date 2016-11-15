//Just started this class and realized that in order to make a bird an overhall
//for collision is required
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bird extends GameObject {

    public Bird(int x, int y, ID id) {
	super(x, y, id);
	img = null;
	img = Game.images.getImgs().get(7);
	if (img != null){
	    HEIGHT = img.getHeight();
	    WIDTH = img.getWidth();
	    //WIDTH = 53;
	}
	else {
	    HEIGHT = 100;
	    WIDTH = 100;
	}
    }
    
    public void tick() {
	x += dX;
	y += dY;
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

    public  Rectangle getBounds(boolean includeZ) {
	return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
    }
    public void hit(GameObject collided) {
	
    }
    
}
