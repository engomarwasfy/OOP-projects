package eg.edu.alexu.csd.oop.jdbc.engine.cs42;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.UsedDataBase;
import eg.edu.alexu.csd.oop.jdbc.xmlMethods.cs42.SchemaFile;

public class DataBase implements ImethodOfDataBase {
    private String databaseName;
    private ArrayList<String> tablesNames = new ArrayList<String>();
    private ArrayList<String> batch = new ArrayList<String>();
    private static DataBase instance = new DataBase();
    private DataBase() {
    }
    
    public static DataBase getinstance() {
	return instance;
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
    
    private ArrayList<String> getTablesName()  {
	ArrayList<String> tables = new ArrayList<String>();
	String tmp = System.getProperty("java.io.tmpdir");
	File databaseDIR = new File(tmp + "DBMS" + File.separator + databaseName);
	File[] listOfFiles = databaseDIR.listFiles();
	if(listOfFiles != null) {
    	    for(int i = 0; i < listOfFiles.length; i++){
    		String filename = listOfFiles[i].getName();
    		if(filename.endsWith(".xml")||filename.endsWith(".XML")) {
    		    tables.add(filename.replaceAll(".xml", ""));
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
	if(dir.exists()) {
	    DropDateBase.drop(databaseName);
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
	    File xml = new File(tmp + "DBMS" + File.separator + databaseName + File.separator + tableName +".xml");
	    tablesNames.remove(tableName);
	    final Path path = xml.toPath();
	    Files.delete(path);
	    File xmldtd = new File(tmp + "DBMS" + File.separator + databaseName + File.separator + tableName + "Schemma.dtd");
	    final Path dtdpath = xmldtd.toPath();
	    Files.delete(dtdpath);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void createTable(String tableName, String[] cols) {
	for (int i = 0; i < tablesNames.size(); i++) {
	    if(tableName.equals(tablesNames.get(i))) {
		throw new RuntimeException();
	    }
	}
	try {
	    final DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder icBuilder = icFactory.newDocumentBuilder();
	    final Document doc = icBuilder.newDocument();
	    final DOMSource source = new DOMSource(doc);
	    String tmp = System.getProperty("java.io.tmpdir");
	    File xml = new File(tmp + "DBMS" + File.separator + databaseName + File.separator + tableName+".xml");
	    final StreamResult n = new StreamResult(xml);
	    Transformer transformer = TransformerFactory.newInstance().newTransformer();
	    Element mainRootElement = doc.createElement(tableName);
	    doc.appendChild(mainRootElement);
	    final Element rowID = doc.createElement("rowID");
	    mainRootElement.appendChild(rowID);
	    for (int j = 0; j < cols.length; j++) {
	        final Element node = doc.createElement(cols[j].split(" ")[0]);
	        node.appendChild(doc.createTextNode(cols[j].split(" ")[1]));
	        rowID.appendChild(node);
	    }
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, tableName + "Schemma.dtd");
	    transformer.transform(source, n);
	    SchemaFile.Dtd(UsedDataBase.getUsedDataBase(), tableName, cols);
	    System.out.println("Table Created");
	} catch (DOMException | IllegalArgumentException | ParserConfigurationException
		| TransformerFactoryConfigurationError | TransformerException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    
    
    
}