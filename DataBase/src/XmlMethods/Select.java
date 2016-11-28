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

public class Select {

  public static String[][] selectAll(final File xml) {
    String[][] selected = null;
    try {
      final DocumentBuilderFactory docFactory = DocumentBuilderFactory
          .newInstance();
      final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      final Document doc = docBuilder.parse(xml);
      doc.getDocumentElement();
      final Element root = doc.getDocumentElement();
      final NodeList nodes = doc.getElementsByTagName("rowID");

      selected = selectAllFunction(nodes, root);

      final TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = null;
      transformer = transformerFactory.newTransformer();
      final DOMSource source = new DOMSource(doc);
      final StreamResult result = new StreamResult(xml.getAbsolutePath());
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, result);
    } catch (final Exception e) {
    	 System.out.println("sql command failed");
    }
    return selected;
  }

  public static String[][] select(final File xml, final String[] col,
      final int[] selectedRows) {
    String[][] selected = null;
    try {
      final DocumentBuilderFactory docFactory = DocumentBuilderFactory
          .newInstance();
      final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      final Document doc = docBuilder.parse(xml);
      doc.getDocumentElement();
      final Element root = doc.getDocumentElement();
      final NodeList nodes = doc.getElementsByTagName("rowID");

      selected = selectFunction(col, selectedRows, nodes, root);

      final TransformerFactory transformerFactory = TransformerFactory
          .newInstance();
      Transformer transformer = null;
      transformer = transformerFactory.newTransformer();
      final DOMSource source = new DOMSource(doc);
      final StreamResult result = new StreamResult(xml.getAbsolutePath());
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, result);

    } catch (final Exception e) {
    	 System.out.println("sql command failed");
    }
    return selected;
  }

  private static String[][] selectAllFunction(final NodeList nodes, final Element root) {
    String[][] selected = null;
    int numOfCols = 0;
    int x = 1;
    while (nodes.item(1).getChildNodes().item(x) != null) {//++
      x += 2;
      numOfCols++;
    }
    final int numOfRows = nodes.getLength();
    selected = new String[numOfRows][numOfCols];

    for (int i = 0; i < numOfRows; i++) {//++
      for (int k = 0; k < numOfCols; k++) {
        final NodeList dataTag = root.getChildNodes().item(2 * i + 1)
            .getChildNodes().item(2 * k + 1).getChildNodes();
        String data = dataTag.item(0).toString();
        data = data.substring(8, data.length() - 1);
        selected[i][k] = data;
      }
    }
    String arr[][]= new String [numOfRows-1][numOfCols];
    for(int i=1 ;i<selected.length;i++){
    	for(int j=0;j<selected[j].length;j++){
    		arr[i-1][j]=selected[i][j];
    	}
    }
    return arr;
  }

  private static String[][] selectFunction(final String[] col,
      final int[] selectedRows, final NodeList nodes, final Element root) {
    String[][] selected = null;
    int numOfCols = 0;
    int x = 1;
    while (nodes.item(1).getChildNodes().item(x) != null) {//++
      x += 2;
      numOfCols++;
    }
    final int numOfRows = selectedRows.length;
    selected = new String[numOfRows][col.length];
    for (int i = 0; i < numOfRows; i++) {
      for (int k = 0; k < numOfCols; k++) {
        for (int j = 0; j < col.length; j++) {
          final String colName = "[" + col[j] + ": null]";
          final NodeList dataTag = root.getChildNodes()
              .item(2 * selectedRows[i] + 1).getChildNodes().item(2 * k + 1)
              .getChildNodes();
          final boolean check = dataTag.toString().equals(colName);

          if (check) {
            String data = dataTag.item(0).toString();
            data = data.substring(8, data.length() - 1);
            selected[i][j] = data;
          }
        }
      }
    }
    return selected;
  }
}
