import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private BufferedImage shadow;
    //private int inAir;
    //public static final int maxJump = 100;
    private boolean forward = true;
    private boolean backward = false;
    private boolean left = false;
    private boolean right = false;
    
    public Player(int x, int y, ID id, Handler handler) {
	super(x, y, id, handler);
	//inAir = 0;
	img = null;
	img = Game.images.imageList.get(20);
	shadow = Game.images.imageList.get(18);
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
        if (collided.getId() == ID.Obstacle){/*
	    int tX = collided.getX();
	    int tY = collided.getY();
	    int tW = (int)collided.getBounds().getWidth();
	    int tH = (int)collided.getBounds().getHeight();
	    

	    if (tX + tW / 2 > x - (int)getBounds().getWidth() / 2
		&& tX - tW / 2 < x + (int)getBounds().getWidth() / 2)
		if (dX != 0)
		    x -= dX;
	    if (tY + tH / 2 > y - (int)getBounds().getHeight() / 2
		&& tY - tH / 2 < y + (int)getBounds().getHeight() / 2)
		y -= dY;
					  */
	    /*
	    if (collided.getX() < x)
		x = collided.getX() + (int)collided.getBounds().getWidth() / 2
		    + WIDTH / 2;
	    else
		x = collided.getX() - (int)collided.getBounds().getWidth() / 2
		    - WIDTH / 2;
	    if (collided.getY() < y)
		y = collided.getY() + (int)collided.getBounds().getHeight() / 2
		    + HEIGHT / 2;
	    else
		y = collided.getY() - (int)collided.getBounds().getHeight() / 2
		- HEIGHT / 2;*/
	    
	    //System.out.println("collided");
	}
	
    }

    @Override
    public void tick() {
	
	if (Game.input.getW() && Game.input.getS())
	    dY = 0;
	else if (Game.input.getW()){
	    dY = -5;
	}
	else if (Game.input.getS()){
	    dY = 5;
	}
	else
	    dY = 0;

	if (Game.input.getA() && Game.input.getD())
	    dX = 0;
	else if (Game.input.getA()){
	    dX = -5;
	    
	}
	else if (Game.input.getD())
	    dX = 5;
	else
	    dX = 0;

	if (Game.input.getSpace()){
	    if (z <= 0)
		dZ = 15;
	}

	if (dY > 0){
	    forward = true;
	    backward = false;
	}
	else if (dY < 0) {
	    forward = false;
	    backward = true;
	}
	else if (dX != 0) {
	    forward = false;
	    backward = false;
	}
	if (dX > 0 ) {
	    left = false;
	    right = true;
	}
	else if (dX < 0) {
	    left = true;
	    right = false;
	}
	else if (dY != 0) {
	    left = false;
	    right = false;
	}
	

	z += dZ;
	x += dX;
	y += dY;
	dZ -= Game.gravity;
	if (z <= 0){
	    z = 0;
	    dZ = 0;
	}

	x = Game.clamp(x, WIDTH / 2, Game.LVLWIDTH - WIDTH / 2);
	y = Game.clamp(y, HEIGHT / 2, Game.LVLHEIGHT - HEIGHT);
	collision();
    }

    public void render(Graphics g) {
	//g.setColor(Color.white);
	//g.drawImage(img, x, y, null);
	//g.drawImage(img, x, y, x + 32, y + 32, 0, 0, 64, 64, null);
	//g.drawImage(img, x, y, x + WIDTH, y + HEIGHT, 0, 0, WIDTH, HEIGHT, null);
	//System.out.println(Integer.toString(x) + " " + Integer.toString(y));

	//int alpha = 20; // 50% transparent
	//Color myColour = new Color(255, 255, 255, alpha);
	//g.setColor(myColour);
	//g.fillRect(x - WIDTH / 2, y, WIDTH, HEIGHT /2);
	
	
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

	BufferedImage temp = shadow;

	//AffineTransform xform = new AffineTransform();
	//xform.translate(.5*HEIGHT, .5*WIDTH);
	//xform.rotate(1.5);
	//xform.translate(-.5*WIDTH,-.5*HEIGHT);
	//temp = rotateCw(shadow);

	/*
	Graphics2D g2d = (Graphics2D) g;
	//g2d.translate(150, 0);
	//g2d.rotate(3.14/2);
	//g2d.drawImage(temp, 0, 0, 100, 100, null);
	*/

	int H = shadow.getHeight();
	int W = shadow.getWidth();
	g.drawImage(shadow, tempX + z / 2,
		    tempY + HEIGHT / 2 - H / 2, tempX + W + z / 2,
		    tempY + HEIGHT / 2 + H / 2,
		    0, 0, W, H, null);
	//g2d.rotate(-3.14/2);
	//else if (tempAir <= maxJump * 2)
	//tempAir = 0;
	
	if (left && forward)
	    temp = Game.images.imageList.get(24);
	else if (left && backward)
	    temp = Game.images.imageList.get(27);
	else if (right && forward)
	    temp = Game.images.imageList.get(25);
	else if (right && backward)
	    temp = Game.images.imageList.get(26);
	else if (left)
	    temp = Game.images.imageList.get(23);
	else if (right)
	    temp = Game.images.imageList.get(22);
	else if (forward)
	    temp = Game.images.imageList.get(20);
	else if (backward)
	    temp = Game.images.imageList.get(21);
	else
	    System.out.println("no direction");
	
	g.drawImage(temp, tempX - WIDTH / 2,
		    tempY - HEIGHT / 2 - z, tempX + WIDTH / 2,
		    tempY + HEIGHT / 2 - z, 0, 0, WIDTH, HEIGHT, null);
    }


    public static BufferedImage rotateCw( BufferedImage img )
    {
	int width  = img.getWidth();
	int height = img.getHeight();
	BufferedImage newImage = new BufferedImage(height, width, img.getType());
 
	for( int i=0 ; i < width ; i++ )
	    for( int j=0 ; j < height ; j++ )
		newImage.setRGB( height-1-j, i, img.getRGB(i,j) );
 
	return newImage;
    }
}
