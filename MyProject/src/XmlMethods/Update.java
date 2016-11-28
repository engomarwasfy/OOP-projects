package XmlMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import extractInformation.UsedDataBase;

public class Update {

    public void update(final File xml, final String[] sets, final String condition)
	    throws SAXException, IOException, ParserConfigurationException {
	final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	final Document doc = docBuilder.parse(xml.getPath());
	// // loop the staff child node

	final XmlParsingMethods x = new XmlParsingMethods();
	int[] indexes = null;
	if (condition == null) {
	    indexes = new int[doc.getElementsByTagName("rowID").getLength()];
	    for (int i = 0; i < indexes.length; i++) {
		indexes[i] = i;
	    }

	} else {
	    final ArrayList<Integer> index = x.parseCondition(xml, condition);
	    indexes = new int[index.size()];
	    for (int i = 0; i < index.size(); i++) {
		indexes[i] = index.get(i);
	    }
	}
	try {

	    for (int j = 0; j < indexes.length; j++) {
		Node firstNode = doc.getElementsByTagName("rowID").item(0);
		final Node staff = doc.getElementsByTagName("rowID").item(indexes[j]);
		final NodeList list = staff.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
		    for (int k = 0; k < sets.length; k++) {
			final String[] arr = sets[k].split("=");
			if (!isNumber(arr[1])) {
			    if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
				arr[1] = arr[1].substring(1, arr[1].length() - 1);
			    }

			}
			if (checkColFound(xml.getName().substring(0, xml.getName().indexOf('.')), arr[0]) == -1) {
			    throw new RuntimeException();
			}
			if (arr[0].equalsIgnoreCase(list.item(i).getNodeName())) {
			    String type = firstNode.getChildNodes().item(i).getTextContent();
			    list.item(i).setTextContent(arr[1]);
			    if ((type.equalsIgnoreCase("int") && isNumber(arr[1]))
				    || (type.equalsIgnoreCase("varchar") && isValidName(arr[1]))) {
				list.item(i).setTextContent(arr[1]);
			    } else {
				throw new RuntimeException();
			    }

			}
		    }
		}
	    }
	    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(doc);
	    final StreamResult result = new StreamResult(xml);
	    transformer.transform(source, result);
	} catch (TransformerFactoryConfigurationError | TransformerException e) {
	    // TODO Auto-generated catch block
	    System.out.println("sql command failed");
	}

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
    public int checkColFound(final String tableName, final String colName) {
	final String database = UsedDataBase.getUsedDataBase();
	final String[] coulmsOfTable = SchemaFile.read(database, tableName).split(",");
	for (int i = 0; i < coulmsOfTable.length; i++) {
	    if (colName.equalsIgnoreCase(coulmsOfTable[i])) {
		return 2 * i + 1;
	    }
	}
	return -1;
    }

    /********************************************************************************************************/
}
