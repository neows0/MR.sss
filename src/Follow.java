public class Follow extends Routine{
  private Approach approach;
  private Routine repeat;
  public Follow(GameObject following){
    approach = (Approach)Routines.approach(following);
    repeat = Routines.repeat(approach, -1);
    repeat.start();
  }
  public void act(GameObject droid, Room board){
    if (repeat == null){
      return;
    }
    else{
      repeat.act(droid, board);
    }

  }
  public void reset() {
    repeat.reset();
  }
  public int getPersonalSpace(){
    return approach.getPersonalSpace();
  }
  public void setPersonalSpace(int ps){
    approach.setPersonalSpace(ps);
  }

}
