
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;

/* I don't really care about this, I stopped the 
** youtube video: Let's Build a Game #6
*/

public class Trail extends GameObject{

    private float alpha = 1;
    private Color color;
    
    public Trail(int x, int y, ID id, Handler handler, Color color) {
	super(x, y, id, handler);
	this.color = color;
    }

    public void tick() {
	
    }

    public void render(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setComposite(makeTransparent(alpha));
	g.setColor(color);
	g.fillRect(x, y, 16, 16);
    }

    private AlphaComposite makeTransparent(float alpha){
	int type = AlphaComposite.SRC_OVER;
	return(AlphaComposite.getInstance(type, alpha));
    }

    public Rectangle getBounds() {
	return null;
    }

    public void hit(GameObject collided){
	
    }
}
