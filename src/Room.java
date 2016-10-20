import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Room {
    private int WIDTH;
    private int HEIGHT;
    private BufferedImage backGround;
    private Handler handler;
    private Player player;

    public Room(Player player) {
	this.player = player;
	if (Game.images.getDir("background") == null){
	    System.out.println("couldn't find background");
	}
	else { 
	    backGround = Game.images.getDir("background").get(0);
	    WIDTH = backGround.getWidth();
	    HEIGHT = backGround.getHeight();
	}
	handler = new Handler();
	handler.addObject(player);

	Random r = new Random();
	for (int i = 0; i < 50; i++){
	    int j = r.nextInt(1000);
	    int k = r.nextInt(1400);
	    if (!player.getBounds().intersects(j - 100, k - 100, 200, 200))
		handler.addObject(new Tree(j, k, ID.Obstacle));
	    else {
		i--;
	    }
	}
	
    }
    public void tick() {
	handler.tick();
    }
    public void render(Graphics g) {
	GameObject temp = handler.findByID(ID.Player);
	    if (temp == null){
		g.drawImage(backGround, 0, 0, Game.WIDTH, Game.HEIGHT,
			    0, 0, Game.WIDTH, Game.HEIGHT, null);
		System.out.println("player in null");
	    }
	    else {
	    
		int [] array = { 0, 0 };
		Game.screenLoc(array);
	    
		int x = array[0];
		int y = array[1];
		
		g.drawImage(backGround, 0, 0, Game.WIDTH, Game.HEIGHT,
			    x, y, Game.WIDTH + x, Game.HEIGHT + y, null);
	    }
	
	    handler.render(g);
    }
    public void load() {
    }
    public Player getPlayer() { return player; }
    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }
    public Handler getHandler() { return handler; }
}
