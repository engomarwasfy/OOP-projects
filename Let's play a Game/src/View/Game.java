package View;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import Controller.AbstractState;
import Controller.GameOverState;
import Controller.GameState;
import Controller.MenuState;
import Controller.MouseMove;
import Controller.ObjectsHandler;
import Controller.SelectDiffecultyState;
import Controller.State;
import Controller.keyInput;
import Model.FactoryImage;

public class Game extends Canvas implements Runnable {
  public static final int WIDTH = 1200;
  public static boolean pause = false;
  private final int HEIGHT = 700;
  private Thread thread;
  private boolean running = false;
  private final ObjectsHandler handler;
  private final Menu menu;
  public final Image image, image2, ImagebackGround;
  private final FactoryImage FactoryImage;
  public AbstractState state = new MenuState();
  
//  private final AbstractState state;

  public Game() {
//    state = new MenuState();
    handler = new ObjectsHandler();
    menu = new Menu(this, handler);
    this.addKeyListener(new keyInput(handler));
    this.addMouseListener(menu);
    this.addMouseMotionListener(new MouseMove(handler));
    new Window(WIDTH, HEIGHT, "Circus of Plates", this, handler);
    FactoryImage = new FactoryImage();
    image = FactoryImage.getImage("clown.PNG");
    image2 = FactoryImage.getImage("clown2.PNG");
    ImagebackGround = FactoryImage.getImage("backGround.PNG");
  }

  public State gameStateEnum = State.Menu;

  public void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public void stop() {
    try {
      thread.join();
      running = false;
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Game loop
   */
  @Override
  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    final double amountOfTicks = 60.0;
    final double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    while (running) {

      final long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;

      while (delta >= 1) {
        if (!pause && gameStateEnum != State.GameOver) {
          handler.tick();
        }
        delta--;
      }
      if (running && !pause) {
        render();
      }
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
      }
    }
    stop();
  }

  private void render() {
    this.requestFocus();

    final BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    final Graphics g = bs.getDrawGraphics();
  g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);
  
	state.execute(g, new Menu(this, handler), null);
    
    	
    
    if (gameStateEnum == State.Game) {
      g.drawImage(ImagebackGround, 0, 0, null);
      handler.render(g);
    } else if (gameStateEnum == State.Menu
        || gameStateEnum == State.SelectDiffeculty) {
      menu.render(g);
    } else if (gameStateEnum == State.GameOver) {
      g.setColor(Color.WHITE);
      g.drawRect(525, 350, 150, 64);
      g.setFont(new Font("Arial", 1, 20));
      g.drawString("Back to Menu", 540, 390);
      g.setFont(new Font("Arial", 1, 30));
      showResult(g);
    }
    g.dispose();
    bs.show();

  }
  

  public void showResult(final Graphics g) {
    if (Menu.greenClown.getScore() > Menu.blueClown.getScore()) {
      g.drawString("Green Clown Won With Score " + Menu.greenClown.getScore(),
          400, 100);
      g.drawImage(image, 600 - 67, 450, null);
      Logging.log("Green Clown Won With Score" + Menu.greenClown.getScore(), "info");
    } else if (Menu.greenClown.getScore() < Menu.blueClown.getScore()) {
      g.drawString("Blue Clown Won With Score : " + Menu.blueClown.getScore(),
          400, 100);
      g.drawImage(image2, 600 - 67, 450, null);
      Logging.log("Blue Clown Won With Score" + Menu.blueClown.getScore(), "info");
    } else {
      g.drawString("             Tie!", 400, 100);
      Logging.log("Game ended with Tie", "info");
    }
  }
  public static void main(final String args[]) {
    Logging.log("Game started", "info");
    try{
    new Game();
    }catch(Exception e){
   Logging.log("SomeThing went wrong", "warn");
    }
  }
}
