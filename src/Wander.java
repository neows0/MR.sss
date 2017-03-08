
import java.util.Random;

public class Wander extends Routine {

  private static Random random = new Random();
  private final Room board;
  private MoveTo moveTo;

  @Override
  public void start() {
    super.start();
    this.moveTo.start();
  }

  public void reset() {
    this.moveTo = new MoveTo(randomBetween(200,board.getWidth()),
    randomBetween(200,board.getHeight()));
  }

  public Wander(Room board) {
    super();

    this.board = board;
    random = new Random();
    if (board != null){
      this.moveTo = new MoveTo(randomBetween(200,board.getWidth()),
      randomBetween(200,board.getHeight()));
      //this.moveTo = new MoveTo(0,0);
    }
    else{
      System.out.println("Board is null");
      this.moveTo = new MoveTo(100,100);
    }
  }

  private int randomBetween(int min, int max){
    if (min > max){
      int temp = min;
      min = max;
      max = temp;
    }
    return min + random.nextInt(max - min);
  }

  @Override
  public void act(GameObject droid, Room board) {
    if (!moveTo.isRunning()) {
      return;
    }
    this.moveTo.act(droid, board);
    if (this.moveTo.isSuccess()) {
      succeed();
    } else if (this.moveTo.isFailure()) {
      fail();
    }
  }
}
