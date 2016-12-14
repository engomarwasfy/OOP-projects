package eg.edu.alexu.csd.oop.jdbc.engine.cs42;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.jdbc.eval.cs42.eval;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.UsedDataBase;
import eg.edu.alexu.csd.oop.jdbc.xmlMethods.cs42.SchemaFile;

public class XmlParsingMethods {

    private IFile filewriter = new XmlFile();

    public void setFileWriter(String protocol) {
	if (protocol.equalsIgnoreCase("xmldb")) {
	    filewriter = new XmlFile();
	} else {
	    filewriter = new JsonFile();
	}
    }

    /**
     * @throws SQLException
     ******************************************************************************************************/
    public ArrayList<Integer> parseCondition(String databaseName, String tableName, final String condition)
	    throws SQLException {
	ArrayList<ArrayList<String>> data = null;
	try {
	    data = filewriter.read(databaseName, tableName);
	} catch (ParserConfigurationException | SAXException | IOException e) {
	    throw new RuntimeException();
	}
	ArrayList<Integer> indexes = new ArrayList<Integer>();
	if (condition == null) {
	    for (int i = 0; i < data.size(); i++) {
		indexes.add(i);
	    }
	} else if (condition.contains("=")) {
	    indexes = equal(data, condition,tableName);
	} else if (condition.contains(">")) {
	    indexes = greaterThan(data, condition,tableName);
	} else if (condition.contains("<")) {
	    indexes = lessThan(data, condition,tableName);
	} else if (condition.equals("'true'")) {
	    for (int i = 0; i < data.size(); i++) {
		indexes.add(i);
	    }
	} else {
	    throw new RuntimeException();
	}
	return indexes;
    }

    /**
     * @throws SQLException
     ******************************************************************************************************/
    public int checkColFound(final String tableName, final String colName) throws SQLException {
	final String database = UsedDataBase.getUsedDataBase();
	ArrayList<String> coulmsOfTable = null;
	try {
	    coulmsOfTable = filewriter.getcols(database, tableName);
	} catch (ParserConfigurationException e) {

	}
	for (int i = 0; i < coulmsOfTable.size(); i++) {
	    if (colName.toLowerCase().equalsIgnoreCase(coulmsOfTable.get(i))) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * @throws SQLException
     ******************************************************************************************************/
    public ArrayList<Integer> greaterThan(ArrayList<ArrayList<String>> data, final String condition , String tableName)
	    throws SQLException {
	ArrayList<Integer> indexes = new ArrayList<Integer>();
	String[] arr = condition.split(">");
	int indexOfCol = checkColFound(tableName, arr[0]);
	if (indexOfCol != -1) {
	    if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
		arr[1] = arr[1].substring(1, arr[1].length() - 1);
	    }
	    for (int i = 0; i < data.size(); i++) {
		if (data.get(i).get(indexOfCol).compareTo(arr[1]) >= 1) {
		    indexes.add(i);
		}
	    }
	} else {
	    throw new RuntimeException();
	}
	return indexes;

    }

    /**
     * @throws SQLException
     ******************************************************************************************************/
    public ArrayList<Integer> lessThan(ArrayList<ArrayList<String>> data, final String condition,String tableName) throws SQLException {
	ArrayList<Integer> indexes = new ArrayList<Integer>();
	String[] arr = condition.split("<");
	int indexOfCol = checkColFound(tableName, arr[0]);
	if (indexOfCol != -1) {
	    if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
		arr[1] = arr[1].substring(1, arr[1].length() - 1);
	    }
	    for (int i = 0; i < data.size(); i++) {
		if (data.get(i).get(indexOfCol).compareTo(arr[1]) <= -1) {
		    indexes.add(i);
		}
	    }
	} else {
	    throw new RuntimeException();
	}
	return indexes;
    }

    /**
     * @throws SQLException
     ******************************************************************************************************/
    public ArrayList<Integer> equal(ArrayList<ArrayList<String>> data, final String condition,String tableName) throws SQLException {
	ArrayList<Integer> indexes = new ArrayList<Integer>();
	String[] arr = condition.split("=");
	int indexOfCol = checkColFound(tableName, arr[0]);
	if (indexOfCol != -1) {
	    if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
		arr[1] = arr[1].substring(1, arr[1].length() - 1);
	    }
	    for (int i = 0; i < data.size(); i++) {
		if (data.get(i).get(indexOfCol).compareTo(arr[1]) == 0) {
		    indexes.add(i);
		}
	    }
	} else {
	    throw new RuntimeException();
	}
	return indexes;

    }
    /******************************************************************************************************/

}
