package Controller;

import java.awt.Graphics;

import View.Game;
import View.Menu;

public class MenuState extends AbstractState {

	@Override
	public void execute(Graphics g, Menu menu, Game game) {
		 menu.render(g);
		
	}

}
