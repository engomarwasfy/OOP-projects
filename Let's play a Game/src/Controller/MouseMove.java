package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import Model.GameObject;

public class MouseMove extends MouseMotionAdapter implements MouseMotionListener{
  private final ObjectsHandler handler;
  public MouseMove(final ObjectsHandler handler) {
    this.handler = handler;
  }
  @Override
  public void mouseDragged(final MouseEvent e) {

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
