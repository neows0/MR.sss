import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Human extends GameObject {

    

    private int jumpCooldown = 0;
    
    private int ma = 0;
    private int buffer = 0;
    private int moe = 5; //margin of error for the z componet
    private int hairStyle = 1;
    
    public Human(int x, int y, ID id) {
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
	
	HEIGHT = IMGHEIGHT * 3 / 5;//imgs.get(4).getHeight();
	WIDTH = IMGWIDTH * 4 / 5;//imgs.get(4).getWidth();
	DEPTH = IMGWIDTH / 2;
	
	inv = new Inventory((GameObject)this);
	inv.addItem(new Bandana(this));
	//inv.equipItem(0);
	inv.addItem(new Sword(this));

    }

    

    public Rectangle getBounds(boolean includeZ){
	if (includeZ)
	    return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
	else
	    return new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT / 2);
    }

    public void hit(GameObject col){ 
        if (col.getId() != ID.Item){
	    
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

   

    @Override
    public void tick() {
		
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

   
    public BufferedImage getImage() {

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

	int [] cord = { 0, 0 };
	Game.screenLoc(cord);
	int tempX = x - cord[0];
	int tempY = y - cord[1];
	int H = imgs.get(8).getHeight();
	int W = imgs.get(8).getWidth();
	g.drawImage(imgs.get(8), tempX + z / 2,
		    tempY + IMGHEIGHT / 2 - H / 2,
		    tempX + W + z / 2,
		    tempY + IMGHEIGHT / 2 + H / 2,
		    0, 0, W, H, null);
	
	g.drawImage(getImage(), tempX - IMGWIDTH / 2, tempY - IMGHEIGHT / 2 - z,
		    tempX + IMGWIDTH / 2, tempY + IMGHEIGHT / 2 - z,
		    0, 0, IMGWIDTH, IMGHEIGHT, null);
	/*
	int H = imgs.get(8).getHeight();xs
	int W = imgs.get(8).getWidth();
	g.drawImage(imgs.get(8), plyrToScrnX() + z / 2 - ground / 2,
		    plyrToScrnY() + HEIGHT / 2 - H / 2 - ground,
		    plyrToScrnX() + W + z / 2 - ground / 2,
		    plyrToScrnY() + HEIGHT / 2 + H / 2 - ground,
		    0, 0, W, H, null);

	temp = getImage();
	g.drawImage(temp, plyrToScrnX() - IMGWIDTH / 2,
		    plyrToScrnY(true) - IMGHEIGHT / 2, plyrToScrnX() + IMGWIDTH / 2,
		    plyrToScrnY() + IMGHEIGHT / 2 - z, 0, 0, IMGWIDTH, IMGHEIGHT, null);*/
	//g.copyArea(plyrToScrnX() - WIDTH / 2 ,plyrToScrnY() - HEIGHT / 2,
	//	   WIDTH, HEIGHT, 100, 0);
	//new TextBox(g, plyrToScrnX(), plyrToScrnY(), "This is my story of how I created a text wraping ablility out of nothing.\nSuck it java!!!", 100);
	//inv.render(g);

	//new TextBox(x,y,"Hello from my text box", 100).render(g);
	inv.renderEquip(g);
    }

}
