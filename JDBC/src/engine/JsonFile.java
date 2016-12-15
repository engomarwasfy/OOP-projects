package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class JsonFile implements IFile {

  @Override
  public ArrayList<ArrayList<String>> read(final String databaseName,
      final String tableName)
      throws ParserConfigurationException, SAXException, IOException {
    String arr[][];
    final JSONParser parser = new JSONParser();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File f = new File(tmp + "DBMS" + File.separator + databaseName
        + File.separator + tableName + ".json");
    Object obj1 = null;
    try {
      obj1 = parser.parse(new FileReader(f));
    } catch (final ParseException e) {
      e.printStackTrace();
    }
    final JSONObject jsonObject1 = (JSONObject) obj1;
    final long sizeOfLinkedlist = (long) jsonObject1.get("num");
    final JSONArray d = (JSONArray) jsonObject1.get("0");
    arr = new String[(int) sizeOfLinkedlist][d.size()];
    ArrayList<String> row = new ArrayList<>();
    final ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    for (Integer i = 2; i < sizeOfLinkedlist; i++) {
      final JSONArray Data = (JSONArray) jsonObject1.get(i.toString());

      final Iterator<String> iterator = Data.iterator();

      for (int j = 0; j < Data.size(); j++) {
        arr[i][j] = iterator.next();
      }
    }
    for (int j = 2; j < arr.length; j++) {
      row = new ArrayList<>();
      for (int j2 = 0; j2 < arr[0].length; j2++) {
        row.add(arr[j][j2]);
      }
      table.add(row);
    }
    return table;
  }

  @Override
  public void write(final ArrayList<ArrayList<String>> data,
      final String dataBaseName, final String xmlName,
      final ArrayList<String> cols, final ArrayList<String> types)
      throws ParserConfigurationException, TransformerConfigurationException,
      TransformerFactoryConfigurationError, SQLException {
    final JSONObject obj = new JSONObject();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File f = new File(tmp + "DBMS" + File.separator + dataBaseName
        + File.separator + xmlName + ".json");
    FileWriter file = null;
    try {
      file = new FileWriter(f);
    } catch (final IOException e1) {
      e1.printStackTrace();
    }
    Integer counter = 0;
    JSONArray temp = new JSONArray();
    for (int i = 0; i < cols.size(); i++) {
      temp.add(cols.get(i));
    }
    obj.put(convertIntegerToString(counter), temp);
    temp = new JSONArray();
    for (int i = 0; i < types.size(); i++) {
      temp.add(types.get(i));
      System.out.println(types.get(i));
    }
    counter++;
    obj.put(convertIntegerToString(counter), temp);
    obj.put("num", new Integer(2));
    if (data.size() > 0) {
      obj.put("num", new Integer(data.size() + 2));
      ArrayList<String> row = new ArrayList();
      for (int i = 0; i < data.size(); i++) {
        temp = new JSONArray();
        row = data.get(i);
        for (int j = 0; j < row.size(); j++) {
          temp.add(row.get(j));
        }
        counter++;
        obj.put(convertIntegerToString(counter).toString(), temp);
      }

    }
    try {
      file.write(obj.toString());
    } catch (final IOException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    try {
      file.flush();
    } catch (final IOException e) {
      e.printStackTrace();
    }
    try {
      file.close();
    } catch (final IOException e) {
      e.printStackTrace();
    }

  }

  public String convertIntegerToString(final Integer x) {
    return x.toString();
  }

  @Override
  public ArrayList<String> getcols(final String dataBaseName,
      final String tableName) {

    final JSONParser parser = new JSONParser();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File f = new File(tmp + "DBMS" + File.separator + dataBaseName
        + File.separator + tableName + ".json");
    Object obj1 = null;
    try {
      obj1 = parser.parse(new FileReader(f));
    } catch (final ParseException e) {
      e.printStackTrace();
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    final ArrayList<String> cols = new ArrayList<String>();
    final JSONObject jsonObject1 = (JSONObject) obj1;
    final JSONArray d = (JSONArray) jsonObject1.get("0");
    final Iterator<String> iterator = d.iterator();
    while (iterator.hasNext()) {
      cols.add(iterator.next());
    }
    return cols;
  }

  @Override
  public ArrayList<String> getcolsTypes(final String dataBaseName,
      final String tableName) {
    final JSONParser parser = new JSONParser();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File f = new File(tmp + "DBMS" + File.separator + dataBaseName
        + File.separator + tableName + ".json");
    Object obj1 = null;
    try {
      obj1 = parser.parse(new FileReader(f));
    } catch (final ParseException e) {
      e.printStackTrace();
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    final ArrayList<String> type = new ArrayList<String>();
    final JSONObject jsonObject1 = (JSONObject) obj1;
    final JSONArray d = (JSONArray) jsonObject1.get("1");
    final Iterator<String> iterator = d.iterator();
    while (iterator.hasNext()) {
      type.add(iterator.next());
    }
    return type;
  }

}
