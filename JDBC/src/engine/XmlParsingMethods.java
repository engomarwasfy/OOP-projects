package engine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import extractInformation.UsedDataBase;

public class XmlParsingMethods {

  private IFile filewriter = new XmlFile();

  public void setFileWriter(final String protocol) {
    if (protocol.equalsIgnoreCase("xmldb")) {
      filewriter = new XmlFile();
    } else {
      filewriter = new JsonFile();
    }
  }

  /**
   * @throws SQLException
   ******************************************************************************************************/
  public ArrayList<Integer> parseCondition(final String databaseName,
      final String tableName, final String condition) throws SQLException {
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
      indexes = equal(data, condition, tableName);
    } else if (condition.contains(">")) {
      indexes = greaterThan(data, condition, tableName);
    } else if (condition.contains("<")) {
      indexes = lessThan(data, condition, tableName);
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
  public int checkColFound(final String tableName, final String colName)
      throws SQLException {
    final String database = UsedDataBase.getUsedDataBase();
    ArrayList<String> coulmsOfTable = null;
    try {
      coulmsOfTable = filewriter.getcols(database, tableName);
    } catch (final ParserConfigurationException e) {

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
  public ArrayList<Integer> greaterThan(final ArrayList<ArrayList<String>> data,
      final String condition, final String tableName) throws SQLException {
    final ArrayList<Integer> indexes = new ArrayList<Integer>();
    final String[] arr = condition.split(">");
    final int indexOfCol = checkColFound(tableName, arr[0]);
    if (indexOfCol != -1) {
      if (arr[1].charAt(0) == '\''
          && arr[1].charAt(arr[1].length() - 1) == '\'') {
        arr[1] = arr[1].substring(1, arr[1].length() - 1);
      }
      for (int i = 0; i < data.size(); i++) {
        if (isIntNumber(arr[1])) {
          if (toInt(data.get(i).get(indexOfCol)) > toInt(arr[1])) {
            indexes.add(i);
          }
        } else if (isFloatNumber(arr[1])) {
          if (toFloat(data.get(i).get(indexOfCol)) > toFloat(arr[1])) {
            indexes.add(i);
          }
        } else if (data.get(i).get(indexOfCol).compareTo(arr[1]) >= 1) {
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
  public ArrayList<Integer> lessThan(final ArrayList<ArrayList<String>> data,
      final String condition, final String tableName) throws SQLException {
    final ArrayList<Integer> indexes = new ArrayList<Integer>();
    final String[] arr = condition.split("<");
    final int indexOfCol = checkColFound(tableName, arr[0]);
    if (indexOfCol != -1) {
      if (arr[1].charAt(0) == '\''
          && arr[1].charAt(arr[1].length() - 1) == '\'') {
        arr[1] = arr[1].substring(1, arr[1].length() - 1);
      }
      for (int i = 0; i < data.size(); i++) {
        if (isIntNumber(arr[1])) {
          if (toInt(data.get(i).get(indexOfCol)) < toInt(arr[1])) {
            indexes.add(i);
          }
        } else if (isFloatNumber(arr[1])) {
          if (toFloat(data.get(i).get(indexOfCol)) < toFloat(arr[1])) {
            indexes.add(i);
          }
        } else if (data.get(i).get(indexOfCol).compareTo(arr[1]) <= -1) {
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
  public ArrayList<Integer> equal(final ArrayList<ArrayList<String>> data,
      final String condition, final String tableName) throws SQLException {
    final ArrayList<Integer> indexes = new ArrayList<Integer>();
    final String[] arr = condition.split("=");
    final int indexOfCol = checkColFound(tableName, arr[0]);
    if (indexOfCol != -1) {
      if (arr[1].charAt(0) == '\''
          && arr[1].charAt(arr[1].length() - 1) == '\'') {
        arr[1] = arr[1].substring(1, arr[1].length() - 1);
      }
      for (int i = 0; i < data.size(); i++) {
        if (isIntNumber(arr[1])) {
          if (toInt(data.get(i).get(indexOfCol)) == toInt(arr[1])) {
            indexes.add(i);
          }
        } else if (isFloatNumber(arr[1])) {
          if (toFloat(data.get(i).get(indexOfCol)) == toFloat(arr[1])) {
            indexes.add(i);
          }
        } else if (data.get(i).get(indexOfCol).compareTo(arr[1]) == 0) {
          indexes.add(i);
        }
      }
    } else {
      throw new RuntimeException();
    }
    return indexes;

  }

  /******************************************************************************************************/
  public boolean isIntNumber(String word) {
    if (word.charAt(0) == '-') {
      word = word.replaceAll("-", "");
    }
    return Pattern.matches("^[0-9]*$", word);

  }

  public boolean isFloatNumber(final String word) {
    return Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", word);

  }

  public int toInt(final String word) {
    return Integer.parseInt(word);
  }

  public float toFloat(final String word) {
    return (float) Double.parseDouble(word);
  }
}
