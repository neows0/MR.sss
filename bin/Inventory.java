import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Inventory {
    private List<Item> items;
    private int size;
    private static final int invY = 450;
    private List<BufferedImage> imgs;
    public Inventory(){
	items = new ArrayList<Item>();
	imgs = Game.images.getDir("toolbar");
    }
    public void addItem(Item item) {items.add(item);}
    public void equipItem(int i) {
	if (items.size() > i){
	    items.get(i).equip();
	}
    }
    public Item getItem(int tX, int tY){
	int i = Game.WIDTH + 27;
	
	if(tX > i && tX < i + 6 * 32 - 2){
	    if (tY > invY && tY < invY + 9* 32 - 2){
		int tempX = tX - i;
		int tempY = tY - invY;
		tempX = tempX / 32;
		tempY = tempY / 32;
		if (items.size() > tempX + tempY * 6){
		    return items.get(tempX + tempY * 6);
		}
	    }
	        
	}
	return null;
    }
    public void tick() {
    }
    public void render(Graphics g) {
	//System.out.println(Integer.toString(imgs.size()));
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
			    invY + i * 32, null);
		if (i * 9 + j < items.size()){
		    if (items.get(i * 6 + j).isEquipped()){
			g.drawImage(imgs.get(2), Game.WIDTH + 27 + j * 32,
			    invY + i * 32, null);
		    }
		    items.get(i * 6 + j).invRender(g, 27 + j * 32 + Game.WIDTH,
						   invY + i * 32, 26, 26);
		}
	    }
	}
	
	
	for(int i = 0; i < items.size(); i++){
	    items.get(i).render(g);
	}
    }
    
}
