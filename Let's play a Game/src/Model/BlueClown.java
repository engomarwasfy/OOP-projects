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
public class BlueClown extends GameObject implements ObservableIF{

  private static BlueClown instance;
  private final Image image;
  private final ObjectsHandler handler;
  private int numOfPlatesStick1, numOfPlatesStick2;
  private int score;
  private final Stack<GameObject> leftStickPaltes = new Stack<GameObject>();
  private final Stack<GameObject> RightStickPaltes = new Stack<GameObject>();
  private final Random rand;
  private final FactoryImage factoryImage;
  private ObserverIF observer;
  /**
   * @param score the score to set
   */
  public void setScore(final int score) {
    this.score = score;
  }
  public static boolean maxPlates;

  private BlueClown(final int x, final int y, final ID id, final ObjectsHandler handler, final Game game,int score) {
    super(x, y, ID.BlueClown);
    add(new Observer());
    factoryImage = new FactoryImage();
    image = factoryImage.getImage("clown2.PNG");
    this.handler = handler;
    numOfPlatesStick1 = 0;
    numOfPlatesStick2 = 0;
    this.score = score;
    rand = new Random();
    maxPlates = false;
  }
  public static BlueClown getInstance(final int x, final int y, final ID id, final ObjectsHandler handler, final Game game,int score){
    if (instance == null) {
      return new BlueClown(x, y, id, handler, game,score);
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
    updateScore(g);
  }

  private void updateScore(final Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", 1, 20));
    g.drawString("Blue Clown Score = " + score, 800, 20);
  }

  private void checkConsecutivePlates() {
    boolean leftCheckMaxPlates = false;
    boolean rightCheckMaxPlates = false;

    if (leftStickPaltes.size() >= 3) {
      final GameObject plate1 = leftStickPaltes.pop();
      final GameObject plate2 = leftStickPaltes.pop();
      final GameObject plate3 = leftStickPaltes.pop();
      if(plate1.y <= 70) {
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
      if(plate1.y <= 70) {
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
    if(leftCheckMaxPlates && rightCheckMaxPlates) {
      maxPlates = true;
    }
  }

  private void collision() {
    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.Plate) {
        final GameObject currentObject = handler.objects.get(i);
        if (!currentObject.collision) {
          if (getBound().intersects(currentObject.getBound())) {
            currentObject.collision = true;
            currentObject.playerStick = "player2Stick1";
            numOfPlatesStick1++;
            leftStickPaltes.push(currentObject);
            currentObject.collisionY = maxPlateY1();
          } else if (getBound2().intersects(currentObject.getBound())) {
            currentObject.collision = true;
            currentObject.playerStick = "player2Stick2";
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

  /**
   * @return the numOfPlatesStick1
   */
  public int getNumOfPlatesStick1() {
    return numOfPlatesStick1;
  }

  /**
   * @param numOfPlatesStick1 the numOfPlatesStick1 to set
   */
  public void setNumOfPlatesStick1(final int numOfPlatesStick1) {
    this.numOfPlatesStick1 = numOfPlatesStick1;
  }

  /**
   * @return the numOfPlatesStick2
   */
  public int getNumOfPlatesStick2() {
    return numOfPlatesStick2;
  }

  /**
   * @param numOfPlatesStick2 the numOfPlatesStick2 to set
   */
  public void setNumOfPlatesStick2(final int numOfPlatesStick2) {
    this.numOfPlatesStick2 = numOfPlatesStick2;
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
