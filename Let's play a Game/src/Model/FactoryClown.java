package Model;
import java.awt.Image;

import Controller.ID;
import Controller.ObjectsHandler;
import View.Game;
public class FactoryClown extends AbstractFactory{
  @Override
  public GameObject getClown(final int x, final int y, final ID id,
      final ObjectsHandler handler, final Game game) {
    if (id == ID.BlueClown) {
      return BlueClown.getInstance(x, y, id, handler, game,0);
    } else if (id == ID.GreenClown) {
      return GreenClown.getInstance(x, y, id, handler, game,0);
    }
    return null;
  }

  @Override
  protected Image getImage(final String imageName) {
    // TODO Auto-generated method stub
    return null;
  }
}
