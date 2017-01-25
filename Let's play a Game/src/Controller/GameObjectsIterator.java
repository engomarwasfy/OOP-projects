package Controller;
import java.util.LinkedList;

import Model.GameObject;

public class GameObjectsIterator implements Iterator {
	private int index = -1;
	private final LinkedList<GameObject>x;
	public GameObjectsIterator(final LinkedList<GameObject>x) {
		this.x = x;
	}

	@Override
  public boolean hasNext() {
		// TODO Auto-generated method stub
		return index < x.size()-1;
	}

	@Override
  public GameObject getNext() {


		return x.get(++index);

	}

}
