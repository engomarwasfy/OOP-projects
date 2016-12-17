package jdbcTesting;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import JDBC.JResultSetMetaData;

public class JResultSetMetaDataTest {
  @Test
  public void testColumnNames() {
    final String tableName = "travel";
    // array of column names.
    final String colNames[] = { "ID", "Names", "City", "Date of birth" };
    // data array.
    final String arr[][] = { { "0", "Sherif", "Alex", "1996-03-03" },
        { "1", "Shaban", "Cairo", "2008-05-09" } };
    final String colTypes[] = { "integer", "string", "string", "date" };
    try {
      final JResultSetMetaData object = new JResultSetMetaData(colNames, arr,
          tableName, colTypes);
      // test function getColumnName().
      for (int i = 1; i <= colNames.length; i++) {
        assertEquals(colNames[i - 1], object.getColumnName(i));
      }
      // test function getColumnLable().
      for (int i = 1; i <= colNames.length; i++) {
        assertEquals(colNames[i - 1], object.getColumnLabel(i));
      }

    } catch (final SQLException e) {
    }
  }
  @Test
  public void testColumnTypes() {
    final String tableName = "travel";
    // array of column names.
    final String colNames[] = { "ID", "Names", "City", "Date of birth" };
    // data array.
    final String arr[][] = { { "0", "Sherif", "Alex", "1996-03-03" },
        { "1", "Shaban", "Cairo", "2008-05-09" } };
    final String colTypes[] = { "integer", "string", "string", "date" };
    try {
      final JResultSetMetaData object = new JResultSetMetaData(colNames, arr,
          tableName, colTypes);
      // test function getColType().
      final int types[] = { java.sql.Types.INTEGER, java.sql.Types.VARCHAR,
          java.sql.Types.VARCHAR, java.sql.Types.DATE };
      for (int i = 1; i <= colTypes.length; i++) {
        assertEquals(types[i - 1], object.getColumnType(i));
      }
    } catch (final SQLException e) {
    }
  }
  @Test
  public void testColumnCount() {
    final String tableName = "travel";
    // array of column names.
    final String colNames[] = { "ID", "Names", "City", "Date of birth" };
    // data array.
    final String arr[][] = { { "0", "Sherif", "Alex", "1996-03-03" },
        { "1", "Shaban", "Cairo", "2008-05-09" } };
    final String colTypes[] = { "integer", "string", "string", "date" };
    try {
      final JResultSetMetaData object = new JResultSetMetaData(colNames, arr,
          tableName, colTypes);
      // test function getColumnCount().
      final int numOfcol = 4;
      assertEquals(numOfcol, object.getColumnCount());
    } catch (final SQLException e) {
    }
  }
  @Test
  public void testGetTableeName() {
    final String tableName = "travel";
    // array of column names.
    final String colNames[] = { "ID", "Names", "City", "Date of birth" };
    // data array.
    final String arr[][] = { { "0", "Sherif", "Alex", "1996-03-03" },
        { "1", "Shaban", "Cairo", "2008-05-09" } };
    final String colTypes[] = { "integer", "string", "string", "date" };
    try {
      final JResultSetMetaData object = new JResultSetMetaData(colNames, arr,
          tableName, colTypes);
      // test function getTableName()
      for (int i = 1; i <= colNames.length; i++) {
        assertEquals("travel", object.getTableName(i));
      }
    } catch (final SQLException e) {
    }
  }
  @Test
  public void testError() throws SQLException{
    final String tableName = "travel";
    // array of column names.
    final String colNames[] = { "ID", "Names", "City", "Date of birth" };
    // data array.
    String arr[][] = { { "0", "Sherif", "Alex", "1996-03-03" },
        { "1", "Shaban", "Cairo", "2008-05-09" } };
    final String colTypes[] = { "integer", "string", "string", "date" };
    // test no data.
    arr = null;
    final JResultSetMetaData object2 = new JResultSetMetaData(colNames, arr,
        tableName, colTypes);
    try {
      object2.getColumnCount();
      Assert.fail("error");
    } catch (final SQLException e) {

    }
  }
}
