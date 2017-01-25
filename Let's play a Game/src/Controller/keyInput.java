package Controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Model.GameObject;
import View.Game;

public class keyInput extends KeyAdapter implements MouseMotionListener{

  private final ObjectsHandler handler;
  private boolean left = false;
  private boolean right = false;

  public keyInput(final ObjectsHandler handler) {
    this.handler = handler;
  }

  @Override
  public void keyPressed(final KeyEvent e) {
    final int key = e.getKeyCode();
    if (key == 32) {
      Game.pause = !Game.pause;
    }
    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.GreenClown) {
        if (key == 39) {
          handler.objects.get(i).setVelX(5);
          right = true;
        }
        if (key == 37) {
          handler.objects.get(i).setVelX(-5);
          left = true;
        }
      }

    }
    return;
//    mouseMoved();
  }

  @Override
  public void keyReleased(final KeyEvent e) {
    final int key = e.getKeyCode();

    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.GreenClown) {
        if (key == 39) {
          right = false;
        }
        if (key == 37) {
          left = false;
        }
        if (!right && !left) {
          handler.objects.get(i).setVelX(0);
        }
      }

    }
  }

  @Override
  public void mouseDragged(final MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseMoved(final MouseEvent e) {
//    System.out.println(e.getX()+" "+e.getY());
    for (int i = 0; i < handler.objects.size(); i++) {
      final GameObject currObject = handler.objects.get(i);
      if (currObject.id == ID.BlueClown) {
        currObject.setX(e.getX());
      }
    }
  }
}
