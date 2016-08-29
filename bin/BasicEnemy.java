
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class BasicEnemy extends GameObject {

    public BasicEnemy(int x, int y, ID id) {
	super(x, y, id);

	dX = 5;
	dY = 5;
    }

    public Rectangle getBounds(){
	return new Rectangle(x, y, 16, 16);
    }

    public void hit(GameObject collided){
	
    }

    @Override
    public void tick() {
	x += dX;
	y += dY;

	if (y <= 0 || y >= Game.HEIGHT - 32) dY *= -1;
	if (x <= 0 || x >= Game.WIDTH - 32) dX *= -1;
    }

    @Override
    public void render(Graphics g) {
	//Graphics2D g2d = (Graphics2D) g;
	//g.setColor(Color.green);
	//g2d.draw(getBounds());

	
	g.setColor(Color.red);
	g.fillRect(x, y, 16, 16);
    }
}
