import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Menu {

    private Game.STATE nextState;
    private Button start;

    
    public void mouseClick(int x, int y) {
	if (start.contains(x,y)) {
	    nextState = Game.STATE.Game;
	}
	
    }
    
    public Menu(){
	nextState = Game.STATE.Menu;
	start = new Button(Game.SCRNWIDTH / 2 - 50, Game.SCRNHEIGHT / 2 - 25,
			   "start", 100, 50);
        
    }

    public Game.STATE getNextState() { return nextState; }
    
    public void tick(){
    }

    public void render(Graphics g){
	Font fnt = new Font("arial", 1, 50);
	Font fnt2 = new Font("arial", 1, 30);

	/*
	g.setFont(fnt);
	g.setColor(Color.blue);
	g.drawString("Menu", Game.WIDTH / 2, 50);

	g.setFont(fnt2);
	g.drawRect(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 32 + 64, 200, 64);
	g.drawString("Play", Game.WIDTH / 2, Game.HEIGHT / 2 - 32 - 10);
	*/

	
	g.drawRect(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 32 - 64, 200, 64);
	start.render(g);
    }
}
