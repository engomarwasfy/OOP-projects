package Model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import Controller.ID;
import Controller.ObjectsHandler;

public class PlateBall extends GameObject{
  private final Image platesArr[] = new Image[3];
  private final Random rand;
  private final ObjectsHandler handler;
  private final Image image1;
  private final Image image2;
  private final Image image3;
  private final Image randImage;
  private final FactoryImage factoryImage;

  public PlateBall(final int x, final int y, final ID id, final ID direction,
      final ObjectsHandler handler, final String difficulty) {
    super(x, y, ID.Plate);
    factoryImage = new FactoryImage();
    image1 = factoryImage.getImage("ballBlue.png");
    image2 = factoryImage.getImage("ballGreen.png");
    image3 = factoryImage.getImage("ballRed.png");
    platesArr[0] = image1;
    platesArr[1] = image2;
    platesArr[2] = image3;

    if (difficulty == "easy") {
      velX = 3;
      velY = 3;
    } else if (difficulty == "medium") {
      velX = 5;
      velY = 5;
    } else if (difficulty == "hard") {
      velX = 7;
      velY = 10;
    }
    rand = new Random();
    randImage = platesArr[rand.nextInt(100) % 3];
    if (randImage == image1) {
      this.color = Color.BLUE;
    } else if (randImage == image2) {
      this.color = Color.GREEN;
    } else if (randImage == image3) {
      this.color = Color.RED;
    }
    this.direction = direction;
    this.handler = handler;
  }

  @Override
  public void tick() {
    if (!collision) {
      if (direction == ID.LeftUp && x < 550) {
        x += velX;
      } else if (direction == ID.RightUp && x > 650) {
        x -= velX;
      } else if (direction == ID.LeftDown && x < 350) {
        x += velX;
      } else if (direction == ID.RightDown && x > 800) {
        x -= velX;
      } else {
        y += velY;
      }
      if (y > 750) {
        PoolPlates.releaseObject(this);
        handler.removeObject(this);
      }
    } else {
      y = collisionY;
      if (playerStick.equals("player1Stick1")) {
        x = View.Menu.greenClown.x + 10;
      } else if (playerStick.equals("player1Stick2")) {
        x = View.Menu.greenClown.x + 100;
      } else if (playerStick.equals("player2Stick1")) {
        x = View.Menu.blueClown.x + 10;
      } else if (playerStick.equals("player2Stick2")) {
        x = View.Menu.blueClown.x + 100;
      }
    }
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(randImage, x, y, null );
  }

  @Override
  public Rectangle getBound() {
    return new Rectangle(x, y, 42, 17);
  }
  @Override
  public GameObject getInstance(final int x, final int y, final ID id, final ID direction,
      final ObjectsHandler handler, final String difficulty) {
    // TODO Auto-generated method stub
    return new PlateNormal(x, y, id, direction, handler, difficulty);
  }

}
