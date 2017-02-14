import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Dimension;

/*
 * BufferedImage.getSubimage(int x, int y, int w, int h);
 */

public class Game extends Canvas implements Runnable, MouseWatcher{

    private static final long serialVersionUID = 1550691097823471818L;

    private static final Dimension screenSize =
	Toolkit.getDefaultToolkit().getScreenSize();
    
    /*public static final int SCRNWIDTH = 1000,
      SCRNHEIGHT = SCRNWIDTH / 12 * 9;*/
    public static final int SCRNWIDTH = (int)screenSize.getWidth(),
      SCRNHEIGHT = (int)screenSize.getHeight() - 25;
     public static final int WIDTH = SCRNWIDTH - 250,
	HEIGHT = SCRNHEIGHT;

    //public static LoadJpg images = LoadJpg.getInstance();

    public static DirLoader images;

    public static final int gravity = 2;
    
    public static KeyInput input;
    public static MouseInput mouse;
    private Thread thread;
    private boolean running = false;
    public static Toolbar toolBar;
    public static Player player;

    public static boolean broadcasting = false;
    public static boolean inServer = false;

    //private BufferedImage backGround;

    public static Room lvl;

    private Random r;

    private Menu menu;
    
    public enum STATE {
	Menu,
	Game
    };

    private STATE gameState = STATE.Menu;
    
    
    public Game(){
	images = new DirLoader("../images");
	if (images.getDir("background") == null){
	    System.out.println("couldn't find background");
	}
	
	input = new KeyInput();
	mouse = new MouseInput();

	
	this.addKeyListener(input);
	this.addMouseListener(mouse);
	//this.addMouseWheelListener(mouse);
	this.addMouseMotionListener(mouse);
	
	mouse.addObserver(this);
	
	new Window(SCRNWIDTH, SCRNHEIGHT, "mrSSS", this);
	
	r = new Random();

	if (gameState == STATE.Game){
	    startRoom();
	}
	else if (gameState == STATE.Menu){
	    startMenu();
	}
	System.out.println(Integer.toString(SCRNWIDTH) + " " +
			   Integer.toString(SCRNHEIGHT));
	
    }

    private void startRoom() {
	player = new Player();
	lvl = new Room("alpah");
	lvl.addGameObject(player.getEntity());
	toolBar = new Toolbar(player);
	//LoadRoom(lvl, "alpah", player);
    }

    private void startMenu() {
	menu = new Menu();
    }
    
    public synchronized void start(){
	thread = new Thread(this);
	thread.start();
	running = true;
    }

    public synchronized void stop(){
	try {
	    thread.join();
	    running = false;
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public void run(){
	this.requestFocus();
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int frames = 0;
	while(running){
	    long now = System.nanoTime();
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    while(delta >= 1){
		tick();
		delta--;
	    }
	    if(running)
		render();
	    frames++;
	    
	    if(System.currentTimeMillis() - timer > 1000){
		timer += 1000;
		//System.out.println("FPS: " + frames);
		frames = 0;
	    }
	}
	stop();
    }

    private void tick(){
	if (gameState == STATE.Game){
	    if (lvl != null){
		lvl.tick();
	    }
	    //handler.tick();
	    if (toolBar != null){
		toolBar.tick();
	    }
	    if (player != null){
		player.handleInput();
	    }
	}
	else if (gameState == STATE.Menu){
	    if(menu != null){
		menu.tick();
	    }
	}
	//hud.tick();
	if (broadcasting) {
	    //System.out.println("broadcasting...");
	}
	//System.out.println(input.getInput());
    }

   
    private void render(){
	BufferStrategy bs = this.getBufferStrategy();
	if(bs == null){
	    this.createBufferStrategy(3);
	    return;
	}

	Graphics g = bs.getDrawGraphics();

	//g.setColor(Color.black);//this is the background
	//g.fillRect(0, 0, WIDTH, HEIGHT);

	if (gameState == STATE.Game){

	    if (lvl != null){
		lvl.render(g);
	    }
	    if (toolBar != null) {
		toolBar.render(g);
	    }
	    
	    //handler.player.invRender(g);
	}
	else if (gameState == STATE.Menu){
	    if (menu != null){
		menu.render(g);
	    }
	}
	
	g.dispose();
	bs.show();
    }

    //stupid java not allowing pass by refrence!!!
    public static void screenLoc(int[] array){ //the array should be x and y
	GameObject temp = player.getEntity();
	if (temp != null) {
	    int x;
	    int y;
	    if (temp == null){
		x = 0;
		y = 0;
		System.out.println("player in null");
	    }
	    else {
		x = temp.getX() - WIDTH / 2;
		y = temp.getY() - HEIGHT / 2;
		if (x < 0)
		    x = 0;
		else if (x + WIDTH > lvl.getWidth())
		    x = lvl.getWidth() - WIDTH;
		if (y < 0)
		    y = 0;
		else if (y + HEIGHT > lvl.getHeight())
		    y = lvl.getHeight() - HEIGHT;
	    }
	    array[0] = x;
	    array[1] = y;
	}
    }

    public static int clamp(int var, int min, int max) {
	if (var >= max)
	    return var = max;
	else if (var <= min)
	    return var = min;
	else
	    return var;
    }

    public void update(MouseInput mi) {
	if (gameState == STATE.Game) {
	    toolBar.mouseClick(mi.getX(), mi.getY());
	    
	}
	else if (gameState == STATE.Menu){
	    //menu.render(g);
	    menu.mouseClick(mi.getX(), mi.getY());
	    if (menu.getNextState() == STATE.Game){
		gameState = STATE.Game;
		startRoom();
	    }
	    
	}
	
	
    }
    public boolean contains(int x, int y) { return true; }
    
    public static void main(String [] args) {
	 new Game();
    }
}
