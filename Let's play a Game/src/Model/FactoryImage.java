package Model;
import java.awt.Image;

import javax.swing.ImageIcon;

import Controller.ID;
import Controller.ObjectsHandler;
import View.Game;
public class FactoryImage extends AbstractFactory{
  @Override
  public Image getImage(final String imageName) {
    imageIcon = new ImageIcon(getClass().getResource(imageName));
    return imageIcon.getImage();
  }
  @Override
  public GameObject getClown(final int x, final int y, final ID id, final ObjectsHandler handler,
      final Game game) {
    // TODO Auto-generated method stub
    return null;
  }
}
