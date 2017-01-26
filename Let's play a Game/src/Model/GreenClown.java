package Model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Stack;

import Controller.ID;
import Controller.ObjectsHandler;
import Controller.ObservableIF;
import Controller.Observer;
import Controller.ObserverIF;
import View.Game;

public class GreenClown extends GameObject implements ObservableIF {
  private static GreenClown instance;
  private final Image image;
  private int timeOfUpPlates, timeOfDownPlates;
  private final ObjectsHandler handler;
  private int numOfPlatesStick1, numOfPlatesStick2;
  private int score;
  private final Stack<GameObject> leftStickPaltes = new Stack<GameObject>();
  private final Stack<GameObject> RightStickPaltes = new Stack<GameObject>();
  private final Random rand;
  private final Game game;
  private final FactoryImage factoryImage;
  private ObserverIF observer;

  private GreenClown(final int x, final int y, final ID id, final ObjectsHandler handler,
      final Game game,int score) {
    super(x, y, ID.GreenClown);
    add(new Observer());
    factoryImage = new FactoryImage();
    image = factoryImage.getImage("clown.PNG");
    this.handler = handler;
    timeOfUpPlates = 0;
    timeOfDownPlates = 0;
    numOfPlatesStick1 = 0;
    numOfPlatesStick2 = 0;
    score = 0;
    rand = new Random();
    this.game = game;
  }
  public static GreenClown getInstance(final int x, final int y, final ID id, final ObjectsHandler handler,
      final Game game,int score){
    if (instance == null) {
      return new GreenClown(x, y, id, handler, game,score);
    }
    return instance;
  }
  @Override
  public void tick() {
    if (x <= 0) {
      x = 0;
    }
    if (x >= 1200 - 135) {
      x = 1200 - 135;
    }
    x += velX;
    checkConsecutivePlates();
    collision();
  }

  @Override
  public void render(final Graphics g) {
    g.setColor(Color.WHITE);
    g.drawImage(image, x, y, null);
    if (difficulty == "easy") {
      updateEasyPlates();
    } else if (difficulty == "medium") {
      updateMediumPlates();
    } else if (difficulty == "hard") {
      updateHardPlates();
    }
    updateScore(g);
  }

  private void updateScore(final Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", 1, 20));
    g.drawString("Green Clown Score = " + score, 10, 20);
  }

  private void checkConsecutivePlates() {
    boolean leftCheckMaxPlates = false;
    boolean rightCheckMaxPlates = false;

    if (leftStickPaltes.size() >= 3) {
      final GameObject plate1 = leftStickPaltes.pop();
      final GameObject plate2 = leftStickPaltes.pop();
      final GameObject plate3 = leftStickPaltes.pop();
      if (plate1.y <= 70) {
        leftCheckMaxPlates = true;
      }
      if (plate1.color == plate2.color && plate2.color == plate3.color) {
        PoolPlates.releaseObject(plate1);
        PoolPlates.releaseObject(plate2);
        PoolPlates.releaseObject(plate3);
        handler.removeObject(plate1);
        handler.removeObject(plate2);
        handler.removeObject(plate3);
        numOfPlatesStick1 -= 3;
        notify(this);
      } else {
        leftStickPaltes.push(plate3);
        leftStickPaltes.push(plate2);
        leftStickPaltes.push(plate1);
      }
    }
    if (RightStickPaltes.size() >= 3) {
      final GameObject plate1 = RightStickPaltes.pop();
      final GameObject plate2 = RightStickPaltes.pop();
      final GameObject plate3 = RightStickPaltes.pop();
      if (plate1.y <= 70) {
        rightCheckMaxPlates = true;
      }
      if (plate1.color == plate2.color && plate2.color == plate3.color) {
        PoolPlates.releaseObject(plate1);
        PoolPlates.releaseObject(plate2);
        PoolPlates.releaseObject(plate3);
        handler.removeObject(plate1);
        handler.removeObject(plate2);
        handler.removeObject(plate3);
        numOfPlatesStick2 -= 3;
        notify(this);
      } else {
        RightStickPaltes.push(plate3);
        RightStickPaltes.push(plate2);
        RightStickPaltes.push(plate1);
      }
    }
    if (leftCheckMaxPlates && rightCheckMaxPlates) {
      if (BlueClown.maxPlates) {
        if (game.gameStateEnum != game.gameStateEnum.GameOver) {
          game.gameStateEnum = game.gameStateEnum.GameOver;
          handler.objects.clear();
        }
      }
    }
  }

