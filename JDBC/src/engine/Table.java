package engine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public class Table implements ImethodOfTable {
  private String databaseName;
  private String tableName;
  private ArrayList<String> coulmsNames;
  private ArrayList<String> coulmsTypes;
  private ArrayList<ArrayList<String>> data;
  private IFile fileWriter = new XmlFile();
  private static Table instance = new Table();

  private Table() {

  }

  public static Table getinstance() {
    return instance;
  }

  public void setDataBaseName(final String DataBaseName) {
    this.databaseName = DataBaseName;
  }

  public void setFileWriter(final String protocol) {
    if (protocol.equalsIgnoreCase("xmldb")) {
      fileWriter = new XmlFile();
    } else {
      fileWriter = new JsonFile();
    }
  }

  public void setTableName(final String tableName)
      throws ParserConfigurationException, SQLException {
    this.tableName = tableName;
    if (tableName != null) {
      coulmsNames = fileWriter.getcols(databaseName, tableName);
      coulmsTypes = fileWriter.getcolsTypes(databaseName, tableName);
    }
  }

  public String getDataBaseName() {
    return databaseName;
  }

  public String getTableName() {
    return tableName;
  }

  @Override
  public int[] deleteRows(final int[] deleteThisRow) throws SQLException {
    try {
      data = fileWriter.read(databaseName, tableName);
    } catch (ParserConfigurationException | SAXException | IOException e1) {
      throw new RuntimeException();
    }
    for (int i = 0; i < deleteThisRow.length; i++) {
      data.remove(deleteThisRow[i] - i);
    }
    try {
      fileWriter.write(data, databaseName, tableName, coulmsNames, coulmsTypes);
    } catch (TransformerConfigurationException | ParserConfigurationException
        | TransformerFactoryConfigurationError e) {
      throw new RuntimeException();
    }
    return deleteThisRow;
  }

  @Override
  public ArrayList<ArrayList<String>> select(final int[] selectThisRow,
      String[] cols) {
    try {
      data = fileWriter.read(databaseName, tableName);
    } catch (ParserConfigurationException | SAXException | IOException e1) {
      throw new RuntimeException();
    }
    if (cols[0].equals("*")) {
      cols = new String[coulmsNames.size()];
      for (int i = 0; i < cols.length; i++) {
        cols[i] = coulmsNames.get(i);
      }
    }
    final int[] colsIndex = new int[cols.length];
    for (int i = 0; i < colsIndex.length; i++) {
      colsIndex[i] = coulmsNames.indexOf(cols[i]);
    }
    final ArrayList<ArrayList<String>> selected = new ArrayList<ArrayList<String>>();
    for (int i = 0; i < selectThisRow.length; i++) {
      final ArrayList<String> row = new ArrayList<String>();
      for (int j = 0; j < colsIndex.length; j++) {
        row.add(data.get(selectThisRow[i]).get(colsIndex[j]));
      }
      selected.add(row);
    }
    return selected;
  }

  @Override
  public int[] update(final int[] updateThisRow, final String[] colsData)
      throws SQLException {
    try {
      data = fileWriter.read(databaseName, tableName);
    } catch (ParserConfigurationException | SAXException | IOException e1) {
      e1.printStackTrace();
    }
    final String[] cols = new String[colsData.length];
    final String[] coulmsdata = new String[colsData.length];
    for (int i = 0; i < colsData.length; i++) {
      cols[i] = colsData[i].split("=")[0];
      coulmsdata[i] = colsData[i].split("=")[1];
    }
    final int[] colsIndex = new int[coulmsdata.length];
    for (int i = 0; i < colsIndex.length; i++) {
      colsIndex[i] = coulmsNames.indexOf(cols[i].toLowerCase());
    }
    for (int i = 0; i < updateThisRow.length; i++) {
      for (int j = 0; j < colsIndex.length; j++) {
        if (checkColType(coulmsNames, coulmsTypes,
            coulmsNames.get(colsIndex[j]), coulmsdata[j])) {
          if (colsData[j].charAt(0) == '\''
              && colsData[j].charAt(colsData[j].length() - 1) == '\'') {
            colsData[j] = colsData[j].substring(1, colsData[j].length() - 1);
          }
          data.get(updateThisRow[i]).set(colsIndex[j], coulmsdata[j]);
        } else {
          throw new RuntimeException();
        }
      }
    }
    try {
      fileWriter.write(data, databaseName, tableName, coulmsNames, coulmsTypes);
    } catch (TransformerConfigurationException | ParserConfigurationException
        | TransformerFactoryConfigurationError e) {
      throw new RuntimeException();
    }
    return updateThisRow;
  }

  @Override
  public void insertRow(String[] cols, final String[] colsData) throws SQLException {
    try {
      data = fileWriter.read(databaseName, tableName);
    } catch (ParserConfigurationException | SAXException | IOException e1) {
      throw new RuntimeException();
    }
    int[] colsIndex = null;
    if (cols != null) {
      colsIndex = new int[cols.length];
    } else {
      cols = new String[coulmsNames.size()];
      for (int i = 0; i < coulmsNames.size(); i++) {
        cols[i] = coulmsNames.get(i);
      }
      colsIndex = new int[coulmsNames.size()];
    }

    for (int i = 0; i < colsIndex.length; i++) {
      if (!coulmsNames.contains(cols[i].toLowerCase())) {
        throw new RuntimeException();
      }
      colsIndex[i] = coulmsNames.indexOf(cols[i].toLowerCase());
    }
    final ArrayList<String> row = new ArrayList<>();
    for (int i = 0; i < coulmsNames.size(); i++) {
      boolean found = false;
      for (int j = 0; j < colsIndex.length; j++) {
        if (i == colsIndex[j]) {
          if (checkColType(coulmsNames, coulmsTypes, coulmsNames.get(i),
              colsData[j])) {
            if (colsData[j].charAt(0) == '\''
                && colsData[j].charAt(colsData[j].length() - 1) == '\'') {
              colsData[j] = colsData[j].substring(1, colsData[j].length() - 1);
            }
            row.add(colsData[j]);
            found = true;
          } else {
            throw new RuntimeException();
          }
          break;
        }
      }
      if (!found) {
        row.add("null");
      }
    }
    data.add(row);
    try {
      fileWriter.write(data, databaseName, tableName, coulmsNames, coulmsTypes);
    } catch (TransformerConfigurationException | ParserConfigurationException
        | TransformerFactoryConfigurationError e) {
      throw new RuntimeException();
    }
  }

  @Override
  public void addCoulm(final String colName, final String colType) throws SQLException {
    try {
      data = fileWriter.read(databaseName, tableName);
    } catch (ParserConfigurationException | SAXException | IOException e1) {
      throw new RuntimeException();
    }
    coulmsNames.add(colName);
    coulmsTypes.add(colType);
    for (int i = 0; i < data.size(); i++) {
      data.get(i).add("null");
    }
    try {
      fileWriter.write(data, databaseName, tableName, coulmsNames, coulmsTypes);
    } catch (TransformerConfigurationException | ParserConfigurationException
        | TransformerFactoryConfigurationError e) {
      throw new RuntimeException();
    }

  }

  @Override
  public void removeCoulm(final String colName) throws SQLException {
    try {
      data = fileWriter.read(databaseName, tableName);
    } catch (ParserConfigurationException | SAXException | IOException e1) {
      throw new RuntimeException();
    }
    final int indexOfCol = coulmsNames.indexOf(colName);
    coulmsNames.remove(colName);
    coulmsTypes.remove(indexOfCol);
    for (int i = 0; i < data.size(); i++) {
      data.get(i).remove(indexOfCol);
    }
    try {
      fileWriter.write(data, databaseName, tableName, coulmsNames, coulmsTypes);
    } catch (TransformerConfigurationException | ParserConfigurationException
        | TransformerFactoryConfigurationError e) {
      throw new RuntimeException();
    }
  }

  public boolean checkColFound(final ArrayList<String> cols, final String colName) {
    for (int i = 0; i < cols.size(); i++) {
      if (colName.equals(cols.get(i))) {
        return true;
      }
    }
    return false;
  }

  public boolean checkColType(final ArrayList<String> cols,
      final ArrayList<String> colsTypes, final String colName, final String data) {
    final int indexOfCol = cols.indexOf(colName);
    if (colsTypes.get(indexOfCol).equals("varchar") && isValidName(data)) {
      return true;
    } else if (colsTypes.get(indexOfCol).equalsIgnoreCase("int")
        && isIntNumber(data)) {
      return true;
    } else if (colsTypes.get(indexOfCol).equalsIgnoreCase("date")
        && isDate(data)) {
      return true;
    } else if (colsTypes.get(indexOfCol).equalsIgnoreCase("float")
        && isFloatNumber(data)) {
      return true;
    }
    return false;
  }

  public boolean isValidName(final String name) {
    if (name.charAt(0) == '\'' && name.charAt(name.length() - 1) == '\'') {
      return true;
    }
    final String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
    return Pattern.matches(regex, name);
  }

  public boolean isIntNumber(String word) {
    if (word.charAt(0) == '-') {
      word = word.replaceAll("-", "");
    }
    return Pattern.matches("^[0-9]*$", word);

  }

  public boolean isFloatNumber(final String word) {
    return Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", word);

  }

  public boolean isDate(String word) {
    if (word.charAt(0) == '\'' && word.charAt(word.length() - 1) == '\'') {
      word = word.substring(1, word.length() - 1);
    }
    return Pattern.matches(
        "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", word);

  }

  public String[][] selectDistenct(final ArrayList<ArrayList<String>> selected) {
    final String arr[][] = new String[selected.size()][selected.get(0).size()];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        arr[i][j] = selected.get(i).get(j);
      }
    }
    final boolean[] dublicate = new boolean[arr.length];
    int notDublicate = 0;

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (Arrays.equals(arr[i], arr[j])) {
          dublicate[j] = true;
        }
      }
    }

    for (int i = 0; i < dublicate.length; i++) {
      if (!dublicate[i]) {
        notDublicate++;
      }
    }
    final String[][] distinct = new String[notDublicate][];
    int counter = 0;
    for (int i = 0; i < dublicate.length; i++) {
      if (!dublicate[i]) {
        distinct[counter] = arr[i];
        counter++;
      }
    }

    return distinct;

  }

}
