import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public abstract class GameObject {
    protected int x, y, z;
    protected ID id;
    protected int dX, dY, dZ;
    protected int HEIGHT, WIDTH;
    public BufferedImage img;
    public List<BufferedImage> imgs;
    public DirLoader imgFolder;
    //Room lvl;
    protected Direction facing;

    public GameObject(int x, int y, ID id) {
	facing = new Direction();
	this.x = x;
	this.y = y;
	z = 0;
	this.id = id;
	//this.lvl = lvl;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public Rectangle getBounds() { return getBounds(false); }
    public abstract Rectangle getBounds(boolean includeZ);
    public abstract void hit(GameObject collided);

    public void collision(Handler handler){
	collision(handler, false);
    }
    public void collision(Handler handler, boolean iZ){
	for(int i = 0; i < handler.objects.size(); i++){
	    GameObject temp = handler.objects.get(i);
	    if (temp != this){
		if(getBounds(iZ).intersects(temp.getBounds(iZ)))
		    hit(temp);
	    }
	}
    }
    public static GameObject collision(Handler handler, int X, int Y){
	return collision(handler, X, Y, false);
    }
    public static GameObject collision(Handler handler,
				       int X, int Y, boolean iZ){
	for(int i = 0; i < handler.objects.size(); i++){
	    GameObject temp = handler.objects.get(i);
	    if(temp.getBounds(iZ).contains(X, Y))
		return temp;
	}
	return null;
    }
    /*
    protected void collision(){
	Handler handler = lvl.getHandler();
	for(int i = 0; i < handler.objects.size(); i++){
	    GameObject temp = handler.objects.get(i);
	    if (temp != this){
		if(getBounds().intersects(temp.getBounds()))
		    hit(temp);
	    }
	}
	}*/

    /* Setters and getters */
    public void setX(int x) {
	this.x = x;
    }
    public void setY(int y) {
	this.y = y;
    }
    public void setDX(int dX){
	this.dX = dX;
    }
    public void setDY(int dY){
	this.dY = dY;
    }
    public void setDir(Direction facing){this.facing = facing;}

    public Direction getDir() {return this.facing;}

     public int getX() {
	return x;
    }
    public int getY() {
	return y;
    }
    public int getDX(){
	return dX;
    }
    public int getDY(){
	return dY;
    }
    public ID getId(){
	return id;
    }
}
