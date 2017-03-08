import java.util.Comparator;
import java.util.Collections;
import java.awt.Graphics;
import java.util.LinkedList;


/*advance all objects and print them to the screen*/
public class Handler {
  LinkedList<GameObject> objects = new LinkedList<GameObject>();

  //public static Player player;

  public void tick(){
    //used to put the closes objects in the end of the list so they can
    //appear infront of the other objects
    Collections.sort(objects, new Comparator<GameObject>() {
      @Override
      public int compare(GameObject GO2, GameObject GO1) {
        return ((Integer)
        (GO2.getY() + GO2.getZ())).compareTo((Integer)
        (GO1.getY() +
        GO1.getZ()));
      }
    });
    for(int i = 0; i < objects.size(); i++){
      objects.get(i).tick();
      if (Game.broadcasting && objects.get(i).updated()) {
        //insert code to update clients
      }
    }
  }

  public void render(Graphics g){
    //player = (Player) findByID(ID.Player);
    for(int i = 0; i < objects.size(); i++){
      objects.get(i).render(g);
    }
  }

  public void addObject(GameObject object) {
    objects.add(object);
  }

  public GameObject findByID(ID id) {
    for (int i = 0; i < objects.size(); i++){
      if (objects.get(i).getId() == id)
      return objects.get(i);
    }
    return null;
  }

  public void removeObject(GameObject object) {
    objects.remove(object);
  }

  public LinkedList<GameObject> getObjectList(){
    return objects;
  }

  public LinkedList<Thing> getObsticals(GameObject notThis){
    if (objects != null && objects.size() > 0){
      LinkedList<Thing> obsticals = new LinkedList<Thing>();
      for(GameObject go : objects){
        obsticals.add(go.getThing());
      }
      return obsticals;
    }
    return null;

  }
}
