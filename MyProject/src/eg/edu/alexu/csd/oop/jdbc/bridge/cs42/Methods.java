package eg.edu.alexu.csd.oop.jdbc.bridge.cs42;

import java.io.File;

public class Methods {
    public static File getXmlFile(String dataBase, String table) {
	return new File(dataBase + "\\" + table + ".xml");
    }
}