  private void updateEasyPlates() {
    if (timeOfUpPlates < rand.nextInt(150) + 90) {
      timeOfUpPlates++;
    } else {
      if (rand.nextInt(6) != 5) {
        handler.addObject(PoolPlates.getObject(0, 70, ID.Plate, ID.LeftUp, handler, "easy"));
      }
      if (rand.nextInt(6) != 3) {

        handler.addObject(PoolPlates.getObject(1200, 70, ID.Plate, ID.RightUp, handler, "easy"));

      }
      timeOfUpPlates = 0;
    }
    if (timeOfDownPlates < rand.nextInt(150) + 90) {
      timeOfDownPlates++;
    } else {
      if (rand.nextInt(6) != 5) {
        handler.addObject(PoolPlates.getObject(0, 150, ID.Plate, ID.LeftDown, handler, "easy"));
      }
      if (rand.nextInt(6) != 2) {
        handler.addObject(PoolPlates.getObject(1200, 150, ID.Plate, ID.RightDown, handler, "easy"));
      }
      timeOfDownPlates = 0;
    }
  }

  private void updateMediumPlates() {
    if (timeOfUpPlates < rand.nextInt(150) + 50) {
      timeOfUpPlates++;
    } else {
      if (rand.nextInt(6) != 5) {
        handler.addObject(PoolPlates.getObject(0, 70, ID.Plate, ID.LeftUp, handler, "medium"));
      }
      if (rand.nextInt(6) != 5) {
        handler.addObject(PoolPlates.getObject(1200, 70, ID.Plate, ID.RightUp, handler, "medium"));

      }
      timeOfUpPlates = 0;
    }
    if (timeOfDownPlates < rand.nextInt(150) + 50) {
      timeOfDownPlates++;
    } else {
      if (rand.nextInt(6) != 5) {
        handler.addObject(PoolPlates.getObject(0, 150, ID.Plate, ID.LeftDown, handler, "medium"));
      }
      if (rand.nextInt(6) != 5) {
        handler.addObject(PoolPlates.getObject(1200, 150, ID.Plate, ID.RightDown, handler, "medium"));
      }
      timeOfDownPlates = 0;
    }
  }

  private void updateHardPlates() {
    if (timeOfUpPlates < rand.nextInt(150) + 30) {
      timeOfUpPlates++;
    } else {
      handler.addObject(PoolPlates.getObject(0, 70, ID.Plate, ID.LeftUp, handler, "hard"));
      if (rand.nextInt(100) % 2 == 0) {
        handler.addObject(PoolPlates.getObject(1200, 70, ID.Plate, ID.RightUp, handler, "hard"));
      }
      timeOfUpPlates = 0;
    }
    if (timeOfDownPlates < rand.nextInt(150) + 30) {
      timeOfDownPlates++;
    } else {
      if (rand.nextInt(100) % 2 == 0) {
        handler.addObject(PoolPlates.getObject(0, 150, ID.Plate, ID.LeftDown, handler, "hard"));
      }
      handler.addObject(PoolPlates.getObject(1200, 150, ID.Plate, ID.RightDown, handler, "hard"));
      timeOfDownPlates = 0;
    }
  }

  private void collision() {
    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.Plate) {
        final GameObject currentObject = handler.objects.get(i);
        if (!currentObject.collision) {
          if (getBound().intersects(currentObject.getBound())) {
            currentObject.collision = true;
            currentObject.playerStick = "player1Stick1";
            numOfPlatesStick1++;
            leftStickPaltes.push(currentObject);
            currentObject.collisionY = maxPlateY1();
          } else if (getBound2().intersects(currentObject.getBound())) {
            currentObject.collision = true;
            currentObject.playerStick = "player1Stick2";
            numOfPlatesStick2++;
            RightStickPaltes.push(currentObject);
            currentObject.collisionY = maxPlateY2();
          }
        }
      }
    }
  }

  @Override
  public Rectangle getBound() {
    final int heightOfPlate = 17;
    return new Rectangle(x, y - numOfPlatesStick1 * heightOfPlate, 35, 1);
  }

  public Rectangle getBound2() {
    final int heightOfPlate = 17;
    return new Rectangle(x + 90, y - numOfPlatesStick2 * heightOfPlate, 35, 1);
  }

  /**
   * @return the score
   */
  public int getScore() {
    return score;
  }
  public int maxPlateY1(){
    return this.y - numOfPlatesStick1 * 17;
  }
  public int maxPlateY2(){
    return this.y - numOfPlatesStick2 * 17;
  }
  @Override
  public GameObject getInstance(final int x, final int y, final ID id, final ID direction,
      final ObjectsHandler handler, final String difficulty) {
    // TODO Auto-generated method stub
    return null;
  }
  public void setScore(final int i) {
    this.score =i;

  }
  @Override
  public void add(final ObserverIF observer) {
    // TODO Auto-generated method stub
    this.observer = observer;
  }
  @Override
  public void notify(final Object o) {
    // TODO Auto-generated method stub
    observer.update(o);
  }
}
