package XmlMethods;

import java.io.File;
import java.io.IOException;
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

import eval.eval;
import extractInformation.UsedDataBase;

public class XmlParsingMethods {

	/********************************************************************************************************/
	public ArrayList<Integer> parseCondition(final File xml, final String condition) {
		ArrayList<Integer> indexes = new ArrayList<>();
		// there is no condition as select * from table
		if (condition == null) {
		}
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e1) {
			 System.out.println("sql command failed");
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(xml.getPath());
		} catch (SAXException | IOException e) {
			 System.out.println("sql command failed");
		}
		if (condition == null) {
			for (int i = 1; i < doc.getElementsByTagName("rowID").getLength(); i++) {
				indexes.add(i);
			}
			return indexes;
		}
		eval logic = new eval();
		if(condition.contains("AND") || condition.contains("OR") || condition.contains("NOT")) {
			indexes = logic.getindexes(xml, condition);
		}else if (condition.contains("=")) {
			indexes = equal(xml, condition);
		} else if (condition.contains(">")) {
			indexes = greaterThan(xml, condition);
		} else if (condition.contains("<")) {
			indexes = lessThan(xml, condition);
		} else if (condition.equals("'true'")) {
			for (int i = 1; i < doc.getElementsByTagName("rowID").getLength(); i++) {
				indexes.add(i);
			}
		} else {
			throw new RuntimeException();
		}
		return indexes;
	}

	/********************************************************************************************************/
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
	public ArrayList<Integer> greaterThan(final File xml, final String condition) {

		final ArrayList<Integer> indexes = new ArrayList<>();
		int n = 0;
		try {
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			final Document doc = docBuilder.parse(xml.getPath());
			n = doc.getElementsByTagName("rowID").getLength();
			// loop the staff child node
		} catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError e) {
			 System.out.println("sql command failed");
		}
		final String arr[] = condition.split(">");
		if (isNumber(arr[0]) && isNumber(arr[1])) {
			if (toInteger(arr[0]) > toInteger(arr[1])) {
				for (int i = 1; i < n; i++) {
					indexes.add(i);
				}
			}
		}
		final int indexOfCol = checkColFound(xml.getName().substring(0, xml.getName().indexOf('.')), arr[0]);
		if (arr.length == 2 && indexOfCol != -1) {
			try {
				if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
					arr[1] = arr[1].substring(1, arr[1].length() - 1);
				}
				final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				final Document doc = docBuilder.parse(xml.getPath());
				// loop the staff child node
				for (int i = 1; i < doc.getElementsByTagName("rowID").getLength(); i++) {
					final Node first = doc.getElementsByTagName("rowID").item(0);
					String type = first.getChildNodes().item(indexOfCol).getTextContent();
					final Node staff = doc.getElementsByTagName("rowID").item(i);
					final NodeList list = staff.getChildNodes();

					if ((type.equals("int"))
							&& (toInteger(list.item(indexOfCol).getTextContent()) > toInteger(arr[1]))) {

						indexes.add(i);
					} else if (list.item(indexOfCol).getTextContent().compareTo(arr[1]) <= -1) {

						indexes.add(i);
					}
				}
			} catch (ParserConfigurationException | SAXException | IOException
					| TransformerFactoryConfigurationError e) {
				 System.out.println("sql command failed");
			}
		}
		return indexes;
	}

	/********************************************************************************************************/
	public ArrayList<Integer> lessThan(final File xml, final String condition) {
		final ArrayList<Integer> indexes = new ArrayList<>();
		int n = 0;
		try {

			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			final Document doc = docBuilder.parse(xml.getPath());
			n = doc.getElementsByTagName("rowID").getLength();
			// loop the staff child node
		} catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError e) {
			 System.out.println("sql command failed");
		}
		final String arr[] = condition.split("<");
		if (isNumber(arr[0]) && isNumber(arr[1])) {
			if (toInteger(arr[0]) < toInteger(arr[1])) {
				for (int i = 1; i < n; i++) {
					indexes.add(i);
				}
			}
		}
		final int indexOfCol = checkColFound(xml.getName().substring(0, xml.getName().indexOf('.')), arr[0]);
		if (arr.length == 2 && indexOfCol != -1) {
			try {
				if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
					arr[1] = arr[1].substring(1, arr[1].length() - 1);
				}
				final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				final Document doc = docBuilder.parse(xml.getPath());
				// // loop the staff child node
				final Node first = doc.getElementsByTagName("rowID").item(0);
				String type = first.getChildNodes().item(indexOfCol).getTextContent();
				for (int i = 1; i < doc.getElementsByTagName("rowID").getLength(); i++) {
					final Node staff = doc.getElementsByTagName("rowID").item(i);
					final NodeList list = staff.getChildNodes();

					if ((type.equals("int"))
							&& (toInteger(list.item(indexOfCol).getTextContent()) > toInteger(arr[1]))) {
						indexes.add(i);
					} else if (list.item(indexOfCol).getTextContent().compareTo(arr[1]) >= 1) {

						indexes.add(i);
					}
				}

			} catch (ParserConfigurationException | SAXException | IOException
					| TransformerFactoryConfigurationError e) {
				 System.out.println("sql command failed");
			}
		}
		return indexes;
	}

	/********************************************************************************************************/
	public ArrayList<Integer> equal(final File xml, final String condition) {

		final ArrayList<Integer> indexes = new ArrayList<>();
		int n = 0;
		try {
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			final Document doc = docBuilder.parse(xml.getPath());
			n = doc.getElementsByTagName("rowID").getLength();
			// loop the staff child node
		} catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError e) {
			 System.out.println("sql command failed");
		}
		final String arr[] = condition.split("=");
		if (isNumber(arr[0]) && isNumber(arr[1])) {
			if (toInteger(arr[0]) == toInteger(arr[1]) || arr[0].equals(arr[1])) {
				for (int i = 1; i < n; i++) {
					indexes.add(i);
				}
			}
		}
		final int indexOfCol = checkColFound(xml.getName().substring(0, xml.getName().indexOf('.')), arr[0]);
		if (arr.length == 2 && indexOfCol != -1) {
			try {
				if (arr[1].charAt(0) == '\'' && arr[1].charAt(arr[1].length() - 1) == '\'') {
					arr[1] = arr[1].substring(1, arr[1].length() - 1);
				}
				final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				final Document doc = docBuilder.parse(xml.getPath());
				// // loop the staff child node
				for (int i = 1; i < doc.getElementsByTagName("rowID").getLength(); i++) {
					final Node staff = doc.getElementsByTagName("rowID").item(i);
					final NodeList list = staff.getChildNodes();
					if (list.item(indexOfCol).getTextContent().equals(arr[1])) {
						indexes.add(i);
					}
				}
			} catch (ParserConfigurationException | SAXException | IOException
					| TransformerFactoryConfigurationError e) {
				 System.out.println("sql command failed");
			}
		}
		return indexes;
	}

	/******************************************************************************************************/
	public boolean isNumber(final String word) {
		return Pattern.matches("^[0-9]*$", word);
	}

	/*******************************************************************************************************/
	public int toInteger(final String word) {
		return Integer.parseInt(word);
	}
	/********************************************************************************************************/

}
