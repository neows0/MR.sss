import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/*
 * BufferedImage.getSubimage(int x, int y, int w, int h);
 */

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1550691097823471818L;

    public static final int SCRNWIDTH = 1000,
	SCRNHEIGHT = SCRNWIDTH / 12 * 9;
     public static final int WIDTH = 750,
	HEIGHT = SCRNHEIGHT;

    public static LoadJpg images = LoadJpg.getInstance();

    public static final int gravity = 2;

    public static int LVLWIDTH;
    public static int LVLHEIGHT;
    public static KeyInput input;
    public static MouseInput mouse;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private static Handler handler;
    private BufferedImage backGround;

    private Menu menu;
    
    public enum STATE {
	Menu,
	Game
    };

    public STATE gameState = STATE.Game;

    HUD hud;  //THIS I THINK COULD BE CHANGED
    
    public Game(){
	//backGround = images.imageList.get(12);//LoadJpg.load("background.png");
	backGround = images.getDir("background").get(0);
	LVLWIDTH = backGround.getWidth();
	LVLHEIGHT = backGround.getHeight();
	
	handler = new Handler();
	input = new KeyInput();
	mouse = new MouseInput();
	menu = new Menu(this);
	this.addKeyListener(input);
	this.addMouseListener(mouse);
	this.addMouseWheelListener(mouse);
	
	new Window(SCRNWIDTH, SCRNHEIGHT, "mrSSS", this);	

	//hud = new HUD();
	
	r = new Random();

	if (gameState == STATE.Game){
	

	    //r.nextInt(WIDTH - 32)
	    //r.nextInt(HEIGHT - 32)
	    Player temp = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler);
	    handler.addObject(temp);
	
	    //int j = 0;
	    /*
	    for (int i = 0; i < 100; i++){
		int j = r.nextInt(1000);
		int k = r.nextInt(1400);
		if (!temp.getBounds().contains(j - 100, k - 100, 200, 200))
		    handler.addObject(new Tree(j, k, ID.Obstacle, handler));
		    }*/
	}
	
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
	    handler.tick();
	}
	else if (gameState == STATE.Menu){
	    menu.tick();
	}
	//hud.tick();
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
	
	    GameObject temp = handler.findByID(ID.Player);
	    if (temp == null){
		g.drawImage(backGround, 0, 0, WIDTH, HEIGHT,
			    0, 0, WIDTH, HEIGHT, null);
		System.out.println("player in null");
	    }
	    else {
	    
		int [] array = { 0, 0 };
		screenLoc(array);
	    
		int x = array[0];
		int y = array[1];
		
		g.drawImage(backGround, 0, 0, WIDTH, HEIGHT,
			    x, y, WIDTH + x, HEIGHT + y, null);
	    }
	
	    handler.render(g);
	}
	else if (gameState == STATE.Menu){
	    menu.render(g);
	}
	
	g.dispose();
	bs.show();
    }

    //stupid java not allowing pass by refrence!!!
    public static void screenLoc(int[] array){ //the array should be x and y
	GameObject temp = handler.findByID(ID.Player);
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
	    else if (x + WIDTH > LVLWIDTH)
		x = LVLWIDTH - WIDTH;
	    if (y < 0)
		y = 0;
	    else if (y + HEIGHT > LVLHEIGHT)
		y = LVLHEIGHT - HEIGHT;
	}
	array[0] = x;
	array[1] = y;
    }

    public static int clamp(int var, int min, int max) {
	if (var >= max)
	    return var = max;
	else if (var <= min)
	    return var = min;
	else
	    return var;
    }
    
    public static void main(String [] args) {
	 new Game();
    }
}
