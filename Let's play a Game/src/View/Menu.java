package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.MenuEvent;

import Controller.GameOverState;
import Controller.ID;
import Controller.MenuState;
import Controller.ObjectsHandler;
import Controller.SelectDiffecultyState;
import Model.AbstractFactory;
import Model.BlueClown;
import Model.FactoryProducer;
import Model.GreenClown;

public class Menu extends MouseAdapter {

  private final Game game;
  public final ObjectsHandler handler;
  public static GreenClown greenClown;
  public static BlueClown blueClown;
  private final AbstractFactory factoryClown;

  Menu(final Game game, final ObjectsHandler handler) {
    this.game = game;
    this.handler = handler;
    factoryClown = FactoryProducer.getFactory("Clown");
  }

  @Override
  public void mousePressed(final MouseEvent e) {
    final int mouseX = e.getX();
    final int mouseY = e.getY();
    // play again
    if (game.gameStateEnum == game.gameStateEnum.GameOver) {
      if (isOver(mouseX, mouseY, 150, 64, 525, 350)) {
        game.gameStateEnum = game.gameStateEnum.Menu;
        game.state = new MenuState();
        return;
      }
    }
    if (game.gameStateEnum == game.gameStateEnum.SelectDiffeculty) {
        if (isOver(mouseX, mouseY, 100, 50, 1000, 500)) {
          game.gameStateEnum = game.gameStateEnum.Menu;
          game.state = new MenuState();
          return;
        }
      }
    if (isOver(mouseX, mouseY, 150, 64, 525, 350)
        && game.gameStateEnum == game.gameStateEnum.Menu) {
      game.gameStateEnum = game.gameStateEnum.SelectDiffeculty;
      game.state = new SelectDiffecultyState();
      return;
    }

    if (game.gameStateEnum == game.gameStateEnum.SelectDiffeculty) {
      // easy
      if (isOver(mouseX, mouseY, 150, 64, 525, 350)) {
        greenClown = (GreenClown) factoryClown.getClown(200, 510, ID.GreenClown,
            handler, game);
        blueClown = (BlueClown) factoryClown.getClown(600, 510, ID.BlueClown,
            handler, game);
        handler.addObject(greenClown);
        handler.addObject(blueClown);
        game.gameStateEnum = game.gameStateEnum.Game;
        greenClown.difficulty = "easy";
        Logging.log("Easy difficulty", "info");
      }
      // medium
      if (isOver(mouseX, mouseY, 150, 64, 525, 434)) {
        greenClown = (GreenClown) factoryClown.getClown(200, 510, ID.GreenClown,
            handler, game);
        blueClown = (BlueClown) factoryClown.getClown(600, 510, ID.BlueClown,
            handler, game);
        handler.addObject(greenClown);
        handler.addObject(blueClown);
        game.gameStateEnum = game.gameStateEnum.Game;
        greenClown.difficulty = "medium";
        Logging.log("medium difficulty", "info");
      }
      // hard
      if (isOver(mouseX, mouseY, 150, 64, 525, 518)) {
        greenClown = (GreenClown) factoryClown.getClown(200, 510, ID.GreenClown,
            handler, game);
        blueClown = (BlueClown) factoryClown.getClown(600, 510, ID.BlueClown,
            handler, game);
        handler.addObject(greenClown);
        handler.addObject(blueClown);
        game.gameStateEnum = game.gameStateEnum.Game;
        greenClown.difficulty = "hard";
        Logging.log("Hard difficulty", "info");
      }
    }
  }

  private boolean isOver(final int mouseX, final int mouseY, final int width,
      final int height, final int x, final int y) {
    if (mouseX > x && mouseX < x + width) {
      if (mouseY > y && mouseY < y + height) {
        return true;
      } else
        return false;
    } else
      return false;
  }

  public void mouseReleased(final MenuEvent e) {

  }

  public void render(final Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("arial", 50, 30));
    if (game.gameStateEnum == game.gameStateEnum.Menu) {
      g.drawRect(525, 350, 150, 64);
      g.drawString("Play", 570, 395);
    } else if (game.gameStateEnum == game.gameStateEnum.SelectDiffeculty) {
      g.drawRect(525, 350, 150, 64);
      g.drawString("Easy", 570, 395);
      g.drawRect(525, 434, 150, 64);
      g.drawString("Medium", 550, 475);
      g.drawRect(525, 518, 150, 64);
      g.drawString("Hard", 570, 560);
      g.drawRect(1000, 500, 100, 50);
      g.drawString("Back", 1020, 540);
    }

  }

}
