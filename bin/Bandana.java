import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bandana extends Item {
    public Bandana(Player owner) {
	super(ITMID.Bandana, owner);
	imgs = Game.images.getDir("bandana");
	if (imgs == null)
	    System.out.println("Error");
	IMGHEIGHT = HEIGHT = imgs.get(0).getHeight();//12
	IMGWIDTH = WIDTH = imgs.get(0).getWidth();//28
	setRelativeX(0);
	setRelativeY(23);
	canEquipped = true;
    }
    public Bandana(int x, int y, boolean onGround){
	super(x, y, ITMID.Bandana, onGround);
	imgs = Game.images.getDir("bandana");
	if (imgs == null)
	    System.out.println("Error");
	IMGHEIGHT = HEIGHT = imgs.get(0).getHeight();
	IMGWIDTH = WIDTH = imgs.get(0).getWidth();
	setRelativeX(0);
	setRelativeY(23);
    }

    public void use(){
    }
    public void tick(){
    }
    public void invRender(Graphics g, int x, int y, int w, int h){
        g.drawImage(imgs.get(8), x + 3, y + 7, null);
    }
    public void render(Graphics g){
	
	if (isEquipped()){
	    BufferedImage temp = imgs.get(3);
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
	    else if (facing.getForward())
		temp = imgs.get(0);
	    else if (facing.getBackward())
		temp = imgs.get(1);
	    else
		System.out.println("no direction");
	
	    g.drawImage(temp, owner.plyrToScrnX() - IMGWIDTH / 2 + getRelativeX(),
			owner.plyrToScrnY(true) - IMGHEIGHT / 2 - getRelativeY(),
			owner.plyrToScrnX() + IMGWIDTH / 2 + getRelativeX(),
			owner.plyrToScrnY(true) + IMGHEIGHT / 2 - getRelativeY(),
			0, 0, IMGWIDTH, IMGHEIGHT, null);
	}
    }
    public Rectangle getBounds(boolean includeZ){
	return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
    }
    public void hit(GameObject collided){
    }
}
