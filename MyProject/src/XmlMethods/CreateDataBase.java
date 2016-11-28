package XmlMethods;

import java.io.File;

public class CreateDataBase {
    public void createDataBase(final String name) {
	final File dir = new File(name);
	dir.mkdir();
	System.out.println("Data Base Created");
    }

}
