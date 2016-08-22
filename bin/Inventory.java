import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Inventory {
    private List<Item> items;
    private int size;
    private List<BufferedImage> imgs;
    public Inventory(){
	items = new ArrayList<Item>();
	imgs = Game.images.getDir("inventory");
    }
    public void addItem(Item item) {items.add(item);}
    public void equipItem(int i) {
	if (items.size() > i){
	    items.get(i).equip();
	}
    }
    public void tick() {
    }
    public void render(Graphics g) {
	int H = imgs.get(0).getHeight();
	int W = imgs.get(0).getWidth();
	//g.drawImage(imgs.get(0), Game.WIDTH, 0, null);
	
	g.drawImage(imgs.get(0), Game.WIDTH, 0,
		    Game.SCRNWIDTH - 5, Game.HEIGHT - 29,
		    0, 0, W, H, null);
	H = imgs.get(1).getHeight();
	W = imgs.get(1).getWidth();
	for (int i = 0; i < 9; i++){
	    for (int j = 0; j < 6; j++){
		g.drawImage(imgs.get(1), Game.WIDTH + 27 + j * 32,
			    406 + i * 32, null);
		if (i * 9 + j < items.size()){
		    if (items.get(i * 6 + j).isEquipped()){
			g.drawImage(imgs.get(2), Game.WIDTH + 27 + j * 32,
			    406 + i * 32, null);
		    }
		    items.get(i * 6 + j).invRender(g, 27 + j + Game.WIDTH,
						   406 + i, 26, 26);
		}
	    }
	}
	
	
	for(int i = 0; i < items.size(); i++){
	    items.get(i).render(g);
	}
    }
    
}
