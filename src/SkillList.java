import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SkillList {
    Player owner;
    public SkillList(Player player){
	owner = player;
    }
    private void handleInput(MouseInput mi){
	Player t  = owner;
	int x = mi.getX() - t.plyrToScrnX() + t.getX();
	int y = mi.getY() - t.plyrToScrnY() + t.getY();
	GameObject temp = GameObject.collision(Game.lvl.getHandler(),
					       x, y, true);
	if (temp != null && temp.getId() == ID.Obstacle){
	    System.out.println("Obstacle at " + Integer.toString(x) +
			       ":" + Integer.toString(y) );
	}
    }
    public void tick() {
    }
    public void render(Graphics g) {
    }
    
}
