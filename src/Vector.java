public class Vector{
  public Cordinate c1;
  public Cordinate c2;

  Vector(Cordinate c1, Cordinate c2){
    this.c1 = c1;
    this.c2 = c2;
  }
  Vector(int x1, int y1, int x2, int y2){
    this(new Cordinate(x1, y1), new Cordinate(x2, y2));
  }
}
