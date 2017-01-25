package Model;
import java.util.LinkedList;
import java.util.Random;

import Controller.ID;
import Controller.ObjectsHandler;

public class FactoryPlate {
  GameObject normalPlate;
  GameObject ballPlate;
  LinkedList<GameObject> plates;

  public FactoryPlate() {
    // TODO Auto-generated constructor stub
    plates = new LinkedList<GameObject>();
    loadPlates();
  }

  public void loadPlates() {
    plates.add(normalPlate);
  }

  public GameObject getRandomPlate(final int x, final int y, final ID id,
      final ID direction, final ObjectsHandler handler,
      final String difficulty) {
    normalPlate = new PlateNormal(x, y, id, direction, handler, difficulty);
    ballPlate = new PlateBall(x, y, id, direction, handler, difficulty);
    final int rand = new Random().nextInt(2);
    switch (rand) {
    case 0:
      return normalPlate;
    case 1:
      return ballPlate;
    default:
      return null;
    }

  }
}
