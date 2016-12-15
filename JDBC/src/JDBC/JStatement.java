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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import javax.swing.text.html.parser.DTD;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import bridge.Bridge;
import bridge.Director;
import bridge.ExtractorFactory;
import engine.DataBase;
import engine.IFile;
import engine.JsonFile;
import engine.XmlFile;
import extractInformation.IExtractor;
import extractInformation.UsedDataBase;
import validateSyntax.OrganizeInput;
import validateSyntax.Parser;

public class JStatement implements Statement {

    private String lastSelect = "";
    private Parser s = new Parser();
    private Director director = new Director();
    private Bridge bridge = new Bridge();
    private static JStatement instance = new JStatement();
    private static String protocol = "xmldb";
    private static IFile fileWriter = new XmlFile();
    private static int updateCount = 0;
    private static int QueryTimeout = 0;

    private JStatement() {

    }

    public static JStatement getinstance() {
	updateCount = 0;
	if (instance == null) {
	    instance = new JStatement();
	}
	return instance;
    }

    public static void setProtocol(String newProtocol) {
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

    private String selectAll(String tableName) {
	String selectall = "select * from" + tableName;
	return selectall;
    }

    @Override
    public void addBatch(String arg0) throws SQLException {
	String databaseName = UsedDataBase.getUsedDataBase();
	String tmp = System.getProperty("java.io.tmpdir");
	File batch = new File(tmp + "batches" + File.separator + databaseName + ".txt");
	// load
	ArrayList<String> load = new ArrayList<String>();
	FileReader fr;
	BufferedReader br = null;
	try {
	    fr = new FileReader(batch);
	    br = new BufferedReader(fr);
	    String scan;
	    while ((scan = br.readLine()) != null) {
		load.add(scan);
	    }
	} catch (IOException e) {
	    final SQLException e1 = new SQLException("not valid statment");
	    throw e1;
	}
	// add
	PrintWriter out = null;
	try {
	    out = new PrintWriter(batch);
	} catch (FileNotFoundException e) {
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
	String databaseName = UsedDataBase.getUsedDataBase();
	String tmp = System.getProperty("java.io.tmpdir");
	File batch = new File(tmp + "batches" + File.separator + databaseName + ".txt");
	PrintWriter out = null;
	try {
	    out = new PrintWriter(batch);
	} catch (FileNotFoundException e) {
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
    public boolean execute(String arg0) throws SQLException {
	String[] arr = null;
		if (arg0.trim().length() != 0) {
	    final String after = OrganizeInput.organize(arg0);
	    arr = after.split(" ");
	    if (s.validate(arr)) {
		director.direct(arr[0].toLowerCase());
		try {
		    bridge.dirct(director, arr, protocol);
		    if(DataBase.isDataBaseHere==true){
			    DataBase.isDataBaseHere=false;
			    return false;
			}

		} catch (Exception e) {
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
		ResultSet result = executeQuery(arg0);
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
	} catch (Exception e) {
	    final SQLException e1 = new SQLException("not valid statment");
	    throw e1;
	}
	return false;
    }

    @Override
    public int[] executeBatch() throws SQLException {
	ArrayList<Integer> mostafa = new ArrayList<Integer>();
	String databaseName = UsedDataBase.getUsedDataBase();
	String tmp = System.getProperty("java.io.tmpdir");
	File batch = new File(tmp + "batches" + File.separator + databaseName + ".txt");
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
				int[] update = bridge.dirct(director, arr, protocol);
				for (int i = 0; i < update.length; i++) {
				    mostafa.add(update[i]);
				}
			    } catch (TransformerException | SAXException | ParserConfigurationException e) {
				final SQLException e1 = new SQLException("not valid statment");
				throw e1;
			    }
			}
		    }
		}
	    }
	} catch (IOException e) {
	    final SQLException e1 = new SQLException("not valid statment");
	    throw e1;
	}
	LinkedHashSet<Integer> omar = new LinkedHashSet<Integer>();
	omar.addAll(mostafa);
	mostafa.clear();
	mostafa.addAll(omar);
	int[] shaban = new int[mostafa.size()];
	for (int i = 0; i < shaban.length; i++) {
	    shaban[i] = mostafa.get(i);
	}
	return shaban;
    }

    @Override
    public ResultSet executeQuery(String arg0) throws SQLException {
	IExtractor formation = null;
	String[] arr = null;
	if (arg0.trim().length() != 0) {
	    final String after = OrganizeInput.organize(arg0);
	    arr = after.split(" ");
	    if (s.validate(arr)) {
		formation = ExtractorFactory.getExtractor(arr[0].toLowerCase());
	    }
	}
	String tablename = formation.getTableName(arr);
	director.direct(arr[0].toLowerCase());
	String[][] result = new String[0][0];
	try {
	    result = bridge.dirct2(director, arr, protocol);
	} catch (TransformerException | SAXException | IOException | ParserConfigurationException e) {
	    final SQLException e1 = new SQLException("not valid statment");
	    throw e1;
	}
	ArrayList<String> colNames = null;
	ArrayList<String> colTypes = null;
	try {
	    colNames = fileWriter.getcols(UsedDataBase.getUsedDataBase(), tablename);
	    colTypes = fileWriter.getcolsTypes(UsedDataBase.getUsedDataBase(), tablename);
	} catch (ParserConfigurationException e) {
	    final SQLException e1 = new SQLException("not valid statment");
	    throw e1;
	}
	if (arr[0].equalsIgnoreCase("select")) {
	    String[] cols = formation.getCol(arr);
	    if (!cols[0].equals("*")) {
		ArrayList<String> colsNames = new ArrayList<String>();
		ArrayList<String> colsTypes = new ArrayList<String>();
		for (int i = 0; i < cols.length; i++) {
		    int index = colNames.indexOf(cols[i]);
		    colsNames.add(colNames.get(index));
		    colsTypes.add(colTypes.get(index));
		}
		JResultset resultset = new JResultset(result, colsNames, tablename, colsTypes);
		return resultset;
	    }
	}
	JResultset resultset = new JResultset(result, colNames, tablename, colTypes);
	return resultset;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
	if (isClosed()) {
	    throw new SQLException();
	}
	int[] result = new int[0];
	if (sql.trim().length() != 0) {
	    final String after = OrganizeInput.organize(sql);
	    final String[] arr = after.split(" ");
	    if (s.validate(arr)) {
		director.direct(arr[0].toLowerCase());
		try {
		    result = bridge.dirct(director, arr, protocol);
		} catch (Exception e) {
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
    public void setQueryTimeout(int arg0) throws SQLException {
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

    private String[][] convertTableToArray(ArrayList<ArrayList<String>> table) {
	String arr[][] = new String[table.size()][table.get(0).size()];
	for (int i = 0; i < table.size(); i++) {
	    for (int j = 0; j < table.get(0).size(); j++) {
		arr[i][j] = table.get(i).get(j);
	    }
	}
	return arr;
    }

    /*******************************************************************/
    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
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
    public boolean execute(String arg0, int arg1) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean execute(String arg0, int[] arg1) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean execute(String arg0, String[] arg1) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int executeUpdate(String arg0, int arg1) throws SQLException {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int executeUpdate(String arg0, int[] arg1) throws SQLException {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int executeUpdate(String arg0, String[] arg1) throws SQLException {
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
    public boolean getMoreResults(int arg0) throws SQLException {
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
    public void setCursorName(String arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setEscapeProcessing(boolean arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setFetchDirection(int arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setFetchSize(int arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setMaxFieldSize(int arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setMaxRows(int arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPoolable(boolean arg0) throws SQLException {
	// TODO Auto-generated method stub

    }

}
