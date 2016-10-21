package MrSSS.Skill;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import MrSSS.Game;
import MrSSS.Object.Player;
import MrSSS.Object.GameObject;
import MrSSS.Object.ObjectID;
import MrSSS.IO.MouseInput;

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
	if (temp != null && temp.getId() == ObjectID.Obstacle){
	    System.out.println("Obstacle at " + Integer.toString(x) +
			       ":" + Integer.toString(y) );
	}
    }
    public void tick() {
    }
    public void render(Graphics g) {
    }
    
}
