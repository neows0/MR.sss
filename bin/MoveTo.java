//This needs to be modified for my board
public class MoveTo extends Routine {

    protected int destX;
    protected int destY;

    private int closeEnough;

    public MoveTo(int destX, int destY) {
        super();
        this.destX = destX;
        this.destY = destY;
	closeEnough = 10;
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
	    else {
		droid.setDY(0);
		droid.setDX(0);
		succeed();
	    }
        }
    }

    private void moveGameObject(GameObject droid) {
	
	if (!isYCloseEnough(droid)) {
	    if (destY > droid.getY()) {
		droid.setDY(1);
            } else {
		droid.setDY(-1);
	    }
	    // System.out.println("Y = " + Integer.toString(droid.getY()));
	    // System.out.println("destY = " + Integer.toString(destY));
	}
	else
	    droid.setDY(0);
	//System.out.println("Y = " + Integer.toString(droid.getY()));

	//System.out.println("destY = " + Integer.toString(destY));

	if (!isXCloseEnough(droid)) {
	    if (destX > droid.getX()) {
		droid.setDX(1);
	    } else {
		droid.setDX(-1);
	    }
	}
	else
	    droid.setDX(0);
	if (isObjectAtDestination(droid)) {
	    //droid.setDX(0);
	    //droid.setDY(0);
	    System.out.println("arrived");
            succeed();
        }
        
    }
    
    private boolean isXCloseEnough(GameObject droid){
	return destX < droid.getX() + closeEnough &&
	    destX > droid.getX() - closeEnough;
    }

    private boolean isYCloseEnough(GameObject droid){
	return destY < droid.getY() + closeEnough &&
	    destY > droid.getY() - closeEnough;
    }

    private boolean isObjectAtDestination(GameObject droid) {
	return isXCloseEnough(droid) && isYCloseEnough(droid);
        //return destX == droid.getX() && destY == droid.getY();
    }
}
