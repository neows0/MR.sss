public class Thing{
  public Edges boundary;
  public Cordinate location;
  public Thing(Edges eg, Cordinate lo){
    boundary = eg;
    location = lo;
  }
  public Thing(Edges eg, int x, int y){
    this(eg,new Cordinate(x, y));
  }
  public int getX(){
    return location.x;
  }
  public int getY(){
    return location.y;
  }
}
