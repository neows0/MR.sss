//This needs to be modified for my board
public class MoveTo extends Routine {

    final protected int destX;
    final protected int destY;

    public MoveTo(int destX, int destY) {
        super();
        this.destX = destX;
        this.destY = destY;
    }

    public void reset() {
        start();
    }

    @Override
    public void act(GameObject droid, Room board) {
        if (isRunning()) {
	    /*
            if (!droid.isAlive()) {
                fail();
                return;
		}*/
            if (!isObjectAtDestination(droid)) {
                moveGameObject(droid);
            }
        }
    }

    private void moveGameObject(GameObject droid) {
        if (destY != droid.getY()) {
            if (destY > droid.getY()) {
                droid.setY(droid.getY() + 1);
            } else {
                droid.setY(droid.getY() - 1);
            }
        }
        if (destX != droid.getX()) {
            if (destX > droid.getX()) {
                droid.setX(droid.getX() + 1);
            } else {
                droid.setX(droid.getX() - 1);
            }
        }
        if (isObjectAtDestination(droid)) {
            succeed();
        }
    }

    private boolean isObjectAtDestination(GameObject droid) {
        return destX == droid.getX() && destY == droid.getY();
    }
}
