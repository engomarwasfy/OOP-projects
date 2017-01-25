package Controller;
import java.awt.Graphics;
import java.util.LinkedList;

import Model.GameObject;

public class ObjectsHandler{
  public LinkedList<GameObject> objects = new LinkedList<GameObject>();
  public synchronized void tick() {
    final Iterator it = getIterator();
    while(it.hasNext()){
      it.getNext().tick();

    }
  }


  public synchronized void render(final Graphics g) {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).render(g);
    }
  }
  public Iterator getIterator(){
    return new GameObjectsIterator(objects);

  }

  public void addObject(final GameObject newObject) {
    objects.add(newObject);
  }

  public void removeObject(final GameObject Object) {
    objects.remove(Object);
  }
//	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
//
//	public  synchronized void tick() {
//		for (int i = 0; i < objects.size(); i++) {
//			objects.get(i).tick();
//		}
//	}
//
//
//	public synchronized void render(final Graphics g) {
//		for (int i = 0; i < objects.size(); i++) {
//			objects.get(i).render(g);
//		}
//	}
//
//	public void addObject(final GameObject newObject) {
//		objects.add(newObject);
//	}
//
//	public void removeObject(final GameObject Object) {
//		objects.remove(Object);
//	}


}





