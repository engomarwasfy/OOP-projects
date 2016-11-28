package XmlMethods;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import extractInformation.UsedDataBase;

public class InsertIntoTable {

    /**
     * []col the columns that will be filled with data "unfilled = null". []data
     * the data that will be added depend on []col.
     * 
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    public void insertIntoTable(final File xml, String[] col, final String[] data)
	    throws ParserConfigurationException, SAXException, IOException, TransformerException {

	if (col == null) {
	    String database = UsedDataBase.getUsedDataBase();
	    col = SchemaFile.read(database, xml.getName().substring(0, xml.getName().indexOf('.'))).split(",");
	}
	if (col.length < data.length) {
	    throw new RuntimeException();
	}
	if (!Checker.checkColsFound(xml.getName().substring(0, xml.getName().indexOf('.')), col)) {
	    throw new RuntimeException();
	}
	final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	final DocumentBuilder db = dbf.newDocumentBuilder();
	final Document doc = db.parse(xml);
	insertRow(doc, xml, col, data);

    }

    /**
     * @throws TransformerException
     ************************************************************************************************************/
    private void insertRow(final Document doc, final File xml, final String[] col, final String[] data)
	    throws TransformerException {

	String database = UsedDataBase.getUsedDataBase();
	String[] colOfTable = SchemaFile.read(database, xml.getName().substring(0, xml.getName().indexOf('.')))
		.split(",");
	final Element rowID = doc.createElement("rowID");
	final Element root = doc.getDocumentElement();
	NodeList rows = doc.getElementsByTagName("rowID");
	Node firstNode = rows.item(0);
	for (int i = 0; i < colOfTable.length; i++) {
	    int index = checkColFound(colOfTable[i], col);
	    if (index != -1) {
		String type = firstNode.getChildNodes().item(2 * i + 1).getTextContent();
		if ((type.equalsIgnoreCase("int") && isNumber(data[index]))
			|| (type.equalsIgnoreCase("varchar") && isValidName(data[index]))) {
		    root.appendChild(getData(doc, data[index], colOfTable[i], rowID));
		} else {
		    throw new RuntimeException();
		}
	    } else {
		root.appendChild(getData(doc, "null", colOfTable[i], rowID));
	    }
	}
	final TransformerFactory tf = TransformerFactory.newInstance();
	final Transformer transformer = tf.newTransformer();
	final DOMSource source = new DOMSource(doc);
	final StreamResult result = new StreamResult(new File(xml.getAbsolutePath()));
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	transformer.transform(source, result);

    }

    /**************************************************************************************************************/
    private Node getData(final Document doc, final String colomn, final String colName, final Element rowID) {
	rowID.appendChild(getCompanyElements(doc, rowID, colName, colomn));
	return rowID;
    }

    /**************************************************************************************************************/
    private Node getCompanyElements(final Document doc, final Element element, final String name, final String value) {
	final Element node = doc.createElement(name);
	node.appendChild(doc.createTextNode(value));
	return node;
    }

    /**************************************************************************************************************/
    public boolean isNumber(String word) {
	return Pattern.matches("^[0-9]*$", word);
    }

    /**************************************************************************************************************/
    public boolean isValidName(String name) {
	String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
	return Pattern.matches(regex, name);
    }

    /**************************************************************************************************************/
    public int checkColFound(String col, String[] cols) {
	for (int i = 0; i < cols.length; i++) {
	    if (col.equals(cols[i])) {
		return i;
	    }
	}
	return -1;
    }
    /**************************************************************************************************************/
}
