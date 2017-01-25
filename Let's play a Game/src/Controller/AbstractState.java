package Controller;

import java.awt.Graphics;

import View.Game;
import View.Menu;

public abstract class AbstractState{

  public abstract void execute(Graphics g, Menu menu, Game game);

}
