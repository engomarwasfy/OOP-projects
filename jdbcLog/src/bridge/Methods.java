package bridge;

import java.io.File;

public class Methods {
  public static File getXmlFile(final String dataBase, final String table) {
    return new File(dataBase + "\\" + table + ".xml");
  }
}
