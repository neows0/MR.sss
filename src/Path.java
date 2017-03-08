import java.util.LinkedList;

public class Path{
  private LinkedList<Cordinate> pathmarkers;
  private Thing mover;
  private LinkedList<Thing> obsticals;
  public Path(Thing mover, LinkedList<Thing> obsticals, int x, int y){
    pathmarkers = new LinkedList<Cordinate>();
    this.mover = mover;
    pathmarkers.add(new Cordinate(mover.getX(),mover.getY()));
    pathmarkers.add(0,new Cordinate(x,y));
    this.obsticals = obsticals;
  }
  public void move(){

  }
  public void setNewDestination(int x, int y) {
    pathmarkers.clear();
    pathmarkers.add(new Cordinate(x, y));
  }
  public void reevaluatePath(){
  }
  public void reevaluatePart(int j){
    reevaluatePart(0,j);
  }
  public void reevaluatePart(int i, int j){
    if (j < 0 || j > pathmarkers.size()){
      return;
    }

  }
}
