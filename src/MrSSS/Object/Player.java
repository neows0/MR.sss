package MrSSS.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import MrSSS.Game;
import MrSSS.Item.Inventory;
import MrSSS.Item.Wearable.Bandana;
import MrSSS.Item.Usable.Sword;

public class Player extends GameObject {

    private Inventory inv;

    private int jumpCooldown = 0;
    
    private int ma = 0;
    private int buffer = 0;
    public Player(int x, int y, ObjectID id) {
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
	HEIGHT = imgs.get(4).getHeight();
	WIDTH = imgs.get(4).getWidth();

	inv = new Inventory();
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

    public void hit(GameObject collided){ 
        if (collided.getId() == ObjectID.Obstacle){
	    x -= dX;
	    if (collided.getBounds().intersects(this.getBounds())){
		x += dX;
		y -= dY;
		if (collided.getBounds().intersects(this.getBounds())){
		    x -= dX;
		}
	    }
		
	}
	
    }

    private void handleInput(){
	if (Game.input.getW() && Game.input.getS()){
	    if (dY > 0)
		dY -= 1;
	    else if (dY < 0)
		dY += 1;
	}
	else if (Game.input.getW()){
	    dY = -5;
	}
	else if (Game.input.getS()){
	    dY = 5;
	}
	else {
	    if (dY > 0)
		dY -= 1;
	    else if (dY < 0)
		dY += 1;
	}

	if (Game.input.getA() && Game.input.getD()){
	    if (dX > 0)
		dX -= 1;
	    else if (dX < 0)
		dX += 1; 
	}
	else if (Game.input.getA()){
	    dX = -5;
	    
	}
	else if (Game.input.getD())
	    dX = 5;
	else{
	    if (dX > 0)
		dX -= 1;
	    else if (dX < 0)
		dX += 1; 
	}

	if (Game.input.getSpace()){
	    if (z <= 0 && jumpCooldown == 0){
		dZ = 15;
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
	dZ -= Game.gravity;
	if (z <= 0){
	    z = 0;
	    dZ = 0;
	}

	x = Game.clamp(x, WIDTH / 2, Game.lvl.getWidth() - WIDTH / 2);
	y = Game.clamp(y, HEIGHT / 2, Game.lvl.getHeight() - HEIGHT);
	collision(Game.lvl.getHandler());
	changeDirection();
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
	g.drawImage(temp, plyrToScrnX() - WIDTH / 2,
		    plyrToScrnY(true) - HEIGHT / 2, plyrToScrnX() + WIDTH / 2,
		    plyrToScrnY() + HEIGHT / 2 - z, 0, 0, IMGWIDTH, IMGHEIGHT, null);
	//g.copyArea(plyrToScrnX() - WIDTH / 2 ,plyrToScrnY() - HEIGHT / 2,
	//	   WIDTH, HEIGHT, 100, 0);
	//new TextBox(g, plyrToScrnX(), plyrToScrnY(), "This is my story of how I created a text wraping ablility out of nothing.\nSuck it java!!!", 100);
	//inv.render(g);
    }

}