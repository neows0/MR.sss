package MrSSS.World;

import java.util.Comparator;
import java.util.Collections;
import java.awt.Graphics;
import java.util.LinkedList;

import MrSSS.Object.GameObject;
import MrSSS.Object.Player;
import MrSSS.Object.ObjectID;

/*advance all objects and print them to the screen*/
public class Handler {
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public static Player player;
    
    public void tick(){
		Collections.sort(objects, new Comparator<GameObject>() {
			@Override
			public int compare(GameObject GO2, GameObject GO1) {
				return ((Integer)(GO2.getY())).compareTo((Integer)(GO1.getY()));
			}
		});
		
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).tick();
		}
    }

    public void render(Graphics g) {
		player = (Player) findByID(ObjectID.Player);
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
    }

    public void addObject(GameObject object) { objects.add(object); }
    public void removeObject(GameObject object) { objects.remove(object); }
	
    public GameObject findByID(ObjectID id) {
		for (int i = 0; i < objects.size(); i++){
			if (objects.get(i).getId() == id)
			return objects.get(i);
		}
		
		return null;
    }
}
