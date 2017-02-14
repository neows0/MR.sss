import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public abstract class GameObject {
    protected int x, y, z;
    protected ID id;
    protected int dX, dY, dZ;
    protected int HEIGHT, WIDTH, DEPTH;
    protected int IMGHEIGHT;
    protected int IMGWIDTH;
    public BufferedImage img;
    public List<BufferedImage> imgs;
    public DirLoader imgFolder;
    //Room lvl;
    protected Direction facing;
    protected int ground;
    protected Inventory inv;
    protected int feildOfVision;
    protected Routine brain;

    public GameObject(int x, int y, ID id) {
	facing = new Direction();
	inv = new Inventory(this);
	this.x = x;
	this.y = y;
	z = 0;
	this.id = id;
	ground = 0;
	feildOfVision = 300;
	//this.lvl = lvl;

	if (Game.lvl != null && id == ID.AIUnit){
	    Room board = Game.lvl;
	    brain = Routines.repeat(
		Routines.sequence(
				  Routines.moveTo(100, 200),
				  Routines.repeat(Routines.wander(board), 4),
				  Routines.moveTo(1000, 1200),
				  Routines.repeat(Routines.wander(board), 4),
				  Routines.moveTo(200, 1400),
				  Routines.repeat(Routines.wander(board), 4)),
				  -1);
	    brain.start();
	}
	//brain = Routines.moveTo(5, 10);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    //public Rectangle getBounds() { return getBounds(false); }
    //public abstract Rectangle getBounds(boolean includeZ);
    /*
    public boolean intersects(GameObject go) {
	return (go.x - go.WIDTH / 2 <= x + WIDTH / 2 &&
		go.x + go.WIDTH / 2 >= x - WIDTH / 2 &&
		go.y - go.DEPTH / 2 <= y + DEPTH / 2 &&
		go.y + go.DEPTH / 2 >= y - DEPTH / 2 &&
		((go.z <= z &&
		  go.z + go.HEIGHT >= z) ||
		 (go.z <= z + HEIGHT &&
		  go.z + go.HEIGHT >= z + HEIGHT)));
		  }*/
    public boolean intersects(GameObject go) {
	return GameObject.intersects(go, x, y, z, WIDTH, DEPTH, HEIGHT);
    }
    public static boolean intersects(GameObject go, int x, int y, int z,
				     int WIDTH, int DEPTH, int HEIGHT) {
        return (go.x - go.WIDTH / 2 <= x + WIDTH / 2 &&
		go.x + go.WIDTH / 2 >= x - WIDTH / 2 &&
		go.y - go.DEPTH / 2 <= y + DEPTH / 2 &&
		go.y + go.DEPTH / 2 >= y - DEPTH / 2 &&
		((go.z <= z &&
		  go.z + go.HEIGHT >= z) ||
		 (go.z <= z + HEIGHT &&
		  go.z + go.HEIGHT >= z + HEIGHT)));
    }

    
    
    public abstract void hit(GameObject collided);

    public void collision(Handler handler){
	collision(handler, false);
    }
    public void collision(Handler handler, boolean iZ){
	for(int i = 0; i < handler.objects.size(); i++){
	    GameObject temp = handler.objects.get(i);
	    if (temp != this){
		//if(getBounds(iZ).intersects(temp.getBounds(iZ)))
		if(intersects(temp)){
		    hit(temp);
		    temp.hit(this);
		}
	    }
	}
    }
    public boolean updated() { return (dX != 0 || dY != 0 || dZ != 0); }
    public static GameObject collision(Handler handler, int X, int Y){
	return collision(handler, X, Y, false);
    }
    
    public static GameObject collision(Handler handler,
				       int X, int Y, boolean iZ){
	for(int i = 0; i < handler.objects.size(); i++){
	    GameObject temp = handler.objects.get(i);
	    Rectangle rec = new Rectangle(temp.x - temp.IMGWIDTH / 2,
					  temp.y - temp.IMGHEIGHT / 2,
			  temp.IMGWIDTH, temp.IMGHEIGHT);
	    if(rec.contains(X, Y))
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
    public void setZ(int z) {
	this.z = z;
    }
    public void setDX(int dX){
	this.dX = dX;
    }
    public void setDY(int dY){
	this.dY = dY;
    }
    public void setDZ(int dZ){
	this.dZ = dZ;
    }
    public void setDir(Direction facing){
	this.facing = facing;
    }

    public void setGround(int ground){
	this.ground = ground;
    }
    public void setInv(Inventory inv){
	this.inv = inv;
    }
    public void setFeildOfVision(int fov){
	this.feildOfVision = fov;
    }
    public void setAI(Routine brain){
	this.brain = brain;
    }

    public Routine getAI(Routine brain){
	return brain;
    }
    public Inventory getInv(){
	return inv;
    }
    public int getGround() {
	return ground;
    }

    public Direction getDir() {
	return this.facing;
    }

     public int getX() {
	return x;
    }
    public int getY() {
	return y;
    }
    public int getZ() {
	return z;
    }
    public int getDX(){
	return dX;
    }
    public int getDY(){
	return dY;
    }
    public int getDZ(){
	return dZ;
    }
    public ID getId(){
	return id;
    }
    public int getFeildOfVision(){
	return feildOfVision;
    }
    public Edges getEdges(){
	return new Edges(false, new Cordinate(0,0), new Cordinate(0,DEPTH),
			 new Cordinate(WIDTH,DEPTH), new Cordinate(WIDTH, 0));
    }
    public Cordinate getLocation(){
	return new Cordinate(x,y,z);
    }
    public Thing getThing(){
	return new Thing(getEdges(),getLocation());
    }
}
