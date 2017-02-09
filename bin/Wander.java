
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
        this.moveTo = new MoveTo(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
    }

    public Wander(Room board) {
        super();
        this.board = board;
        this.moveTo = new MoveTo(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
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
