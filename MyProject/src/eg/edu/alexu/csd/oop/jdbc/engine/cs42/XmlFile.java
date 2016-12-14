package eg.edu.alexu.csd.oop.jdbc.engine.cs42;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.UsedDataBase;

public class XmlFile implements IFile {

    public ArrayList<ArrayList<String>> read(String databaseName, String tableName)
	    throws ParserConfigurationException, SAXException, IOException {
	String tmp = System.getProperty("java.io.tmpdir");
	File xml = new File(tmp + "DBMS" + File.separator + databaseName + File.separator + tableName + ".xml");
	ArrayList<ArrayList<String>> selected = new ArrayList<ArrayList<String>>();
	final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	final DocumentBuilder db = dbf.newDocumentBuilder();
	final Document doc = db.parse(xml);
	doc.getDocumentElement();
	Element root = doc.getDocumentElement();
	NodeList nodes = doc.getElementsByTagName("rowID");
	int numOfCols = 0;
	int x = 1;
	while (nodes.item(0).getChildNodes().item(x) != null) {
	    x += 2;
	    numOfCols++;
	}
	final int numOfRows = nodes.getLength();
	for (int i = 1; i < numOfRows; i++) {
	    ArrayList<String> row = new ArrayList<String>();
	    for (int j = 0; j < numOfCols; j++) {
		row.add(nodes.item(i).getChildNodes().item(2 * j + 1).getTextContent());
	    }
	    selected.add(row);
	}
	return selected;

    }

    public void write(ArrayList<ArrayList<String>> data, String dataBaseName, String xmlName, ArrayList<String> cols,
	    ArrayList<String> types) throws ParserConfigurationException, TransformerConfigurationException,
	    TransformerFactoryConfigurationError {
	DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder icBuilder = icFactory.newDocumentBuilder();
	Document doc = icBuilder.newDocument();
	DOMSource source = new DOMSource(doc);
	String tmp = System.getProperty("java.io.tmpdir");
	File xml = new File(tmp + "DBMS" + File.separator + dataBaseName + File.separator + xmlName + ".xml");
	StreamResult n = new StreamResult(xml);
	Transformer transformer = TransformerFactory.newInstance().newTransformer();
	Element mainRootElement = doc.createElement(xmlName);
	doc.appendChild(mainRootElement);
	Element rowID = doc.createElement("rowID");
	mainRootElement.appendChild(rowID);
	for (int j = 0; j < cols.size(); j++) {
	    final Element node = doc.createElement(cols.get(j));
	    node.appendChild(doc.createTextNode(types.get(j)));
	    rowID.appendChild(node);
	}
	for (int i = 0; i < data.size(); i++) {
	    rowID = doc.createElement("rowID");
	    for (int j = 0; j < data.get(i).size(); j++) {
		Element root = doc.getDocumentElement();
		NodeList rows = doc.getElementsByTagName("rowID");
		Element node = doc.createElement(cols.get(j));
		node.appendChild(doc.createTextNode(data.get(i).get(j)));
		rowID.appendChild(node);
		root.appendChild(rowID);
	    }
	}
	final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer1 = null;
	transformer1 = transformerFactory.newTransformer();
	final DOMSource source1 = new DOMSource(doc);
	final StreamResult result = new StreamResult(xml.getAbsolutePath());
	transformer1.setOutputProperty(OutputKeys.INDENT, "yes");
	try {
	    transformer1.transform(source1, result);
	} catch (TransformerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public ArrayList<String> getcols(String dataBaseName, String tableName) throws ParserConfigurationException, SQLException {
	ArrayList<String> cols = new ArrayList<String>();
	try {
	    String tmp = System.getProperty("java.io.tmpdir");
	    File xml = new File(tmp + "DBMS" + File.separator + dataBaseName + File.separator + tableName + ".xml");
	    final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    final DocumentBuilder db = dbf.newDocumentBuilder();
	    final Document doc = db.parse(xml);
	    doc.getDocumentElement();
	    Element root = doc.getDocumentElement();
	    NodeList nodes = doc.getElementsByTagName("rowID");
	    int numOfCols = 0;
	    int x = 1;
	    while (nodes.item(0).getChildNodes().item(x) != null) {
		x += 2;
		numOfCols++;
	    }
	    for (int j = 0; j < numOfCols; j++) {
		cols.add(nodes.item(0).getChildNodes().item(2 * j + 1).getNodeName().toLowerCase());
	    }
	} catch (SAXException | IOException e) {
	    final SQLException ex = new SQLException("not valid statment");
	      throw ex;
	}
	return cols;
    }

    @Override
    public ArrayList<String> getcolsTypes(String dataBaseName, String tableName) {
	ArrayList<String> types = new ArrayList<String>();
	try {
	    String tmp = System.getProperty("java.io.tmpdir");
	    File xml = new File(tmp + "DBMS" + File.separator + dataBaseName + File.separator + tableName + ".xml");
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document doc = docBuilder.parse(xml);
	    doc.getDocumentElement();
	    Element root = doc.getDocumentElement();
	    NodeList nodes = doc.getElementsByTagName("rowID");
	    int numOfCols = 0;
	    int x = 1;
	    while (nodes.item(0).getChildNodes().item(x) != null) {
		x += 2;
		numOfCols++;
	    }
	    for (int j = 0; j < numOfCols; j++) {
		types.add(nodes.item(0).getChildNodes().item(2 * j + 1).getTextContent());
	    }
	} catch (ParserConfigurationException | SAXException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return types;
    }

}
