package XmlMethods;

import java.io.File;
import java.io.IOException;

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

import extractInformation.UsedDataBase;

public class CreateTable {
    public void createTable(final String dataBaseName, final String xmlName, final String[] col)
	    throws TransformerException, IOException, ParserConfigurationException {
	final DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder icBuilder;

	icBuilder = icFactory.newDocumentBuilder();
	final Document doc = icBuilder.newDocument();
	final DOMSource source = new DOMSource(doc);
	final StreamResult n = new StreamResult(dataBaseName + File.separator + xmlName + ".xml");
	Transformer transformer;
	transformer = TransformerFactory.newInstance().newTransformer();
	Element mainRootElement;
	mainRootElement = doc.createElement(xmlName);
	doc.appendChild(mainRootElement);
	final Element rowID = doc.createElement("rowID");
	mainRootElement.appendChild(rowID);
	for (int j = 0; j < col.length; j++) {
	    final Element node = doc.createElement(col[j].split(" ")[0]);
	    node.appendChild(doc.createTextNode(col[j].split(" ")[1]));
	    rowID.appendChild(node);
	}
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, xmlName + "Schemma.dtd");
	transformer.transform(source, n);
	SchemaFile.Dtd(UsedDataBase.getUsedDataBase(), xmlName, col);
	System.out.println("Table Created");

    }
}
