import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.File;

public class Room {
    private int WIDTH;
    private int HEIGHT;
    private BufferedImage backGround;
    private Handler handler;

    private String roomName;
    
    public Room(String roomName) {
	loadRoom(roomName);
	
	if (Game.images.getDir("background") == null){
	    System.out.println("couldn't find background");
	}
	else { 
	    backGround = Game.images.getDir("background").get(0);
	    WIDTH = backGround.getWidth();
	    HEIGHT = backGround.getHeight();
	}
	handler = new Handler();

	handler.addObject(new Bird(500, 500, 250, ID.Obstacle));
	
	Random r = new Random();
	
	for (int i = 0; i < 5; i++){
	    int j = r.nextInt(1000);
	    int k = r.nextInt(1400);
	    handler.addObject(new Tree(j, k, ID.Obstacle));
	}
	for (int i = 0; i < 10; i++){
	    int j = r.nextInt(1000);
	    int k = r.nextInt(1400);
	    handler.addObject(new Rock(j, k, ID.Obstacle));
	}
	
    }
    public void addGameObject(GameObject go) { handler.addObject(go); }
    public void loadRoom(String newRoomName){
	this.roomName = newRoomName;
	File folder = new File("../rooms/" + newRoomName + "/");
	String[] l = folder.list();
	if (l != null)
	    System.out.println(l[0]);
    }

    public void mouseClick(int x, int y) {
	
    }
    
    public void tick() {
	handler.tick();
    }
    public void render(Graphics g) {
	//GameObject temp = handler.findByID(ID.Player);
	Player temp = Game.player;
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
    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }
    public Handler getHandler() { return handler; }
}
