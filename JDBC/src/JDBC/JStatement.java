package JDBC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import bridge.Bridge;
import bridge.Director;
import bridge.ExtractorFactory;
import engine.IFile;
import engine.JsonFile;
import engine.XmlFile;
import extractInformation.IExtractor;
import extractInformation.UsedDataBase;
import validateSyntax.OrganizeInput;
import validateSyntax.Parser;

public class JStatement implements Statement {

  private String lastSelect = "";
  private final Parser s = new Parser();
  private final Director director = new Director();
  private final Bridge bridge = new Bridge();
  private static JStatement instance = new JStatement();
  private static String protocol = "xmldb";
  private static IFile fileWriter = new XmlFile();
  private static int updateCount = 0;
  private static int QueryTimeout = 0;

  public JStatement() {

  }

  public static JStatement getinstance() {
    updateCount = 0;
    if (instance == null) {
      instance = new JStatement();
    }
    return instance;
  }

  public static void setProtocol(final String newProtocol) {
    protocol = newProtocol;
    if (protocol.equalsIgnoreCase("xmldb")) {
      fileWriter = new XmlFile();
    } else {
      fileWriter = new JsonFile();
    }
  }

  public static String getProtocol() {
    return protocol;
  }

  @Override
  public void addBatch(final String arg0) throws SQLException {
    final String databaseName = UsedDataBase.getUsedDataBase();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File batch = new File(
        tmp + "batches" + File.separator + databaseName + ".txt");
    // load
    final ArrayList<String> load = new ArrayList<String>();
    FileReader fr;
    BufferedReader br = null;
    try {
      fr = new FileReader(batch);
      br = new BufferedReader(fr);
      String scan;
      while ((scan = br.readLine()) != null) {
        load.add(scan);
      }
    } catch (final IOException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    // add
    PrintWriter out = null;
    try {
      out = new PrintWriter(batch);
    } catch (final FileNotFoundException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    for (int i = 0; i < load.size(); i++) {
      out.println(load.get(i));
    }
    out.println(arg0);
    out.close();
  }

  @Override
  public void clearBatch() throws SQLException {
    final String databaseName = UsedDataBase.getUsedDataBase();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File batch = new File(
        tmp + "batches" + File.separator + databaseName + ".txt");
    PrintWriter out = null;
    try {
      out = new PrintWriter(batch);
    } catch (final FileNotFoundException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    out.close();
  }

  @Override
  public void close() throws SQLException {
    instance = null;
  }

  @Override
  public boolean execute(final String arg0) throws SQLException {
    String[] arr = null;
    if (arg0.trim().length() != 0) {
      final String after = OrganizeInput.organize(arg0);
      arr = after.split(" ");
      if (s.validate(arr)) {
        director.direct(arr[0].toLowerCase());
        try {
          bridge.dirct(director, arr, protocol);
        } catch (final Exception e) {
          final SQLException e1 = new SQLException("not valid statment");
          throw e1;
        }
      } else {
        final SQLException e = new SQLException("not valid statment");
        throw e;
      }
    }
    try {
      if (arr[0].equalsIgnoreCase("select")) {
        lastSelect = arg0;
        final ResultSet result = executeQuery(arg0);
        int cunter = 0;
        while (result.next()) {
          cunter++;
        }
        if (cunter > 0) {
          return true;
        } else {
          return false;
        }
      }
    } catch (final Exception e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    return false;
  }

  @Override
  public int[] executeBatch() throws SQLException {
    final ArrayList<Integer> mostafa = new ArrayList<Integer>();
    final String databaseName = UsedDataBase.getUsedDataBase();
    final String tmp = System.getProperty("java.io.tmpdir");
    final File batch = new File(
        tmp + "batches" + File.separator + databaseName + ".txt");
    // load
    FileReader fr;
    BufferedReader br = null;
    try {
      fr = new FileReader(batch);
      br = new BufferedReader(fr);
      String scan;
      while ((scan = br.readLine()) != null) {
        if (execute(scan)) {
          if (scan.trim().length() != 0) {
            final String after = OrganizeInput.organize(scan);
            final String[] arr = after.split(" ");
            if (s.validate(arr)) {
              director.direct(arr[0].toLowerCase());
              try {
                final int[] update = bridge.dirct(director, arr, protocol);
                for (int i = 0; i < update.length; i++) {
                  mostafa.add(update[i]);
                }
              } catch (TransformerException | SAXException
                  | ParserConfigurationException e) {
                final SQLException e1 = new SQLException("not valid statment");
                throw e1;
              }
            }
          }
        }
      }
    } catch (final IOException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    final LinkedHashSet<Integer> omar = new LinkedHashSet<Integer>();
    omar.addAll(mostafa);
    mostafa.clear();
    mostafa.addAll(omar);
    final int[] shaban = new int[mostafa.size()];
    for (int i = 0; i < shaban.length; i++) {
      shaban[i] = mostafa.get(i);
    }
    return shaban;
  }

  @Override
  public ResultSet executeQuery(final String arg0) throws SQLException {
    IExtractor formation = null;
    String[] arr = null;
    if (arg0.trim().length() != 0) {
      final String after = OrganizeInput.organize(arg0);
      arr = after.split(" ");
      if (s.validate(arr)) {
        formation = ExtractorFactory.getExtractor(arr[0].toLowerCase());
      }
    }
    final String tablename = formation.getTableName(arr);
    director.direct(arr[0].toLowerCase());
    String[][] result = new String[0][0];
    try {
      result = bridge.dirct2(director, arr, protocol);
    } catch (TransformerException | SAXException | IOException
        | ParserConfigurationException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    ArrayList<String> colNames = null;
    ArrayList<String> colTypes = null;
    try {
      colNames = fileWriter.getcols(UsedDataBase.getUsedDataBase(), tablename);
      colTypes = fileWriter.getcolsTypes(UsedDataBase.getUsedDataBase(),
          tablename);
    } catch (final ParserConfigurationException e) {
      final SQLException e1 = new SQLException("not valid statment");
      throw e1;
    }
    if (arr[0].equalsIgnoreCase("select")) {
      final String[] cols = formation.getCol(arr);
      if (!cols[0].equals("*")) {
        final ArrayList<String> colsNames = new ArrayList<String>();
        final ArrayList<String> colsTypes = new ArrayList<String>();
        for (int i = 0; i < cols.length; i++) {
          final int index = colNames.indexOf(cols[i]);
          colsNames.add(colNames.get(index));
          colsTypes.add(colTypes.get(index));
        }
        final JResultset resultset = new JResultset(result, colsNames, tablename,
            colsTypes);
        return resultset;
      }
    }
    final JResultset resultset = new JResultset(result, colNames, tablename,
        colTypes);
    return resultset;
  }

  @Override
  public int executeUpdate(final String sql) throws SQLException {
    if (isClosed()) {
      throw new SQLException();
    }
    int[] result = new int[0];
    if (sql.trim().length() != 0) {
      final String after = OrganizeInput.organize(sql);
      final String[] arr = after.split(" ");
      try {
        if (s.validate(arr)) {
          director.direct(arr[0].toLowerCase());
        } else {
          throw new SQLException();
        }
        result = bridge.dirct(director, arr, protocol);
      } catch (final Exception e) {
        final SQLException ex = new SQLException("not valid statment");
        throw ex;
      }
      if (arr[0].equalsIgnoreCase("update")) {
        return result.length;
      }
      if (arr[0].equalsIgnoreCase("insert")) {
        return 1;
      }
    }

    updateCount += result.length;
    return updateCount;
  }

  @Override
  public Connection getConnection() throws SQLException {
    return JConnection.getinstance();
  }

  @Override
  public int getQueryTimeout() throws SQLException {
    return QueryTimeout;
  }

  @Override
  public void setQueryTimeout(final int arg0) throws SQLException {
    QueryTimeout = arg0;
  }

  @Override
  public ResultSet getResultSet() throws SQLException {
    if (lastSelect.length() > 0) {
      return executeQuery(lastSelect);
    }
    return null;
  }

  // new2
  @Override
  public int getUpdateCount() throws SQLException {
    return updateCount;
  }

  /*******************************************************************/
  @Override
  public boolean isWrapperFor(final Class<?> arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public <T> T unwrap(final Class<T> arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void cancel() throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void clearWarnings() throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void closeOnCompletion() throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean execute(final String arg0, final int arg1) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean execute(final String arg0, final int[] arg1) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean execute(final String arg0, final String[] arg1) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int executeUpdate(final String arg0, final int arg1) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int executeUpdate(final String arg0, final int[] arg1) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int executeUpdate(final String arg0, final String[] arg1) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getFetchDirection() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getFetchSize() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ResultSet getGeneratedKeys() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getMaxFieldSize() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMaxRows() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean getMoreResults() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getMoreResults(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int getResultSetConcurrency() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getResultSetHoldability() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getResultSetType() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isCloseOnCompletion() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isClosed() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isPoolable() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setCursorName(final String arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setEscapeProcessing(final boolean arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setFetchDirection(final int arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setFetchSize(final int arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMaxFieldSize(final int arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMaxRows(final int arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setPoolable(final boolean arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

}
