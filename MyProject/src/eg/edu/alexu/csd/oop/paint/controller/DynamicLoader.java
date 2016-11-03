package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Color;
import java.io.File;

import eg.edu.alexu.csd.oop.paint.model.AllShape;

public class DynamicLoader {
    public AllShape getShape(String url, float x1, float y1, float x2, float y2, Color fillColor, Color drawColor)
	    throws ClassNotFoundException, InstantiationException, IllegalAccessException {

	ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
	MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
	String shapeName = "";
	for (int i = url.length() - 7; i > 0; i--) {
	    if (url.charAt(i) != '\\') {
		shapeName = url.charAt(i) + shapeName;
	    } else {
		break;
	    }
	}
	Class myObjectClass = classLoader.loadClass(url, "eg.edu.alexu.csd.oop.paint.model." + shapeName, shapeName);
	AllShape object = (AllShape) myObjectClass.newInstance();
	classLoader = new MyClassLoader(parentClassLoader);
	myObjectClass = classLoader.loadClass(url, "eg.edu.alexu.csd.oop.paint.model." + shapeName, shapeName);
	object = (AllShape) myObjectClass.newInstance();
	object.init(x1, y1, x2, y2, fillColor, drawColor);
	CopyFile cp = new CopyFile();
	File x = new File("");
	String s = x.getAbsolutePath();
	cp.copy(url, s + "\\bin\\eg\\edu\\alexu\\csd\\oop\\paint\\model\\" + shapeName + ".class");
	return object;

    }

}
