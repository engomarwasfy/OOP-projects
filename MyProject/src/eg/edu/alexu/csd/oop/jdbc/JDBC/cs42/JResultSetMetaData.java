package eg.edu.alexu.csd.oop.jdbc.JDBC.cs42;

import java.sql.Date;
import java.sql.SQLException;

public class JResultSetMetaData implements java.sql.ResultSetMetaData{
  private final String colNames[];
  private final Object arr[][];
  private final String tableName;
  public JResultSetMetaData(final String colNames[],final Object arr[][], final String tableName){
    this.arr = arr;
    this.colNames = colNames;
    this.tableName = tableName;
  }

  @Override
  public int getColumnCount() throws SQLException {
    if (arr == null) {
      final SQLException ex = new SQLException("data access error , error at getColumnCount");
      throw ex;
    }
    return arr[0].length;
  }

  @Override
  public String getTableName(final int column) throws SQLException {
    if (arr == null) {
      final SQLException ex = new SQLException("data access error , error at getTableName");
      throw ex;
    }
    if(column < 1 || column > colNames.length) {
      return "";
    }
    return tableName;
  }

  @Override
  public String getColumnLabel(final int column) throws SQLException {
    //we did not use SQL Aliases "as", so this function will return getColumnName(column)
    return getColumnName(column);
  }
  @Override
  public String getColumnName(final int column) throws SQLException {
    if (arr == null || column < 1 || column > colNames.length) {
      final SQLException ex = new SQLException("data access error , error at getColumnName");
      throw ex;
    }
    return colNames[column - 1];
  }
  @Override
  public int getColumnType(final int column) throws SQLException {
    if (arr == null || column < 1 || column > colNames.length) {
      final SQLException ex = new SQLException("data access error , error at getColumnType");
      throw ex;
    }
    if (arr[0][column - 1] instanceof String) {
      return java.sql.Types.VARCHAR;
    }
    if (arr[0][column - 1] instanceof Integer) {
      return java.sql.Types.INTEGER;
    }
    if (arr[0][column - 1] instanceof Double) {
      return java.sql.Types.FLOAT;
    }
    if (arr[0][column - 1] instanceof Date) {
      return java.sql.Types.DATE;
    }
    final SQLException ex = new SQLException("data access error , error at getColumnType, no type matches");
    throw ex;
  }


  /****************************************************/

  @Override
  public boolean isWrapperFor(final Class<?> iface) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public <T> T unwrap(final Class<T> iface) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getCatalogName(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getColumnClassName(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getColumnDisplaySize(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getColumnTypeName(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getPrecision(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getScale(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getSchemaName(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAutoIncrement(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCaseSensitive(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCurrency(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isDefinitelyWritable(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int isNullable(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isReadOnly(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSearchable(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSigned(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isWritable(final int arg0) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

}
