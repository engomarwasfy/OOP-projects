package engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DropDateBase {
  public static void drop(final String dbName) throws IOException {
    final String tmp = System.getProperty("java.io.tmpdir");
    final File db = new File(tmp + "DBMS" + File.separator + dbName);
    final Path path = db.toPath();
    if (db.isDirectory()) {
      for (final File c : db.listFiles()) {
        Files.delete(c.toPath());
      }
      Files.delete(path);
    }
  }
}