package View;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

import javax.swing.JFileChooser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.ObjectsHandler;
import Model.GameObject;

//import eg.edu.alexu.csd.oop.paint.model.AllShape;

public class Save {
	private final LinkedList<GameObject> gameObjects;
//	Handler handler;
	private final JFileChooser chooser = new JFileChooser();

	public Save(final ObjectsHandler handler) {
		gameObjects = handler.objects;
//		this.handler = handler;
	}

	public void saveGame(final String dir) {

		final JSONObject obj = new JSONObject();
		try {

			obj.put("num", new Integer(gameObjects.size()));
			Integer teet = 0;
			for (int i = 0; i < gameObjects.size(); i++) {
				teet = teet + 1;
				final JSONArray temp = new JSONArray();
				temp.add(gameObjects.get(i).id.toString());
				temp.add(Integer.valueOf(gameObjects.get(i).x).toString());
				temp.add(Integer.valueOf(gameObjects.get(i).y).toString());
				temp.add(Integer.valueOf(gameObjects.get(i).collisionY).toString());
				if(gameObjects.get(i).direction == null){
					temp.add("null");
				}else{
					temp.add(gameObjects.get(i).direction.toString());

				}

				if (gameObjects.get(i).collision == true) {
					temp.add("true");
				} else {
					temp.add("false");
				}

				temp.add(Float.valueOf(gameObjects.get(i).velX).toString());
				temp.add(Float.valueOf(gameObjects.get(i).velY).toString());
				if (gameObjects.get(i).color != null) {
					temp.add(colorToString(gameObjects.get(i).color));
				} else {
					temp.add("null");
				}
				temp.add(gameObjects.get(i).difficulty);
				temp.add(gameObjects.get(i).playerStick);

				obj.put(convertIntegerToString(teet).toString(), temp);
			}
			final File fout = new File(dir + ".json");
			final FileOutputStream fo = new FileOutputStream(fout, false);
			final OutputStreamWriter x = new OutputStreamWriter(fo);
			final BufferedWriter bw = new BufferedWriter(x);
			bw.append(obj.toString());
			bw.newLine();
			bw.close();
			// file.write(obj.toString());
			// file.flush();
			// file.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	String convertIntegerToString(final Integer x) {
		return x.toString();
	}

	private static String colorToString(final Color c) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(Integer.toHexString(c.getRGB() & 0xFFFFFF));
		while (buffer.length() < 6)
			buffer.insert(0, '0');
		buffer.insert(0, '#');
		return buffer.toString();
	}

}
