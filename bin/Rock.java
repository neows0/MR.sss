import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Rock extends GameObject {

    public static final int Large = 2;
    public static final int Medium = 1;
    public static final int Small = 0;
    public Rock(int x, int y, ID id) {
	this(x,y,id,(new Random()).nextInt(3));
        
    }
    public Rock(int x, int y, ID id, int rockSize) {
	super(x, y, id);
	img = null;
	imgs = Game.images.getDir("rocks");
	img = imgs.get(rockSize);
	if (img != null){
	    IMGHEIGHT = img.getHeight();
	    //System.out.println(Integer.toString(HEIGHT));
	    if (rockSize == 0)
		DEPTH = IMGHEIGHT;
	    else if (rockSize == 1)
		DEPTH = IMGHEIGHT * 3 / 4;
	    else
		DEPTH = IMGHEIGHT * 5 / 8;
	    HEIGHT = DEPTH;
	    WIDTH = IMGWIDTH = img.getWidth();
	    //HEIGHT = 10000;
	    //WIDTH = 53;
	}
	else {
	    HEIGHT = 100;
	    DEPTH = WIDTH = 100;
	    System.out.println("Error Loading tree image");
	}
    }
    
    public Rectangle getBounds(boolean includeZ) {
	if (includeZ){
	    return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
	}
	else {
	    return new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT / 2);
	}
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
	g.drawImage(img, tempX - IMGWIDTH / 2, tempY - IMGHEIGHT / 2,
		    tempX + IMGWIDTH / 2, tempY + IMGHEIGHT / 2,
		    0, 0, IMGWIDTH, IMGHEIGHT, null);
    }
}
