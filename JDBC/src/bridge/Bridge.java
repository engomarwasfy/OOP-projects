package bridge;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import engine.DataBase;
import engine.DropDateBase;
import engine.Table;
import engine.XmlParsingMethods;
import extractInformation.IExtractor;
import extractInformation.UsedDataBase;

/***********************/
public class Bridge {
    private IExtractor extractor;
    private PrintTable print;
    private XmlParsingMethods condParse;

    public Bridge() {
	print = new PrintTable();
	condParse = new XmlParsingMethods();
    }

    private int[] getRows(String databaseName, String tableName, final String condition, String[] sql)
	    throws SQLException {
	ArrayList<Integer> selectThisRow1 = condParse.parseCondition(databaseName, tableName,
		extractor.getCondition(sql));
	int[] selectThisRow = new int[selectThisRow1.size()];
	for (int i = 0; i < selectThisRow.length; i++) {
	    selectThisRow[i] = selectThisRow1.get(i);
	}
	return selectThisRow;
    }

    private Table getTable(String databaseName, String tableName) throws SQLException {
	Table table = Table.getinstance();
	table.setDataBaseName(databaseName);
	try {
	    table.setTableName(tableName);
	} catch (ParserConfigurationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return table;
    }

    private DataBase getdb(String databaseName) {
	DataBase db = DataBase.getinstance();
	db.setdatabaseName(databaseName);
	return db;
    }

    public int[] dirct(final Director dir, final String[] sql, String protocol)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException, SQLException {

	extractor = dir.getExtractor();
	String tableName = extractor.getTableName(sql);
	String dbName = extractor.getDataBaseName(sql);
	condParse.setFileWriter(protocol);
	Table table = null;
	if (!dir.getExtract().equals("create")) {
	    table = getTable(dbName, tableName);
	    table.setFileWriter(protocol);
	}
	DataBase database = getdb(dbName);
	database.setFileWriter(protocol);
	if (dir.getExtract().equals("select")) {
	    if (sql[1].equalsIgnoreCase("DISTINCT")) {
		int[] selectThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
		ArrayList<ArrayList<String>> select = new ArrayList<ArrayList<String>>();
		select = table.select(selectThisRow, extractor.getCol(sql));
		table.selectDistenct(select);
		return new int[0];
	    }
	    int[] selectThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
	    ArrayList<ArrayList<String>> select = new ArrayList<ArrayList<String>>();
	    select = table.select(selectThisRow, extractor.getCol(sql));
	    return new int[0];
	} else if (dir.getExtract().equals("delete")) {
	    int[] deleteThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
	    table.deleteRows(deleteThisRow);
	    return deleteThisRow;
	} else if (dir.getExtract().equals("update")) {
	    int[] updateThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
	    table.update(updateThisRow, extractor.getData(sql));
	    return updateThisRow;
	} else if (dir.getExtract().equals("insert")) {
	    table.insertRow(extractor.getCol(sql), extractor.getData(sql));
	    return new int[1];
	} else if (dir.getExtract().equals("alter")) {
	    if (sql[3].equalsIgnoreCase("add")) {
		table.addCoulm(extractor.getCol(sql)[0], extractor.getData(sql)[0]);
	    } else {
		table.removeCoulm(extractor.getCol(sql)[0]);
	    }
	    return new int[0];
	} else if (dir.getExtract().equals("drop")) {
	    if (sql[1].equalsIgnoreCase("table")) {
		database.dropTable(tableName);
	    } else {
		DropDateBase.drop(dbName);
	    }
	    return new int[0];
	} else if (dir.getExtract().equals("create")) {
	    if (sql[1].equalsIgnoreCase("table")) {
		database.createTable(tableName, extractor.getCol(sql));
	    } else {
		database.create(dbName);
	    }
	    return new int[0];
	} else if (dir.getExtract().equals("use")) {
	    UsedDataBase.setUsedDataBase(dbName);
	    database.setdatabaseName(dbName);
	    return new int[0];
	}
	return new int[0];

    }

    public String[][] dirct2(final Director dir, final String[] sql, String protocol)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException, SQLException {
	extractor = dir.getExtractor();
	String tableName = extractor.getTableName(sql);
	String dbName = extractor.getDataBaseName(sql);
	Table table = null;
	if (!dir.getExtract().equals("create")) {
	    table = getTable(dbName, tableName);
	    table.setFileWriter(protocol);
	}
	DataBase database = getdb(dbName);
	if (dir.getExtract().equals("select")) {
	    if (sql[1].equalsIgnoreCase("DISTINCT")) {
		int[] selectThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
		ArrayList<ArrayList<String>> select = new ArrayList<ArrayList<String>>();
		select = table.select(selectThisRow, extractor.getCol(sql));
		return table.selectDistenct(select);
	    }
	    int[] selectThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
	    ArrayList<ArrayList<String>> select = new ArrayList<ArrayList<String>>();
	    select = table.select(selectThisRow, extractor.getCol(sql));
	    String[][] selected = new String[0][0];
	    if (select.size() > 0) {
		selected = new String[select.size()][select.get(0).size()];
	    }
	    for (int i = 0; i < select.size(); i++) {
		for (int j = 0; j < select.get(0).size(); j++) {
		    selected[i][j] = select.get(i).get(j);
		}
	    }
	    return selected;
	} else if (dir.getExtract().equals("delete")) {
	    int[] deleteThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
	    table.deleteRows(deleteThisRow);
	    return new String[0][0];
	} else if (dir.getExtract().equals("update")) {
	    int[] updateThisRow = getRows(dbName, tableName, extractor.getCondition(sql), sql);
	    table.update(updateThisRow, extractor.getData(sql));
	    return new String[0][0];
	} else if (dir.getExtract().equals("insert")) {
	    table.insertRow(extractor.getCol(sql), extractor.getData(sql));
	    return new String[0][0];
	} else if (dir.getExtract().equals("alter")) {
	    if (sql[3].equalsIgnoreCase("add")) {
		table.addCoulm(extractor.getCol(sql)[0], extractor.getData(sql)[0]);
	    } else {
		table.removeCoulm(extractor.getCol(sql)[0]);
	    }
	    return new String[0][0];
	} else if (dir.getExtract().equals("drop")) {
	    if (sql[1].equalsIgnoreCase("table")) {
		database.dropTable(tableName);
	    } else {
		DropDateBase.drop(dbName);
	    }
	    return new String[0][0];
	} else if (dir.getExtract().equals("create")) {
	    if (sql[1].equalsIgnoreCase("table")) {
		database.createTable(tableName, extractor.getCol(sql));
	    } else {
		database.create(dbName);
	    }
	    return new String[0][0];
	} else if (dir.getExtract().equals("use")) {
	    UsedDataBase.setUsedDataBase(dbName);
	    database.setdatabaseName(dbName);
	    return new String[0][0];
	}
	return new String[0][0];

    }
}