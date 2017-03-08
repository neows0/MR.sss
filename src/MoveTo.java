//This needs to be modified for my board
public class MoveTo extends Routine {

  protected int destX;
  protected int destY;
  private int topSpeed;

  private int closeEnough;

  public MoveTo(int destX, int destY) {
    super();
    this.destX = destX;
    this.destY = destY;
    closeEnough = 10;
    topSpeed = 7;
  }

  public void reset() {
    start();
  }

  @Override
  public void act(GameObject droid, Room board) {
    if (isRunning()) {

      if (!isAtDestination(droid)) {
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
    int x = droid.getX();
    int y = droid.getY();
    int dX = (x - destX);
    int dY = (y - destY);
    double angle = Math.atan2(dY,dX);

    droid.setDY(-(int)(Math.sin(angle) * (double)getSpeed(x,y)));
    droid.setDX(-(int)(Math.cos(angle) * (double)getSpeed(x,y)));

    if (isAtDestination(droid)) {
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

  private int getSpeed(int x, int y){
    int speed = 0;
    if (getDistBetween(x,y) < closeEnough * 2){
      speed = topSpeed / 2;
    }
    else{
      speed = topSpeed;
    }
    return speed;
  }

  public int getDistBetween(int x, int y){
    return Math.abs(x - destX) + Math.abs(y - destY);
  }

  public boolean isAtDestination(GameObject droid) {
    return isXCloseEnough(droid) && isYCloseEnough(droid);
    //return destX == droid.getX() && destY == droid.getY();
  }

  public int getCloseEnough(){
    return closeEnough;
  }

  public int getDestX(){
    return destX;
  }
  public int getDestY(){
    return destY;
  }
  public int getTopSpeed(){
    return topSpeed;
  }

  public void setCloseEnough(int closeEnough){
    this.closeEnough = closeEnough;
  }
  public void setDestX(int destX){
    this.destX = destX;
  }
  public void setDestY(int destY){
    this.destY = destY;
  }
  public void setTopSpeed(int topSpeed){
    this.topSpeed = topSpeed;
  }
}
