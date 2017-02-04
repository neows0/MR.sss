import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class Menu {

    private Game.STATE nextState;
    private Button start;

    private BufferedImage background;
    
    public void mouseClick(int x, int y) {
	if (start.contains(x,y)) {
	    nextState = Game.STATE.Game;
	}
	
    }
    
    public Menu(){
	nextState = Game.STATE.Menu;
	start = new Button(Game.SCRNWIDTH / 2 - 50, Game.SCRNHEIGHT / 2 - 25,
			   "start", 100, 50);
        background = Game.images.getDir("background").get(1);
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
	if (background != null) {
	    g.drawImage(background,0,0,Game.SCRNWIDTH,Game.SCRNHEIGHT,null);
	}

	
	//g.drawRect(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 32 - 64, 200, 64);
	start.render(g);
    }
}
