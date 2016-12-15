package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;


public class DataBase implements ImethodOfDataBase {
  private String databaseName;
  private ArrayList<String> tablesNames = new ArrayList<String>();
  private final ArrayList<String> batch = new ArrayList<String>();
  private static DataBase instance = new DataBase();
  private IFile fileWriter = new XmlFile();

  private DataBase() {
  }

  public static DataBase getinstance() {
    return instance;
  }

  public void setFileWriter(final String protocol) {
    if (protocol.equalsIgnoreCase("xmldb")) {
      fileWriter = new XmlFile();
    } else {
      fileWriter = new JsonFile();
    }
  }

  public void setdatabaseName(final String dbName) {
    this.databaseName = dbName;
    tablesNames = getTablesName();
  }

  public String getdatabaseName() {
    return databaseName;
  }

  public ArrayList<String> getTables() {
    return tablesNames;
  }

  public void addTable(final String tableName) {
    tablesNames.add(tableName);
  }

  public void removeTable(final String tableName) {
    tablesNames.remove(tableName);
  }

  private ArrayList<String> getTablesName() {
    final ArrayList<String> tables = new ArrayList<String>();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File databaseDIR = new File(tmp + "DBMS" + File.separator + databaseName);
    final File[] listOfFiles = databaseDIR.listFiles();
    if (listOfFiles != null) {
      for (int i = 0; i < listOfFiles.length; i++) {
        final String filename = listOfFiles[i].getName();
        if (filename.endsWith(".xml")) {
          tables.add(filename.replaceAll(".xml", ""));
        } else if (filename.endsWith(".json")) {
          tables.add(filename.replaceAll(".json", ""));
        }
      }
    }
    return tables;
  }

  @Override
  public void create(final String databaseName) throws IOException {
    final String tmp = System.getProperty("java.io.tmpdir");
    final File DBMSdir = new File(tmp + "DBMS");
    DBMSdir.mkdir();
    final File dir = new File(DBMSdir + File.separator + databaseName);
    if (dir.exists()) {
      DropDateBase.drop(databaseName);
    }
    dir.mkdir();
    final File batchdir = new File(tmp + "batches");
    batchdir.mkdir();
    final File batch = new File(batchdir + File.separator + databaseName + ".txt");
    PrintWriter out = null;
    try {
      out = new PrintWriter(batch);
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    }
    out.close();
    System.out.println("Data Base Created");
  }

  @Override
  public void dropTable(final String tableName) {
    try {
      final String tmp = System.getProperty("java.io.tmpdir");
      final File xml = new File(tmp + "DBMS" + File.separator + databaseName
          + File.separator + tableName + ".xml");
      tablesNames.remove(tableName);
      final Path path = xml.toPath();
      Files.delete(path);
      final File xmldtd = new File(tmp + "DBMS" + File.separator + databaseName
          + File.separator + tableName + "Schemma.dtd");
      final Path dtdpath = xmldtd.toPath();
      Files.delete(dtdpath);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void createTable(final String tableName, final String[] cols) throws SQLException {
    for (int i = 0; i < tablesNames.size(); i++) {
      if (tableName.equals(tablesNames.get(i))) {
        throw new RuntimeException();
      }
    }
    final ArrayList<String> colsName = new ArrayList<String>();
    final ArrayList<String> colstype = new ArrayList<String>();
    for (int j = 0; j < cols.length; j++) {
      colsName.add(cols[j].split(" ")[0]);
      colstype.add(cols[j].split(" ")[1]);
    }

    try {
      fileWriter.write(new ArrayList<ArrayList<String>>(), databaseName,
          tableName, colsName, colstype);
    } catch (TransformerConfigurationException | ParserConfigurationException
        | TransformerFactoryConfigurationError e) {
      throw new RuntimeException();
    }

  }

}