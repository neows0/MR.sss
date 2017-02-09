import java.util.LinkedList;

public class Path{
    private LinkedList<Cordinate> pathmarkers;
    private GameObject go;
    private Room room;
    Path(GameObject go, Room room, int x, int y){
	pathmarkers = new LinkedList<Cordinate>();
	this.go = go;
	pathmarkers.add(new Cordinate(x,y));
	this.room = room;
    }
    public void move(){
	
    }
    public void setNewDestination(int x, int y) {
	pathmarkers.clear();
	pathmarkers.add(new Cordinate(x, y));
	
    }
    private void avoidAllCollisions(){
	
    }
    private void avoidCollision(GameObject obstical){
	
    }
}
