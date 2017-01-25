package Controller;

import Model.BlueClown;
import Model.GreenClown;
import View.Logging;

public class Observer implements ObserverIF {

  @Override
  public void update(final Object o) {

    if (o instanceof BlueClown) {

      ((BlueClown) o).setScore(((BlueClown) o).getScore() + 1);
      Logging.log("BlueClownScore ="+((BlueClown) o).getScore(), "info");
    } else if (o instanceof GreenClown) {
      ((GreenClown) o).setScore(((GreenClown) o).getScore() + 1);
      Logging.log("GreenClownScore ="+((GreenClown) o).getScore(), "info");
    }
  }
}
