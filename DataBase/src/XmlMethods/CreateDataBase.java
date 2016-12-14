package XmlMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreateDataBase {
    public void createDataBase(final String name) {
	String tmp = System.getProperty("java.io.tmpdir");
	final File DBMSdir = new File(tmp + "DBMS");
	DBMSdir.mkdir();
	final File dir = new File(DBMSdir + File.separator + name);
	dir.mkdir();
	File batchdir = new File(tmp + "batches");
	batchdir.mkdir();
	File batch = new File(batchdir + File.separator + name + ".txt");
	PrintWriter out = null;
	try {
		out = new PrintWriter(batch);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	out.close();
	System.out.println("Data Base Created");
    }

}
