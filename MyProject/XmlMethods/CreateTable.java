package XmlMethods;

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
  public void createTable(final String dataBaseName, final String xmlName,final String []col) {
    final DocumentBuilderFactory icFactory = DocumentBuilderFactory
        .newInstance();
    DocumentBuilder icBuilder;
    try {
      icBuilder = icFactory.newDocumentBuilder();
      final Document doc = icBuilder.newDocument();
      final DOMSource source = new DOMSource(doc);
      final StreamResult n = new StreamResult(
          dataBaseName + "\\" + xmlName + ".xml");
      Transformer transformer;
      transformer = TransformerFactory.newInstance().newTransformer();
       Element mainRootElement;
       mainRootElement = doc.createElement(xmlName);
       doc.appendChild(mainRootElement);
       final Element rowID = doc.createElement("rowID");
       mainRootElement.appendChild(rowID);
       for (int j = 0; j < col.length; j++) {
    	   final Element node = doc.createElement(col[j].split(" ")[0]);
    	   node.setAttribute("type", col[j].split(" ")[1]);
    	   node.appendChild(doc.createTextNode("dummy"));
    	   rowID.appendChild(node);
	}
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, n);
      SchemaFile.Dtd(UsedDataBase.getUsedDataBase(), xmlName,col );
      System.out.println("Table Created");
    } catch (final ParserConfigurationException e1) {
    	 System.out.println("sql command failed");
    } catch (final TransformerFactoryConfigurationError e) {
    	 System.out.println("sql command failed");
    } catch (final TransformerConfigurationException e) {
    	 System.out.println("sql command failed");
    } catch (final TransformerException e) {
    	 System.out.println("sql command failed");
    } catch (final IOException e) {
    	 System.out.println("sql command failed");
	}
  }

}
