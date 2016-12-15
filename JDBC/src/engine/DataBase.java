package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import extractInformation.UsedDataBase;
//hello
public class DataBase implements ImethodOfDataBase {
    private String databaseName;
    private ArrayList<String> tablesNames = new ArrayList<String>();
    private ArrayList<String> batch = new ArrayList<String>();
    private static DataBase instance = new DataBase();
    private IFile fileWriter = new XmlFile();
    public static  boolean isDataBaseHere=false;

    private DataBase() {
    }

    public static DataBase getinstance() {
	return instance;
    }

    public void setFileWriter(String protocol) {
	if (protocol.equalsIgnoreCase("xmldb")) {
	    fileWriter = new XmlFile();
	} else {
	    fileWriter = new JsonFile();
	}
    }

    public void setdatabaseName(String dbName) {
	this.databaseName = dbName;
	tablesNames = getTablesName();
    }

    public String getdatabaseName() {
	return databaseName;
    }

    public ArrayList<String> getTables() {
	return tablesNames;
    }

    public void addTable(String tableName) {
	tablesNames.add(tableName);
    }

    public void removeTable(String tableName) {
	tablesNames.remove(tableName);
    }

    private ArrayList<String> getTablesName() {
	ArrayList<String> tables = new ArrayList<String>();
	String tmp = System.getProperty("java.io.tmpdir");
	File databaseDIR = new File(tmp + "DBMS" + File.separator + databaseName);
	File[] listOfFiles = databaseDIR.listFiles();
	if (listOfFiles != null) {
	    for (int i = 0; i < listOfFiles.length; i++) {
		String filename = listOfFiles[i].getName();
		if (filename.endsWith(".xml")) {
		    tables.add(filename.replaceAll(".xml", ""));
		} else if(filename.endsWith(".json")) {
		    tables.add(filename.replaceAll(".json", ""));
		}
	    }
	}
	return tables;
    }

    public void create(String databaseName) throws IOException {
	String tmp = System.getProperty("java.io.tmpdir");
	final File DBMSdir = new File(tmp + "DBMS");
	DBMSdir.mkdir();
	final File dir = new File(DBMSdir + File.separator + databaseName);
	if (dir.exists()) {
	    isDataBaseHere=true;
	   // DropDateBase.drop(databaseName);
	}
	dir.mkdir();
	File batchdir = new File(tmp + "batches");
	batchdir.mkdir();
	File batch = new File(batchdir + File.separator + databaseName + ".txt");
	PrintWriter out = null;
	try {
	    out = new PrintWriter(batch);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	out.close();
	System.out.println("Data Base Created");
    }

    @Override
    public void dropTable(String tableName) {
	try {
	    String tmp = System.getProperty("java.io.tmpdir");
	    File xml = new File(tmp + "DBMS" + File.separator + databaseName + File.separator + tableName + ".xml");
	    tablesNames.remove(tableName);
	    final Path path = xml.toPath();
	    Files.delete(path);
	    File xmldtd = new File(
		    tmp + "DBMS" + File.separator + databaseName + File.separator + tableName + "Schemma.dtd");
	    final Path dtdpath = xmldtd.toPath();
	    Files.delete(dtdpath);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void createTable(String tableName, String[] cols) throws SQLException {
	for (int i = 0; i < tablesNames.size(); i++) {
	    if (tableName.equals(tablesNames.get(i))) {
		throw new RuntimeException();
	    }
	}
	ArrayList<String> colsName = new ArrayList<String>();
	ArrayList<String> colstype = new ArrayList<String>();
	for (int j = 0; j < cols.length; j++) {
	    colsName.add(cols[j].split(" ")[0]);
	    colstype.add(cols[j].split(" ")[1]);
	}
	
	try {
	    fileWriter.write(new ArrayList<ArrayList<String>>(), databaseName, tableName, colsName, colstype);
	} catch (TransformerConfigurationException | ParserConfigurationException
		| TransformerFactoryConfigurationError e) {
	    throw new RuntimeException();
	}

    }

}