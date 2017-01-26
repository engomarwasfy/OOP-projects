package View;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFileChooser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Controller.ID;
import Controller.ObjectsHandler;
import Controller.State;
import Model.BlueClown;
import Model.GameObject;
import Model.GreenClown;
import Model.PlateBall;

public class LoadGame {
	LinkedList<GameObject> gameObjects;
	JFileChooser chooser = new JFileChooser();
	private int x, y, collisionY;
	private float velX, velY;
	private ID direction;
	private Color color;
	private boolean collision;
	private String playerStick, difficulty;
	public LoadGame(final ObjectsHandler handler, final Game game, final File file) throws ParseException {
		direction = null;
		game.gameStateEnum = State.Game;
		final JSONParser parser = new JSONParser();
		try {

			Object obj1 = null;
      try {
        obj1 = parser.parse(new FileReader(file));
      } catch (final org.json.simple.parser.ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
			final JSONObject jsonObject1 = (JSONObject) obj1;
			final long sizeOfLinkedlist = (long) jsonObject1.get("num");
			handler.objects.clear();
			for (Integer i = 1; i < sizeOfLinkedlist; i++) {
				final Integer number = i;
				final String n = number.toString();
				final JSONArray shap = (JSONArray) jsonObject1.get(n);
				final Iterator<String> iterator = shap.iterator();
				final String f1 = iterator.next();
				x = Integer.parseInt(iterator.next());
				y = Integer.parseInt(iterator.next());
				collisionY = Integer.parseInt(iterator.next());
				// direction = iterator.next();
				final String f2 = iterator.next();
				if (f2.equals("LeftUp")) {
					direction = ID.LeftUp;
				} else if (f2.equals("LeftDown")) {
					direction = ID.LeftDown;
				} else if (f2.equals("RightUp")) {
					direction = ID.RightUp;
				} else if (f2.equals("RightDown")) {
					direction = ID.RightDown;
				} else {
					// do nothing
				}
				final String f3 = iterator.next();
				if (f3.equalsIgnoreCase("true")) {
					collision = true;
				} else if (f3.equals("false")) {
					collision = false;
				} else {
					// do nothing
				}
				velX = Float.parseFloat(iterator.next());
				velY = Float.parseFloat(iterator.next());
				final String s = iterator.next();
				if(!s.equals("null") && s != null){
					new Color(0);
					color = Color.decode(s);
				} else{

				}


				difficulty = iterator.next();
				playerStick = iterator.next();
				/**
				 * plate
				 */
				if (f1.equals("Plate")) {
					final GameObject plate = new PlateBall(x, y, ID.Plate, direction, handler, difficulty);
					plate.collisionY = collisionY;
					plate.collision = collision;
					plate.velX = velX;
					plate.velY = velY;
					plate.color = color;
					plate.playerStick = playerStick;
					handler.addObject(plate);
				} else if (f1.equals("GreenClown")) {
					final GreenClown greenOne = GreenClown.getInstance(x, collisionY, direction, handler, game,0);
					greenOne.id = ID.GreenClown;
					greenOne.y = y;
					greenOne.collision = collision;
					greenOne.velX = velX;
					greenOne.velY = velY;
					greenOne.color = color;
					greenOne.difficulty = difficulty;
					greenOne.playerStick = playerStick;
					handler.addObject(greenOne);
				} else if (f1.equalsIgnoreCase("BlueClown")) {
					final BlueClown blueOne = BlueClown.getInstance(x, collisionY, direction, handler, game,0);
					blueOne.id = ID.BlueClown;
					blueOne.y = y;
					blueOne.collision = collision;
					blueOne.velX = velX;
					blueOne.velY = velY;
					blueOne.color = color;
					blueOne.difficulty = difficulty;
					blueOne.playerStick = playerStick;
					handler.addObject(blueOne);
				}
			}

			Game.pause = false;

		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}

	}

	}
