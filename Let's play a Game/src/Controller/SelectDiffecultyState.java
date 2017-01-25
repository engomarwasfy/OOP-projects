package Controller;

import java.awt.Graphics;

import View.Game;
import View.Menu;

public class SelectDiffecultyState extends AbstractState {

	@Override
	public void execute(Graphics g, Menu menu,Game game) {
		// TODO Auto-generated method stub
		menu.render(g);
	}

}
