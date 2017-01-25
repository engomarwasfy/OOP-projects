package Model;
import java.awt.Image;

import javax.swing.ImageIcon;

import Controller.ID;
import Controller.ObjectsHandler;
import View.Game;

public abstract class AbstractFactory {
  protected ImageIcon imageIcon;
  public abstract GameObject getClown(final int x, final int y, final ID id,final ObjectsHandler handler, final Game game);
  protected abstract Image getImage(final String imageName) ;
}
