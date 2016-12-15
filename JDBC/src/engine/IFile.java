package engine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public interface IFile {

  public ArrayList<ArrayList<String>> read(String databaseName,
      String tableName)
      throws ParserConfigurationException, SAXException, IOException;

  public void write(ArrayList<ArrayList<String>> data, String dataBaseName,
      String tableName, ArrayList<String> cols, ArrayList<String> types)
      throws ParserConfigurationException, TransformerConfigurationException,
      TransformerFactoryConfigurationError, SQLException;

  public ArrayList<String> getcols(String dataBaseName, String tableName)
      throws ParserConfigurationException, SQLException;

  public ArrayList<String> getcolsTypes(String dataBaseName, String tableName);
}
