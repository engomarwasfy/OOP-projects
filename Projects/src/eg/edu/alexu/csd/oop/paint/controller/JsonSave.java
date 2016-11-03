package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import eg.edu.alexu.csd.oop.paint.model.AllShape;
import eg.edu.alexu.csd.oop.paint.model.CircleImp;
import eg.edu.alexu.csd.oop.paint.model.ElipseImp;
import eg.edu.alexu.csd.oop.paint.model.LineImp;
import eg.edu.alexu.csd.oop.paint.model.RectangleImp;
import eg.edu.alexu.csd.oop.paint.model.SquareImp;

public class JsonSave {
    public void save(ArrayList<AllShape> shapes, String dir) throws JsonIOException, IOException, NoSuchMethodException,
	    SecurityException, IllegalArgumentException, InvocationTargetException {

	Gson gson = new Gson();
	DynamicLoader d = new DynamicLoader();
	AllShape rectangle = new RectangleImp();
	AllShape elipse = new ElipseImp();
	AllShape circle = new CircleImp();
	AllShape square = new SquareImp();
	AllShape line = new LineImp();
	AllShape tri = null;

	ArrayList<AllShape> newShapes = new ArrayList<AllShape>();
	// 1. Java object to JSON, and save into a file
	for (AllShape s : shapes) {
	    float x1 = s.getX1();
	    float x2 = s.getX2();
	    float y1 = s.getY1();
	    float y2 = s.getY2();
	    Color fillColor = s.getFillColor();
	    Color drawColor = s.getDrawColor();
	    if (s.getClass() == rectangle.getClass()) {
		newShapes.add(new RectangleImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getClass() == elipse.getClass()) {
		newShapes.add(new ElipseImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getClass() == circle.getClass()) {
		newShapes.add(new CircleImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getClass() == square.getClass()) {
		newShapes.add(new SquareImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else if (s.getClass() == line.getClass()) {
		newShapes.add(new LineImp(x1, y1, x2, y2, fillColor, drawColor));
	    } else {
		try {
		    newShapes.add(d.getShape(Surface.url, x1, y1, x2, y2, fillColor, drawColor));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }

	}
	String jsonInString = gson.toJson(newShapes);
	File fout = new File(dir + ".txt");
	FileOutputStream fo = new FileOutputStream(fout, false);
	OutputStreamWriter x = new OutputStreamWriter(fo);
	BufferedWriter bw = new BufferedWriter(x);
	bw.append(jsonInString);
	bw.newLine();
	bw.close();
	System.out.println(jsonInString);
    }
}
