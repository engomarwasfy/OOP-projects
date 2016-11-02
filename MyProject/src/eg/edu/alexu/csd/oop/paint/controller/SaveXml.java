package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Rectangle;
import java.awt.Shape;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.model.AllShape;

public class  SaveXml {
	private static final String SERIALIZED_FILE_NAME="save.xml";
public  static  void save(ArrayList<AllShape>shapes,String dir){
	

		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dir+".xml")));
		for(AllShape s:shapes){
		encoder.writeObject(s);
		}
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		
		encoder.close();
		
	
	
}

}