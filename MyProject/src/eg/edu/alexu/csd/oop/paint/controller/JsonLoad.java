package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import eg.edu.alexu.csd.oop.paint.model.AllShape;
import eg.edu.alexu.csd.oop.paint.model.CircleImp;
import eg.edu.alexu.csd.oop.paint.model.ElipseImp;
import eg.edu.alexu.csd.oop.paint.model.LineImp;
import eg.edu.alexu.csd.oop.paint.model.RectangleImp;
import eg.edu.alexu.csd.oop.paint.model.SquareImp;

public class JsonLoad {

    @SuppressWarnings("unchecked")
    public ArrayList<AllShape> load(String dir) throws JsonSyntaxException, JsonIOException, IOException {
	ArrayList<AllShape> arr = new ArrayList<>();
	ArrayList<AllShape> finalShapes = new ArrayList<>();
	Gson gson = new Gson();
	StringBuilder json = new StringBuilder() ;
	FileInputStream fis = new FileInputStream(dir);

	// Construct BufferedReader from InputStreamReader
	InputStreamReader x = new InputStreamReader(fis);
	BufferedReader br = new BufferedReader(x);

	String line = null;
	while ((line = br.readLine()) != null) {
	    json.append(line);
	}
	br.close();
	arr = gson.fromJson(json.toString(), new TypeToken<ArrayList<RectangleImp>>() {
	}.getType());
	for (AllShape s : arr) {
	    float x1 = s.getX1();
	    float x2 = s.getX2();
	    float y1 = s.getY1();
	    float y2 = s.getY2();
	    Color fillColor = s.getFillColor();
	    Color drawColor = s.getDrawColor();
	    System.out.println(s.getShapeName());
	    if (s.getShapeName().equals("rectangle")) {
		finalShapes.add(s);
	    } else if (s.getShapeName().equals("elipse")) {
		System.out.println("elipse");
		finalShapes.add(new ElipseImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getShapeName().equals("circle")) {
		finalShapes.add(new CircleImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getShapeName().equals("square")) {
		finalShapes.add(new SquareImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getShapeName().equals("line")) {
		finalShapes.add(new LineImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getShapeName().equals("triangle")) {
		DynamicLoader d = new DynamicLoader();
		try {
		    finalShapes.add(d.getShape(Surface.url, x1, y1, x2, y2, fillColor, drawColor));
		} catch (Exception e) {

		}
	    }
	    return finalShapes;

	}
	return finalShapes;
    }
}
