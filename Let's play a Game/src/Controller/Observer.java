package Controller;

import Model.BlueClown;
import Model.GreenClown;
import View.CareTaker;
import View.Logging;
import View.Originator;
import View.ScoreData;

public class Observer implements ObserverIF {
  
 public  Originator orig = new  Originator();
  public CareTaker careTaker = new  CareTaker();
  public Observer() {
	orig.setgScore(0);
	orig.setgScore(0);
	careTaker.add(orig.saveStateToMemento());
}
  public void update(final Object o) {
    if (o instanceof BlueClown) {

      ((BlueClown) o).setScore(((BlueClown) o).getScore() + 1);
      orig.setbScore(((BlueClown) o).getScore());
      careTaker.add(orig.saveStateToMemento());
     ScoreData.setgScore(((BlueClown) o).getScore());
      Logging.log("BlueClownScore ="+((BlueClown) o).getScore(), "info");
    } else if (o instanceof GreenClown) {
      ((GreenClown) o).setScore(((GreenClown) o).getScore() + 1);
      orig.setgScore(((GreenClown) o).getScore());
      careTaker.add(orig.saveStateToMemento());
      ScoreData.setbScore(((GreenClown) o).getScore());
      Logging.log("GreenClownScore ="+((GreenClown) o).getScore(), "info");
    }
  }
}
