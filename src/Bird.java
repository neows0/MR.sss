//Just started this class and realized that in order to make a bird an overhall
//for collision is required
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bird extends GameObject {

  public Bird(int x, int y, int z, ID id) {
    super(x, y, id);
    //img = null;
    //img = Game.images.getImgs().get(7);
    imgs = Game.images.getDir("Bird");
    if (imgs == null)
      System.out.println("Error");
    HEIGHT = DEPTH = IMGHEIGHT = imgs.get(0).getHeight();
    WIDTH = IMGWIDTH = imgs.get(0).getWidth();

    this.z = z;
    dX = 1;
    dY = 1;
    dZ = -1;
  }

  public void tick() {
    if (id == ID.AIUnit && brain == null){
      //brain = Routines.repeat(Routines.wander(Game.lvl), -1);
      brain = Routines.follow(Game.player.getEntity());
      brain.start();
      System.out.println("new brain");
    }
    else if (id == ID.AIUnit){
      if (brain.getState() == null) {
        // hasn't started yet so we start it
        brain.start();
      }
      brain.act(this,Game.lvl);
    }
    x += dX;
    y += dY;
    z += dZ;
    x = Game.clamp(x, WIDTH / 2, Game.lvl.getWidth() - WIDTH / 2);
    y = Game.clamp(y, HEIGHT / 2, Game.lvl.getHeight() - HEIGHT);
    if (z < 0){
      z = 0;
      dZ = 0;
    }
    //AIAdvance();
  }

  public void AIAdvance() {
    if (z < 10)
      dZ = 1;
    else if (z > 250)
      dZ = -1;

    if (x > 1000)
      dX = -1;
    else if (x < 20)
      dX = 1;

    if (y < 20)
      dY = 1;
    else if (y > 1000)
      dY = -1;
  }

  public void render(Graphics g) {
    int [] cord = { 0, 0 };
    Game.screenLoc(cord);
    int tempX = x - cord[0];
    int tempY = y - cord[1];
    int H = imgs.get(1).getHeight();
    int W = imgs.get(1).getWidth();
    g.drawImage(imgs.get(1), tempX + z / 2,
    tempY + HEIGHT / 2 - H / 2,
    tempX + W + z / 2,
    tempY + HEIGHT / 2 + H / 2,
    0, 0, W, H, null);

    g.drawImage(imgs.get(0), tempX - WIDTH / 2, tempY - HEIGHT / 2 - z,
    tempX + WIDTH / 2, tempY + HEIGHT / 2 - z,
    0, 0, WIDTH, HEIGHT, null);
  }


  public void hit(GameObject collided) {

  }

}
