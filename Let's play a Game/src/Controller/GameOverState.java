package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import View.Game;
import View.Menu;

public class GameOverState extends AbstractState {

	public void execute(Graphics g, Menu menu,Game game) {
		g.setColor(Color.WHITE);
	      g.drawRect(525, 350, 150, 64);
	      g.setFont(new Font("Arial", 1, 20));
	      g.drawString("Back to Menu", 540, 390);
	      g.setFont(new Font("Arial", 1, 30));
	      game.showResult(g);
		
	}

	

}
