import java.util.List;
import java.util.ArrayList;

public abstract class Animal extends GameObject {
  private List<Animal> enemies;
  public Animal (int x, int y, ID id) {
    super(x, y, id);
  }
  public abstract void advance();
  public void tick() {
    x += dX;
    y += dY;
  	z += dZ;
  	x = Game.clamp(x, WIDTH / 2, Game.lvl.getWidth() - WIDTH / 2);
  	y = Game.clamp(y, HEIGHT / 2, Game.lvl.getHeight() - HEIGHT);
  	if (z < 0){
	    z = 0;
	    dZ = 0;
  	}
  	advance();
  }

}
