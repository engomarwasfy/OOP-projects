package JDBC;

import java.sql.SQLException;

public class JResultSetMetaData implements java.sql.ResultSetMetaData{
  private final String colNames[];
  private final String arr[][];
  private final String tableName;
  private final String colTypes[];

  public JResultSetMetaData(final String colNames[],final String arr[][], final String tableName, final String colTypes[]){
    this.arr = arr;
    this.colNames = colNames;
    this.tableName = tableName;
    this.colTypes = colTypes;
  }

  @Override
  public int getColumnCount() throws SQLException {
    if (arr == null) {
      final SQLException ex = new SQLException("data access error , error at getColumnCount");
      throw ex;
    }
    return colNames.length;
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
     return (Integer) null;
    }

    if (colTypes[column - 1] == "string") {
      return java.sql.Types.VARCHAR;
    }
    if (colTypes[column - 1] == "integer") {
      return java.sql.Types.INTEGER;
    }
    if (colTypes[column - 1] == "float") {
      return java.sql.Types.FLOAT;
    }
    if (colTypes[column - 1] == "date") {
      return java.sql.Types.DATE;
    }

    final SQLException ex = new SQLException("colType = "+colTypes[column - 1]);
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
