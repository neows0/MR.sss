
import java.awt.Graphics;
import java.util.LinkedList;

/*advance all objects and print them to the screen*/
public class Handler {
    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
	for(int i = 0; i < objects.size(); i++){
	    objects.get(i).tick();
	}
    }

    public void render(Graphics g){
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
}
