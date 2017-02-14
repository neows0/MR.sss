import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player {

    private int jumpCooldown = 0;
    
    private int ma = 0;
    private int buffer = 0;
    private int moe = 5; //margin of error for the z componet
    private int hairStyle = 1;

    private GameObject possessed;
    
    public Player() {
	possessed = new Human(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player);
    }

    public GameObject getEntity() { return possessed; }
    public void setEntity(GameObject go) { possessed = go; }

    public void handleInput(){
	boolean w = Game.input.isPressed("w");
	boolean s = Game.input.isPressed("s");
	boolean a = Game.input.isPressed("a");
	boolean d = Game.input.isPressed("d");
	int dX = possessed.getDX();
	int dY = possessed.getDY();
	int dZ = possessed.getDZ();
	int ground = possessed.getGround();
	int z = possessed.getZ();
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

	    }
	    else {
		dY = 5;

	    }
	}
	else if (w == s) { //a != d
	    if (dY != 0) 
		dY -= dY / Math.abs(dY);
	    
	    if (a) {
		dX = -5;

	    }
	    else {
		dX = 5;

	    }
	}
	else if (w && a){
	    dY = -5;
	    dX = -5;

	}
	else if (w && d) {
	    dY = -5;
	    dX = 5;

	}
	else if (s && a) {
	    dY = 5;
	    dX = -5;

	}
	else if (s && d) {
	    dY = 5;
	    dX = 5;

	}
	else {
	    if (dY != 0)
		dY -= dY / Math.abs(dY);
	    if (dX != 0)
		dX -= dX / Math.abs(dX);
	}
	

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

	possessed.setDX(dX);
        possessed.setDY(dY);
	possessed.setDZ(dZ);
	possessed.setGround(ground);
	possessed.setZ(z);
	
    }

    /*
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
    */
    

}
