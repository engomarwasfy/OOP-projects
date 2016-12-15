package JDBC;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class JDriver implements Driver {

  static {
    try {
      java.sql.DriverManager.registerDriver(new JDriver());
    } catch (final SQLException E) {
      throw new RuntimeException("Can't register driver!");
    }
  }

  public JDriver() {

  }

  @Override
  public boolean acceptsURL(final String arg0) throws SQLException {
    if (arg0 == null) {
      throw new RuntimeException();
    }
    final String fixedxml = "jdbc:xmldb://localhost";
    final String fixedjson = "jdbc:altdb://localhost";
    if (arg0.equalsIgnoreCase(fixedxml)
        || arg0.equalsIgnoreCase(fixedjson)) {
      return true;
    }
    return false;
  }

  @Override
  public Connection connect(final String arg0, final Properties arg1) throws SQLException {
    final String tmp = System.getProperty("java.io.tmpdir");
    final File DBMSdir = new File(tmp + "DBMS");
    DBMSdir.listFiles();
    if (acceptsURL(arg0)) {
      JConnection.getinstance().setProtocol(arg0.split(":")[1]);
    }
    return JConnection.getinstance();
  }

  @Override
  public int getMajorVersion() {
    return 0;
  }

  @Override
  public int getMinorVersion() {
    return 0;
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return null;
  }

  @Override
  public DriverPropertyInfo[] getPropertyInfo(final String arg0, final Properties arg1)
      throws SQLException {
    return null;
  }

  @Override
  public boolean jdbcCompliant() {
    return false;
  }

}
