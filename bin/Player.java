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
	IMGHEIGHT = imgs.get(4).getHeight();
	IMGWIDTH = imgs.get(4).getWidth();
	
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
	    /*
	    if (collided.z <= z && collided.z + HEIGHT >= z){
		x -= dX;
		if (collided.intersects(this)){
		    x += dX;
		    y -= dY;
		    if (collided.intersects(this)){
			x -= dX;
		    
		    }
		}
		}*/
	    /*
	    
	    boolean isDZ = false;
	    boolean isDX = false;
	    boolean isDY = false;
	    boolean isYX = false;
	    boolean isXZ = false;
	    boolean isZY = false;
	    //is it just Z
	    x -= dX;
	    y -= dY; 
	    if (collided.intersects(this)) {
		isDZ = true;
	    }
   	    //is it just x
	    x += dX;
	    z -= dZ;
	    if (collided.intersects(this)) {
		isDX = true;
	    }
	     //is it just y
	    y += dY;
	    x -= dX;
	    if (collided.intersects(this)) {
		isDY = true;
	    }
	    //is it YX
	    x += dX;
	    if (collided.intersects(this)) {
		isYX = true;
	    }
	    //is it XZ
	    y -= dY;
	    z += dZ;
	    if (collided.intersects(this)) {
		isXZ = true;
	    }
	    //is it ZY
	    x -= dX;
	    y += dY;
	    if (collided.intersects(this)) {
		isZY = true;
	    }

	    x += dX;
	    
	  
	    
	    if (isDZ) {
		z -= dZ;
		ground = z;
	    }
	    if (isDX)
	    x -= dX;
	    if (isDY) {
	    y -= dY;
	    }*/
	    /*
	      x -= dX;
	      y -= dY;
	      z -= dZ;
	      if (!((x < collided.x - collided.WIDTH / 2 &&
	      x + dX > collided.x - collided.WIDTH / 2) ||
	      (x > collided.x + collided.WIDTH / 2 &&
	      x + dX < collided.x + collided.WIDTH / 2)))
	      x += dX;
	      if (!((y < collided.y - collided.DEPTH / 2 &&
	      y + dY > collided.y - collided.DEPTH / 2) ||
	      (y > collided.y + collided.DEPTH / 2 &&
	      y + dY < collided.y + collided.DEPTH / 2)))
	      y += dY;
	      if (!(z <= collided.z + collided.HEIGHT && z >= collided.z))
	      z += dZ;
	    */
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
	    if ((z + HEIGHT / 2 > col.z - col.HEIGHT / 2 &&
		 z - HEIGHT /2 < col.z + col.HEIGHT / 2) &&
		!(oldZ + HEIGHT / 2 > col.z - col.HEIGHT / 2 &&
		  oldZ - HEIGHT /2 < col.z + col.HEIGHT / 2)) {
		z = oldZ;
		ground = z;
	    }

		
	}
	
    }

    private void handleInput(){
	if (Game.input.isPressed("w") && Game.input.isPressed("s")){
	    if (dY > 0)
		dY -= 1;
	    else if (dY < 0)
		dY += 1;
	}
	else if (Game.input.isPressed("w")){
	    dY = -5;
	}
	else if (Game.input.isPressed("s")){
	    dY = 5;
	}
	else {
	    if (dY > 0)
		dY -= 1;
	    else if (dY < 0)
		dY += 1;
	}

	if (Game.input.isPressed("a") && Game.input.isPressed("d")){
	    if (dX > 0)
		dX -= 1;
	    else if (dX < 0)
		dX += 1; 
	}
	else if (Game.input.isPressed("a")){
	    dX = -5;
	    
	}
	else if (Game.input.isPressed("d"))
	    dX = 5;
	else{
	    if (dX > 0)
		dX -= 1;
	    else if (dX < 0)
		dX += 1; 
	}

	if (Game.input.getSpace()){
	    if (z <= ground && jumpCooldown == 0){
		//dZ = 15;
		
		dZ = 30;
		jumpCooldown = 20;
	    }
	}

	if (Game.mouse.actionTaken == false){
	//dX = ((Game.mouse.getXf() - Game.mouse.getXi()) * 100) / Game.WIDTH;
	//dY = ((Game.mouse.getYf() - Game.mouse.getYi()) * 100) / Game.HEIGHT;
	    Game.toolBar.input(Game.mouse);
	    /*
	    int tX = Game.mouse.getXi();
	    int tY = Game.mouse.getYi();
	    Item temp = inv.getItem(tX, tY);
	    if (temp != null){
		if (!temp.isEquipped()){
		    temp.equip();
		}
		else{
		    temp.unequip();
		}
		}*/
	    Game.mouse.actionTaken = true;
	}

	if (jumpCooldown != 0){
	    jumpCooldown--;
	}
    }

    

    private void changeDirection(){
	if (dY > 0){
	    facing.setForward(true);
	    facing.setBackward(false);
	}
	else if (dY < 0) {
	    facing.setForward(false);
	    facing.setBackward(true);
	}
	else if (dX != 0) {
	    facing.setForward(false);
	    facing.setBackward(false);
	}
	if (dX > 0 ) {
	    facing.setLeft(false);
	    facing.setRight(true);
	}
	else if (dX < 0) {
	    facing.setLeft(true);
	    facing.setRight(false);
	}
	else if (dY != 0) {
	    facing.setLeft(false);
	    facing.setRight(false);
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
	changeDirection();
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

    public void render(Graphics g) {
	BufferedImage temp = imgs.get(8);

	int H = imgs.get(8).getHeight();
	int W = imgs.get(8).getWidth();
	g.drawImage(imgs.get(8), plyrToScrnX() + z / 2,
		    plyrToScrnY() + HEIGHT / 2 - H / 2,
		    plyrToScrnX() + W + z / 2,
		    plyrToScrnY() + HEIGHT / 2 + H / 2,
		    0, 0, W, H, null);

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
	    if (dY == 0){
		temp = imgs.get(0);
	    }
	    else {
		int i = 0;
		// % 8 == 0:0 1:9 2:10 3:9 4:0 
		// % 8 == 5:11 6:12 7:11
		if (ma % 8 == 0)
		    i = 0;
		else if (ma % 8 == 1)
		    i = 9;
		else if (ma % 8 == 2)
		    i = 10;
		else if (ma % 8 == 3)
		    i = 9;
		else if (ma % 8 == 4)
		    i = 0;
		else if (ma % 8 == 5)
		    i = 11;
		else if (ma % 8 == 6)
		    i = 12;
		else
		    i = 11;
		temp = imgs.get(i);
		if (buffer % 20 / dY == 0)
		    ma++;
		buffer++;

	    }
	}
	else if (facing.getBackward())
	    temp = imgs.get(1);
	else
	    System.out.println("no direction");
	g.drawImage(temp, plyrToScrnX() - IMGWIDTH / 2,
		    plyrToScrnY(true) - IMGHEIGHT / 2, plyrToScrnX() + IMGWIDTH / 2,
		    plyrToScrnY() + IMGHEIGHT / 2 - z, 0, 0, IMGWIDTH, IMGHEIGHT, null);
	//g.copyArea(plyrToScrnX() - WIDTH / 2 ,plyrToScrnY() - HEIGHT / 2,
	//	   WIDTH, HEIGHT, 100, 0);
	//new TextBox(g, plyrToScrnX(), plyrToScrnY(), "This is my story of how I created a text wraping ablility out of nothing.\nSuck it java!!!", 100);
	//inv.render(g);

	//new TextBox(x,y,"Hello from my text box", 100).render(g);
    }

}
