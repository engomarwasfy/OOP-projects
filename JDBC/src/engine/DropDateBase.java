package engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import extractInformation.UsedDataBase;

public class DropDateBase {
    public static void drop(String dbName) throws IOException {
	String tmp = System.getProperty("java.io.tmpdir");
	File db = new File(tmp + "DBMS" + File.separator + dbName);
	final Path path = db.toPath();
	if (db.isDirectory()) {
	    for (final File c : db.listFiles()) {
		Files.delete(c.toPath());
	    }
	    Files.delete(path);
	}
    }
}