public class Cordinate{
  public int x;
  public int y;
  public int z;
  Cordinate(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  Cordinate(int x, int y){
    this(x,y,0);
  }
  Cordinate(){
    this(0,0,0);
  }
}
