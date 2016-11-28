package XmlMethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import extractInformation.UsedDataBase;

public class SchemaFile {
    public static void Dtd(final String dataBaseName, final String tableName, final String[] data) throws IOException {
	final File file = new File(dataBaseName + "/" + tableName + "Schemma" + ".dtd");
	if (!file.exists()) {
	    file.createNewFile();
	}
	String test = "";
	for (int i = 0; i < data.length; i++) {
	    test = test.concat(data[i].split(" ")[0]);
	    if (i != data.length - 1) {
		test = test.concat(",");
	    }
	}
	final FileWriter fw = new FileWriter(file.getAbsoluteFile());
	final BufferedWriter bw = new BufferedWriter(fw);
	bw.write("<!ELEMENT " + tableName + " (" + "rowID" + ")" + "*" + ">");
	bw.newLine();
	bw.write("<!ELEMENT " + "rowID" + " (" + test + ")" + ">");
	bw.newLine();
	for (int i = 0; i < data.length; i++) {
	    bw.write("<!ELEMENT " + data[i].split(" ")[0] + " (#PCDATA)" + "*" + ">");
	    bw.newLine();
	}

	bw.close();
    }

    public static String read(final String dataBaseName, final String tableName) {
	BufferedReader br = null;
	String text = "";
	try {
	    String sCurrentLine;
	    br = new BufferedReader(new FileReader(dataBaseName + "/" + tableName + "schemma" + ".dtd"));
	    int i = 0;
	    String colLine = "";
	    while ((sCurrentLine = br.readLine()) != null) {
		i++;
		if (i == 2) {
		    colLine = sCurrentLine;
		}
	    }
	    for (int j = 0; j < colLine.length(); j++) {
		if (colLine.charAt(j) == '(') {
		    int y = j + 1;
		    while (colLine.charAt(y) != ')') {
			text = text.concat(String.valueOf(colLine.charAt(y)));
			y++;
		    }
		}
	    }
	} catch (final IOException e) {
	    System.out.println("sql command failed");
	} finally {
	    try {
		if (br != null)
		    br.close();
	    } catch (final IOException ex) {
		System.out.println("sql command failed");
	    }
	}
	return text;
    }
}
