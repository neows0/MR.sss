
public class IsObjectInRange extends Routine {

  public IsObjectInRange() {}

    @Override
    public void reset() {
      start();
    }

    @Override
    public void act(GameObject droid, Room board) {

    }

    private boolean isInRange(GameObject droid, GameObject enemy) {
      return (Math.abs(droid.getX() - enemy.getX())
      <= droid.getFeildOfVision()
      && Math.abs(droid.getY() - enemy.getY())
      <= droid.getFeildOfVision());
    }
  }
