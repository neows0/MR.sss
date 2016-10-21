package MrSSS.IO.GUI;

import java.awt.Graphics;
import java.util.List;
import java.awt.image.BufferedImage;

import MrSSS.IO.Loader.DirLoader;
import MrSSS.IO.MouseInput;
import MrSSS.Object.Player;
import MrSSS.Item.Item;
import MrSSS.Game;

public class Toolbar {
    
    private DirLoader imgFolder;
    private Player thisPlayer;
    private int toolState;
    private final static int INV = 0;
    private final static int SKILLS = 1;
    public Toolbar(Player player) {
		imgFolder = Game.images.getDirLoader("toolbar");
		thisPlayer = player;
		toolState = INV;
    }
	
    public void input(MouseInput mI) {
		if (toolState == INV) {
			int tX = mI.getXi();
			int tY = mI.getYi();
			Item temp = thisPlayer.getInv().getItem(tX, tY);
			if (temp != null){
				if (!temp.isEquipped()) {
					temp.equip();
				}
				else {
					temp.unequip();
				}
			}
		}
		else if (toolState == SKILLS) {}
    }
	
    public void tick(){
		if (toolState == INV) {
			thisPlayer.getInv().tick();
		}
		else if (toolState == SKILLS) {
		}
    }
	
    public void render(Graphics g){
		if (toolState == INV){
			thisPlayer.getInv().render(g);
		}
		else if (toolState == SKILLS) {
		}
    }
}
