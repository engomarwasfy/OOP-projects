package Controller;

import java.awt.Graphics;

import View.Game;
import View.Menu;

public class GameState extends AbstractState{
	@Override
	public void execute(Graphics g, Menu menu, Game game) {
		// TODO Auto-generated method stub
		g.drawImage(game.ImagebackGround, 0, 0, null);
	     menu.handler.render(g);
		
	}

}
