import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private Inventory inv;

    private int jumpCooldown = 0;
    
    private int ma = 0;
    private int buffer = 0;
    private int moe = 5; //margin of error for the z componet
    private int hairStyle = 1;
    
    public Player(int x, int y, ID id) {
	super(x, y, id);
	//inAir = 0;
	img = null;
	//img = Game.images.imageList.get(20);
	//shadow = Game.images.imageList.get(18);
	imgFolder = Game.images.getDirLoader("player");
	imgs = Game.images.getDir("player");
	if (imgs == null)
	    System.out.println("Error");
	IMGHEIGHT = 61; //imgs.get(4).getHeight(); //61
	IMGWIDTH = 32; //imgs.get(4).getWidth();   //32
	
	//System.out.println(Integer.toString(IMGHEIGHT));
	//System.out.println(Integer.toString(IMGWIDTH));
	
	HEIGHT = IMGHEIGHT;//imgs.get(4).getHeight();
	WIDTH = IMGWIDTH;//imgs.get(4).getWidth();
	DEPTH = WIDTH;
	
	inv = new Inventory((GameObject)this);
	inv.addItem(new Bandana(this));
	//inv.equipItem(0);
	inv.addItem(new Sword(this));

    }

    public Inventory getInv() { return inv; }

    public Rectangle getBounds(boolean includeZ){
	if (includeZ)
	    return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
	else
	    return new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT / 2);
    }

    public void hit(GameObject col){ 
        if (col.getId() == ID.Obstacle){
	    
	    int oldX = x - dX;
	    int oldY = y - dY;
	    int oldZ = z - dZ;
	    if ((x + WIDTH / 2 > col.x - col.WIDTH / 2 &&
		 x - WIDTH /2 < col.x + col.WIDTH / 2) &&
		!(oldX + WIDTH / 2 > col.x - col.WIDTH / 2 &&
		  oldX - WIDTH /2 < col.x + col.WIDTH / 2))
		x = oldX;
	    if ((y + DEPTH / 2 > col.y - col.DEPTH / 2 &&
		 y - DEPTH /2 < col.y + col.DEPTH / 2) &&
		!(oldY + DEPTH / 2 > col.y - col.DEPTH / 2 &&
		  oldY - DEPTH /2 < col.y + col.DEPTH / 2))
		y = oldY;
	    if ((z + HEIGHT > col.z && z < col.z + col.HEIGHT) &&
		!(oldZ + HEIGHT > col.z &&
		  oldZ < col.z + col.HEIGHT)) {

		if (z < oldZ) {
		    ground = oldZ;
		}
		else {
		    dZ = 0;
		}
		z = oldZ;
	    }	
	}
	
    }

    private void handleInput(){
	boolean w = Game.input.isPressed("w");
	boolean s = Game.input.isPressed("s");
	boolean a = Game.input.isPressed("a");
	boolean d = Game.input.isPressed("d");
	int angle = facing.getAngle();
	if (a == d && w == s) {
	    if (dY != 0)
		dY -= dY / Math.abs(dY);
	    if (dX != 0)
		dX -= dX / Math.abs(dX);
	}
	else if (a == d) { //w != s
	    if (dX != 0)
		dX -= dX / Math.abs(dX);
	    
	    if (w) {
		dY = -5;
		angle = 90;
	    }
	    else {
		dY = 5;
		angle = 270;
	    }
	}
	else if (w == s) { //a != d
	    if (dY != 0) 
		dY -= dY / Math.abs(dY);
	    
	    if (a) {
		dX = -5;
		angle = 180;
	    }
	    else {
		dX = 5;
		angle = 0;
	    }
	}
	else if (w && a){
	    dY = -5;
	    dX = -5;
	    angle = 135;
	}
	else if (w && d) {
	    dY = -5;
	    dX = 5;
	    angle = 45;
	}
	else if (s && a) {
	    dY = 5;
	    dX = -5;
	    angle = 225;
	}
	else if (s && d) {
	    dY = 5;
	    dX = 5;
	    angle = 315;
	}
	else {
	    if (dY != 0)
		dY -= dY / Math.abs(dY);
	    if (dX != 0)
		dX -= dX / Math.abs(dX);
	}

	facing.moveTowards(angle, 10);
	

	if (Game.input.getSpace()){
	    if (z <= ground && jumpCooldown == 0){
		//dZ = 15;
		
		dZ = 30;
		jumpCooldown = 20;
	    }
	}

	if (jumpCooldown != 0){
	    jumpCooldown--;
	}
    }

    @Override
    public void tick() {
	
	handleInput();

	
	
	z += dZ;
	x += dX;
	y += dY;
	

	x = Game.clamp(x, WIDTH / 2, Game.lvl.getWidth() - WIDTH / 2);
	y = Game.clamp(y, HEIGHT / 2, Game.lvl.getHeight() - HEIGHT);
	ground = 0;
	collision(Game.lvl.getHandler());

	if (z > ground){
	    dZ -= Game.gravity;
	}
	
	if (z <= 0){
	    z = 0;
	    dZ = 0;
	}
    }

    public int plyrToScrnX(){
	int tempX = Game.WIDTH / 2;
	if (x - Game.WIDTH / 2 < 0)
	    tempX = x;
	else if (x + Game.WIDTH / 2 > Game.lvl.getWidth() )
	    tempX = x - (Game.lvl.getWidth() - Game.WIDTH);
	return tempX;
    }
    
    public int plyrToScrnY(){
	int tempY = Game.HEIGHT / 2;
	if (y < Game.HEIGHT / 2)
	    tempY = y;
	else if (y > Game.lvl.getHeight() - Game.HEIGHT / 2)
	    tempY = y - (Game.lvl.getHeight() - Game.HEIGHT);
	return tempY;
    }
    
    public int plyrToScrnY(boolean withZ){
	if (withZ)
	    return plyrToScrnY() - z;
	else
	    return plyrToScrnY();
    }

    public BufferedImage getImage() {
	/*BufferedImage temp = null;
	if (facing.getLeft() && facing.getForward())
	    temp = imgs.get(4);
	else if (facing.getLeft() && facing.getBackward())
	    temp = imgs.get(7);
	else if (facing.getRight() && facing.getForward())
	    temp = imgs.get(5);
	else if (facing.getRight() && facing.getBackward())
	    temp = imgs.get(6);
	else if (facing.getLeft())
	    temp = imgs.get(3);
	else if (facing.getRight())
	    temp = imgs.get(2);
	else if (facing.getForward()){
	    try {
		BufferedImage tmp1 = imgFolder.getDir("Torso").get(0);
		BufferedImage tmp2 = imgFolder.getDir("Head").get(0);
		//int w = tmp1.getWidth() + tmp2.getWidth();
		int w = Math.max(tmp1.getWidth(), tmp2.getWidth());
		int h = tmp1.getHeight() + tmp2.getHeight();
		//Math.max(tmp1.getHeight(), tmp2.getHeight());
		BufferedImage tmp3 =
		    new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		Graphics gr = tmp3.getGraphics();
		gr.drawImage(tmp1, 0, tmp2.getHeight() - 3, null);
		gr.drawImage(tmp2, (tmp1.getWidth() - tmp2.getWidth()) / 2, 0,
			     null);
		tmp2 = imgFolder.getDir("Hair").get(0);
	        gr.drawImage(tmp2, (tmp1.getWidth() - tmp2.getWidth()) / 2, 0,
			     null);
		//g.drawImage(tmp1, 0, 100, null);
		//g.drawImage(tmp2, w, 100, null);
		//g.drawImage(tmp3, w * 2, 100, null);
		temp = tmp3;
	    } catch (Exception e) {
		temp = imgs.get(0);
	    }
	}
	else if (facing.getBackward())
	    temp = imgs.get(1);
	else
	    System.out.println("no direction");
	return temp;
				  */
	BufferedImage tmp2 = getHeadImg();
	BufferedImage tmp1 = getBodyImg();
	int w = Math.max(tmp1.getWidth(), tmp2.getWidth());
	int h = tmp1.getHeight() + tmp2.getHeight();
	BufferedImage tmp3 =
		    new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
	Graphics gr = tmp3.getGraphics();
	gr.drawImage(tmp1, 0, tmp2.getHeight() - 2, null);
	gr.drawImage(tmp2, (tmp1.getWidth() - tmp2.getWidth()) / 2, 0,
		     null);
	
	return tmp3;
    }
    
    public BufferedImage getHeadImg() {
	int angle = facing.getSimpleAngle();
	
	BufferedImage tmp1 = imgFolder.getDir("Head").get(angle / 45);
	BufferedImage tmp2 = imgFolder.getDir("Hair").get(angle / 45);
	int w = Math.max(tmp1.getWidth(), tmp2.getWidth());
	int h = Math.max(tmp1.getHeight(), tmp2.getHeight());
	BufferedImage tmp3 =
		    new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
	Graphics gr = tmp3.getGraphics();
	gr.drawImage(tmp1, 0, 0, null);
	if (hairStyle != 0)
	    gr.drawImage(tmp2, 0, 0, null);
	return tmp3;
    }

    public BufferedImage getBodyImg() {
	int angle = facing.getSimpleAngle();
	BufferedImage tmp1 = imgFolder.getDir("Torso").get(angle / 45);
	return tmp1;
    }


    
    public void render(Graphics g) {
	BufferedImage temp = imgs.get(8);

	
	int H = imgs.get(8).getHeight();
	int W = imgs.get(8).getWidth();
	g.drawImage(imgs.get(8), plyrToScrnX() + z / 2 - ground / 2,
		    plyrToScrnY() + HEIGHT / 2 - H / 2 - ground,
		    plyrToScrnX() + W + z / 2 - ground / 2,
		    plyrToScrnY() + HEIGHT / 2 + H / 2 - ground,
		    0, 0, W, H, null);

	temp = getImage();
	g.drawImage(temp, plyrToScrnX() - IMGWIDTH / 2,
		    plyrToScrnY(true) - IMGHEIGHT / 2, plyrToScrnX() + IMGWIDTH / 2,
		    plyrToScrnY() + IMGHEIGHT / 2 - z, 0, 0, IMGWIDTH, IMGHEIGHT, null);
	//g.copyArea(plyrToScrnX() - WIDTH / 2 ,plyrToScrnY() - HEIGHT / 2,
	//	   WIDTH, HEIGHT, 100, 0);
	//new TextBox(g, plyrToScrnX(), plyrToScrnY(), "This is my story of how I created a text wraping ablility out of nothing.\nSuck it java!!!", 100);
	//inv.render(g);

	//new TextBox(x,y,"Hello from my text box", 100).render(g);
	inv.renderEquip(g);
    }

}
