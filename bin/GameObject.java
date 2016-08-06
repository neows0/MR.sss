import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int dX, dY;
    protected int HEIGHT, WIDTH;
    Handler handler;

    public GameObject(int x, int y, ID id, Handler handler) {
	this.x = x;
	this.y = y;
	this.id = id;
	this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract void hit(GameObject collided);

    protected void collision(){
	for(int i = 0; i < handler.objects.size(); i++){
	    GameObject temp = handler.objects.get(i);
	    if (temp != this){
		if(getBounds().intersects(temp.getBounds()))
		    hit(temp);
	    }
	}
    }

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
