import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{

    Game game;
    
    public Menu(Game game){
	this.game = game;
    }

    public Menu(){
    }
    
    public void mousePressed(MouseEvent e){
	int mx = e.getX();
	int my = e.getY();
	System.out.println("mouse clicked");
	//210 150 200 64
	if(mouseOver(mx, my, Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 32 - 64,
		     200, 64)){
	    game.gameState = Game.STATE.Game;
	    System.out.println("box pressed");
	}
    }

    public void mouseReleased(MouseEvent e){
	
    }

    private boolean mouseOver(int mx, int my, int x, int y,
			      int width, int height){
	if (mx > x && mx < x + width){
	    if (my > y && my < y + height) {
		return true;
	    }
	    else
		return false;
	}
	else
	    return false;
    }

    public void tick(){
    }

    public void render(Graphics g){
	Font fnt = new Font("arial", 1, 50);
	Font fnt2 = new Font("arial", 1, 30);

	g.setFont(fnt);
	g.setColor(Color.blue);
	g.drawString("Menu", Game.WIDTH / 2, 50);

	g.setFont(fnt2);
	g.drawRect(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 32 + 64, 200, 64);
	g.drawString("Play", Game.WIDTH / 2, Game.HEIGHT / 2 - 32 - 10);
	
	g.drawRect(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 32 - 64, 200, 64);
    }
}
