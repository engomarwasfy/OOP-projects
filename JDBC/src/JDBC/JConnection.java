package JDBC;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class JConnection implements Connection {

  private static JConnection instance = new JConnection();
  private static String protocol = "xmldb";

  public JConnection() {

  }

  public static JConnection getinstance() {
    if (instance == null) {
      instance = new JConnection();
    }
    return instance;
  }

  public static void setProtocol(final String newProtocol) {
    protocol = newProtocol;
  }

  public static String getProtocol() {
    return protocol;
  }

  @Override
  public void close() throws SQLException {
    instance = null;
  }

  @Override
  public Statement createStatement() throws SQLException {
    JStatement.getinstance().setProtocol(protocol);
    return JStatement.getinstance();
  }

  /*****************************************************************/

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
  public void abort(final Executor arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void clearWarnings() throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void commit() throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public Array createArrayOf(final String arg0, final Object[] arg1) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Blob createBlob() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Clob createClob() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NClob createNClob() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SQLXML createSQLXML() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Statement createStatement(final int arg0, final int arg1) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Statement createStatement(final int arg0, final int arg1, final int arg2)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Struct createStruct(final String arg0, final Object[] arg1) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getAutoCommit() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getCatalog() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Properties getClientInfo() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getClientInfo(final String arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getHoldability() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public DatabaseMetaData getMetaData() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getNetworkTimeout() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getSchema() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getTransactionIsolation() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Map<String, Class<?>> getTypeMap() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isClosed() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isReadOnly() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isValid(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String nativeSQL(final String arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CallableStatement prepareCall(final String arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CallableStatement prepareCall(final String arg0, final int arg1, final int arg2)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CallableStatement prepareCall(final String arg0, final int arg1, final int arg2,
      final int arg3) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(final String arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(final String arg0, final int arg1)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(final String arg0, final int[] arg1)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(final String arg0, final String[] arg1)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(final String arg0, final int arg1, final int arg2)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(final String arg0, final int arg1, final int arg2,
      final int arg3) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void releaseSavepoint(final Savepoint arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void rollback() throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void rollback(final Savepoint arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setAutoCommit(final boolean arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setCatalog(final String arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setClientInfo(final Properties arg0) throws SQLClientInfoException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setClientInfo(final String arg0, final String arg1)
      throws SQLClientInfoException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setHoldability(final int arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setNetworkTimeout(final Executor arg0, final int arg1) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setReadOnly(final boolean arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public Savepoint setSavepoint() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Savepoint setSavepoint(final String arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSchema(final String arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setTransactionIsolation(final int arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setTypeMap(final Map<String, Class<?>> arg0) throws SQLException {
    // TODO Auto-generated method stub

  }

}
