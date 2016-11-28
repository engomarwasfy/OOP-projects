package XmlMethods;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Delete {

  public void delete(final File inputFile, final int[] deleteThisRow) {
    try {
      final DocumentBuilderFactory docFactory = DocumentBuilderFactory
          .newInstance();
      final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      final Document doc = docBuilder.parse(inputFile);
      doc.getDocumentElement();
      final Element root = doc.getDocumentElement();
      final NodeList nodes = doc.getElementsByTagName("rowID");
      for (int i = 0; i < deleteThisRow.length; i++) {
        root.removeChild(nodes.item(deleteThisRow[i] - i));
      }
      final TransformerFactory transformerFactory = TransformerFactory
          .newInstance();
      Transformer transformer = null;
      transformer = transformerFactory.newTransformer();
      final DOMSource source = new DOMSource(doc);
      final StreamResult result = new StreamResult(inputFile.getAbsolutePath());
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, result);
    } catch (final Exception e) {
    	 System.out.println("sql command failed");
    }

  }
}
