package Model;
import java.util.HashMap;
import java.util.Map;

import Controller.ID;
import Controller.ObjectsHandler;

public class PoolPlates {
  public static HashMap<GameObject, Long> available = new HashMap<GameObject, Long>();
  public static HashMap<GameObject, Long> inUse = new HashMap<GameObject, Long>();
  private static FactoryPlate factoryPlate = new FactoryPlate();

  public synchronized static GameObject getObject(final int x, final int y, final ID id, final ID direction,
      final ObjectsHandler handler, final String difficulty) {
    final long now = System.currentTimeMillis();
    if (available.isEmpty()) {
      final GameObject pooledObject = factoryPlate.getRandomPlate(x, y, id, direction, handler, difficulty);
      push(inUse, pooledObject, now);
//    Create new instance
      return pooledObject;
    } else {
      for (final Map.Entry<GameObject, Long> entry : available.entrySet()) {
        if (entry.getKey().direction == direction) {
//        Improve performance**************
          final GameObject pooledObject = popElement(available, entry.getKey());
          cleanUp(pooledObject);
          push(inUse, pooledObject, now);
          return pooledObject;
        }
      }
    }
    final GameObject pooledObject = factoryPlate.getRandomPlate(x, y, id, direction, handler, difficulty);
    push(inUse, pooledObject, now);
//  Create new instance
    return pooledObject;
  }

  private synchronized static void push(final HashMap<GameObject, Long> map,
      final GameObject po, final long now) {
    map.put(po, now);
  }

  public static void releaseObject(final GameObject po) {
    cleanUp(po);
    available.put(po, System.currentTimeMillis());
    inUse.remove(po);
  }

  public static GameObject popElement(final HashMap<GameObject, Long> map) {
     final Map.Entry<GameObject, Long> entry = map.entrySet().iterator().next();
     final GameObject key= entry.getKey();
     //Long value=entry.getValue();
     map.remove(entry.getKey());
     return key;
  }

  public static GameObject popElement(final HashMap<GameObject, Long> map, final GameObject key) {
    map.remove(key);
    return key;
  }

  public static void cleanUp(final GameObject po) {
    po.collision = false;
    if (po.direction == ID.LeftUp) {
      po.x = 0;
      po.y = 70;
    } else if (po.direction == ID.RightUp) {
      po.x = 1200;
      po.y = 70;
    } else if (po.direction == ID.LeftDown) {
      po.x = 0;
      po.y = 150;
    } else if (po.direction == ID.RightDown) {
      po.x = 1200;
      po.y = 150;
    }

  }
}
