package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Shape;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.model.AllShape;

public class LoadXml {
    private static final String SERIALIZED_FILE_NAME = "save.xml";
    private ArrayList<AllShape> shapes = new ArrayList<AllShape>();

    public ArrayList<AllShape> load(String dir) {

	XMLDecoder decoder = null;
	try {
	    decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(dir)));
	} catch (Exception e) {
	    System.out.println("ERROR: File .xml not found");
	}
	try {
	    shapes = (ArrayList<AllShape>) decoder.readObject();
//	    while (true) {
//		AllShape s = (AllShape) decoder.readObject();
//		shapes.add(s);
//	    }
	} catch (Exception e) {
	}
	return shapes;

    }
}
