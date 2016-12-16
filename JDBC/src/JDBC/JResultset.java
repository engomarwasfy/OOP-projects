package JDBC;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class JResultset implements ResultSet {
  private final String colNames[];
  private String arr[][];
  private final String colTypes[];
  private int cursor = -1;
  private final int rowsNum;
  private final String tableName;
  public JResultset(final String[][] list, final ArrayList<String> colNames,
      final String tableName, final ArrayList<String> colTypes) {
    this.arr = list;
    this.rowsNum = arr.length;
    this.tableName = tableName;
    this.colNames = convertColToArray(colNames);
    this.colTypes = convertColToArray(colTypes);
  }

  private String[] convertColToArray(final ArrayList<String> list) {
    final String names[] = new String[list.size()];
    for (int i = 0; i < names.length; i++) {
      names[i] = list.get(i);
    }
    return names;
  }

  @Override
  public boolean absolute(final int row) throws SQLException {
    if (Math.abs(row) > 0 && Math.abs(row) <= rowsNum) {
      if (row >= 0) {
        cursor = row - 1;
      } else {
        cursor = row + rowsNum;
      }
      return true;
    }
    if (row <= 0) {
      cursor = -1;
    } else if (row > 0) {
      cursor = rowsNum;
    }
    return false;
  }

  @Override
  public void afterLast() throws SQLException {
    cursor = rowsNum;
  }

  @Override
  public void beforeFirst() throws SQLException {
    cursor = -1;
  }

  @Override
  public void close() throws SQLException {
    arr = null;
  }

  @Override
  public int findColumn(final String columnLabel) throws SQLException {
    for (int i = 0; i < colNames.length; i++) {
      if (colNames[i].equalsIgnoreCase(columnLabel)) {
        return i + 1;
      }
    }
    final SQLException ex = new SQLException(
        "data access error , error at findColumn()");
    throw ex;
  }

  @Override
  public boolean first() throws SQLException {
    if (arr.length > 0) {
      cursor = 0;
      return true;
    }
    return false;
  }

  @Override
  public int getInt(final int columnIndex) throws SQLException {
    if (arr == null || columnIndex < 1 || columnIndex > arr[cursor].length
        || cursor < 0 || cursor >= arr.length) {
      final SQLException ex = new SQLException(
          "data access error , error at getInt(int colIndex)");
      throw ex;
    }
    if (arr[cursor][columnIndex - 1] == null) {
      return 0;
    }
    return Integer.parseInt(arr[cursor][columnIndex - 1]);
  }

  @Override
  public int getInt(final String columnLabel) throws SQLException {
    int colIndex = 0;
    boolean found = false;
    for (int i = 0; i < colNames.length; i++) {
      if (colNames[i] == columnLabel) {
        colIndex = i + 1;
        found = true;
        break;
      }
    }
    if (found == false || arr == null || cursor < 0 || cursor >= arr.length) {
      final SQLException ex = new SQLException(
          "data access error , error at getInt(String colLable)");
      throw ex;
    }
    if (arr[cursor][colIndex - 1] == null) {
      return 0;
    }
    return Integer.parseInt(arr[cursor][colIndex - 1]);
  }

  @Override
  public Date getDate(final int columnIndex) throws SQLException {
    if (arr == null || columnIndex < 1 || columnIndex > arr[cursor].length
        || cursor < 0 || cursor >= arr.length) {
      return null;
    }
    if (arr[cursor][columnIndex - 1] == null) {
      return null;
    }
    if (arr[cursor][columnIndex - 1].equals("null")) {
      return null;
    }
  //  final String[] DateArray = arr[cursor][columnIndex - 1].split("-");
    return java.sql.Date.valueOf(arr[cursor][columnIndex-1]);

    //return new Date(Integer.parseInt(DateArray[0]),
      //  Integer.parseInt(DateArray[1]), Integer.parseInt(DateArray[2]));
  }

  // NEW
  @Override
  public Date getDate(final String columnLabel) throws SQLException {
    int colIndex = 0;
    boolean found = false;
    for (int i = 0; i < colNames.length; i++) {
      if (colNames[i] == columnLabel) {
        colIndex = i + 1;
        found = true;
        break;
      }
    }
    if (cursor < 0 || cursor >= arr.length) {
      return null;
    }
    if (found == false || arr == null) {
      return null;
    }
    if (arr[cursor][colIndex - 1].equals("null")) {
      return null;
    }
    if (arr[cursor][colIndex - 1] == null) {
      return null;
    }
    return java.sql.Date.valueOf(arr[cursor][colIndex-1]);
//    final String[] DateArray = arr[cursor][colIndex - 1].split("-");
//
//    return new Date(Integer.parseInt(DateArray[0]),
//        Integer.parseInt(DateArray[1]), Integer.parseInt(DateArray[2]));
  }

  @Override
  public String getString(final int columnIndex) throws SQLException {
    if (arr == null || columnIndex < 1 || columnIndex > arr[cursor].length
        || cursor < 0 || cursor >= arr.length) {
      final SQLException ex = new SQLException(
          "data access error , error at getString(String columnIndex)");
      throw ex;
    }
    if (arr[cursor][columnIndex - 1] == null) {
      return null;
    }
    return arr[cursor][columnIndex - 1];

  }

  @Override
  public String getString(final String columnLabel) throws SQLException {
    int colIndex = 0;
    boolean found = false;
    for (int i = 0; i < colNames.length; i++) {
      if (colNames[i] == columnLabel) {
        colIndex = i + 1;
        found = true;
        break;
      }
    }
    if (found == false || arr == null || cursor < 0 || cursor >= arr.length) {
      final SQLException ex = new SQLException(
          "data access error , error at getString(String columnLabel)");
      throw ex;
    }
    if (arr[cursor][colIndex - 1] == null) {
      return null;
    }
    return arr[cursor][colIndex - 1];

  }

  @Override
  public float getFloat(final int columnIndex) throws SQLException {
    if (arr == null || columnIndex < 1 || columnIndex > arr[cursor].length
        || cursor < 0 || cursor >= arr.length) {
      final SQLException ex = new SQLException(
          "data access error , error at getFloat(String columnIndex)");
      throw ex;
    }
    if (arr[cursor][columnIndex - 1] == null) {
      return 0;
    }
    return Float.parseFloat(arr[cursor][columnIndex - 1]);
  }

  @Override
  public float getFloat(final String columnLabel) throws SQLException {
    int colIndex = 0;
    boolean found = false;
    for (int i = 0; i < colNames.length; i++) {
      if (colNames[i] == columnLabel) {
        colIndex = i + 1;
        found = true;
        break;
      }
    }
    if (found == false || arr == null || cursor < 0 || cursor >= arr.length) {
      final SQLException ex = new SQLException(
          "data access error , error at getFloat(final String columnLabel)");
      throw ex;
    }
    if (arr[cursor][colIndex - 1] == null) {
      return 0;
    }
    return Float.parseFloat(arr[cursor][colIndex - 1]);
  }

  // new2
  @Override
  public ResultSetMetaData getMetaData() throws SQLException {
    return new JResultSetMetaData(colNames, arr, tableName, colTypes);
  }

  @Override
  public Object getObject(final int columnIndex) throws SQLException {
    if (arr == null || columnIndex < 1 || columnIndex > arr[cursor].length
        || cursor < 0 || cursor >= arr.length) {
      throw new SQLException();
    }
    if (arr[cursor][columnIndex - 1] == null) {
      return 0;
    }
    if(colTypes[columnIndex-1].equalsIgnoreCase("string")){
    	return arr[cursor][columnIndex - 1];
    }
    else if(colTypes[columnIndex-1].equalsIgnoreCase("int")){
    	return Integer.parseInt(arr[cursor][columnIndex - 1]);
    }else if ( colTypes[columnIndex-1].equalsIgnoreCase("float")){
    	Float.parseFloat(arr[cursor][columnIndex-1]);
    }else if ( colTypes[columnIndex-1].equalsIgnoreCase("date")){
    	return java.sql.Date.valueOf(arr[cursor][columnIndex-1]);
    }
    return arr[cursor][columnIndex - 1];
  }

  /********************* not done ******************************/
  @Override
  public Statement getStatement() throws SQLException {
    return null;
  }

  @Override
  public boolean isAfterLast() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    return cursor == arr.length;
  }

  @Override
  public boolean isBeforeFirst() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    return cursor == -1;
  }

  @Override
  public boolean isClosed() throws SQLException {
    return arr == null;
  }

  @Override
  public boolean isFirst() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    return cursor == 0;
  }

  @Override
  public boolean isLast() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    return cursor == arr.length - 1;
  }

  @Override
  public boolean last() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    if (arr.length == 0) {
      return false;
    }
    cursor = arr.length - 1;
    return true;
  }

  @Override
  public boolean next() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    if (cursor >= arr.length - 1) {
      afterLast();
      return false;
    }
    cursor++;
    return true;
  }

  @Override
  public boolean previous() throws SQLException {
    if (arr == null) {
      throw new SQLException();
    }
    if (cursor <= 0) {
      cursor = -1;
      return false;
    }
    cursor--;
    return true;
  }

  @Override
  public boolean isWrapperFor(final Class<?> iface) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public <T> T unwrap(final Class<T> iface) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public void cancelRowUpdates() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void clearWarnings() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void deleteRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Array getArray(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Array getArray(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public InputStream getAsciiStream(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public InputStream getAsciiStream(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public BigDecimal getBigDecimal(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public BigDecimal getBigDecimal(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public BigDecimal getBigDecimal(final int arg0, final int arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public BigDecimal getBigDecimal(final String arg0, final int arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public InputStream getBinaryStream(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public InputStream getBinaryStream(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Blob getBlob(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Blob getBlob(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean getBoolean(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean getBoolean(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public byte getByte(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public byte getByte(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public byte[] getBytes(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public byte[] getBytes(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Reader getCharacterStream(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Reader getCharacterStream(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Clob getClob(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Clob getClob(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public int getConcurrency() throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public String getCursorName() throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Date getDate(final int arg0, final Calendar arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Date getDate(final String arg0, final Calendar arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public double getDouble(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public double getDouble(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public int getFetchDirection() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public int getFetchSize() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public int getHoldability() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public long getLong(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public long getLong(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Reader getNCharacterStream(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Reader getNCharacterStream(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public NClob getNClob(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public NClob getNClob(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public String getNString(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public String getNString(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Object getObject(final String arg0) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getObject(final int arg0, final Map<String, Class<?>> arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Object getObject(final String arg0, final Map<String, Class<?>> arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public <T> T getObject(final int arg0, final Class<T> arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public <T> T getObject(final String arg0, final Class<T> arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Ref getRef(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Ref getRef(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public int getRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public RowId getRowId(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public RowId getRowId(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public SQLXML getSQLXML(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public SQLXML getSQLXML(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public short getShort(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public short getShort(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public Time getTime(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Time getTime(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Time getTime(final int arg0, final Calendar arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Time getTime(final String arg0, final Calendar arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Timestamp getTimestamp(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Timestamp getTimestamp(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Timestamp getTimestamp(final int arg0, final Calendar arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public Timestamp getTimestamp(final String arg0, final Calendar arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public int getType() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public URL getURL(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public URL getURL(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public InputStream getUnicodeStream(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public InputStream getUnicodeStream(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void insertRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void moveToCurrentRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void moveToInsertRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void refreshRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean relative(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean rowDeleted() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean rowInserted() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean rowUpdated() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void setFetchDirection(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void setFetchSize(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateArray(final int arg0, final Array arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateArray(final String arg0, final Array arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateAsciiStream(final int arg0, final InputStream arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateAsciiStream(final String arg0, final InputStream arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateAsciiStream(final int arg0, final InputStream arg1,
      final int arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateAsciiStream(final String arg0, final InputStream arg1,
      final int arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateAsciiStream(final int arg0, final InputStream arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateAsciiStream(final String arg0, final InputStream arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBigDecimal(final int arg0, final BigDecimal arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBigDecimal(final String arg0, final BigDecimal arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBinaryStream(final int arg0, final InputStream arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBinaryStream(final String arg0, final InputStream arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBinaryStream(final int arg0, final InputStream arg1,
      final int arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBinaryStream(final String arg0, final InputStream arg1,
      final int arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBinaryStream(final int arg0, final InputStream arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBinaryStream(final String arg0, final InputStream arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBlob(final int arg0, final Blob arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBlob(final String arg0, final Blob arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBlob(final int arg0, final InputStream arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBlob(final String arg0, final InputStream arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBlob(final int arg0, final InputStream arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBlob(final String arg0, final InputStream arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBoolean(final int arg0, final boolean arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBoolean(final String arg0, final boolean arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateByte(final int arg0, final byte arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateByte(final String arg0, final byte arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBytes(final int arg0, final byte[] arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateBytes(final String arg0, final byte[] arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateCharacterStream(final int arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateCharacterStream(final String arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateCharacterStream(final int arg0, final Reader arg1,
      final int arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateCharacterStream(final String arg0, final Reader arg1,
      final int arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateCharacterStream(final int arg0, final Reader arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateCharacterStream(final String arg0, final Reader arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateClob(final int arg0, final Clob arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateClob(final String arg0, final Clob arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateClob(final int arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateClob(final String arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateClob(final int arg0, final Reader arg1, final long arg2)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateClob(final String arg0, final Reader arg1, final long arg2)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateDate(final int arg0, final Date arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateDate(final String arg0, final Date arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateDouble(final int arg0, final double arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateDouble(final String arg0, final double arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateFloat(final int arg0, final float arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateFloat(final String arg0, final float arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateInt(final int arg0, final int arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateInt(final String arg0, final int arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateLong(final int arg0, final long arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateLong(final String arg0, final long arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNCharacterStream(final int arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNCharacterStream(final String arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNCharacterStream(final int arg0, final Reader arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNCharacterStream(final String arg0, final Reader arg1,
      final long arg2) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNClob(final int arg0, final NClob arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNClob(final String arg0, final NClob arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNClob(final int arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNClob(final String arg0, final Reader arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNClob(final int arg0, final Reader arg1, final long arg2)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNClob(final String arg0, final Reader arg1, final long arg2)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNString(final int arg0, final String arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNString(final String arg0, final String arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateNull(final int arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public void updateNull(final String arg0) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateObject(final int arg0, final Object arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateObject(final String arg0, final Object arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateObject(final int arg0, final Object arg1, final int arg2)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateObject(final String arg0, final Object arg1, final int arg2)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateRef(final int arg0, final Ref arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateRef(final String arg0, final Ref arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateRow() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateRowId(final int arg0, final RowId arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateRowId(final String arg0, final RowId arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateSQLXML(final int arg0, final SQLXML arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateSQLXML(final String arg0, final SQLXML arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateShort(final int arg0, final short arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateShort(final String arg0, final short arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateString(final int arg0, final String arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateString(final String arg0, final String arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateTime(final int arg0, final Time arg1) throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateTime(final String arg0, final Time arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateTimestamp(final int arg0, final Timestamp arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public void updateTimestamp(final String arg0, final Timestamp arg1)
      throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

  @Override
  public boolean wasNull() throws SQLException {
    throw new java.lang.UnsupportedOperationException();

  }

}
