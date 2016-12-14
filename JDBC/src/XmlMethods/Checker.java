package XmlMethods;

import extractInformation.UsedDataBase;

public class Checker {

    public static boolean checkColsFound(String tableName, String[] cols) {
	String database = UsedDataBase.getUsedDataBase();
	String[] coulmsOfTable = SchemaFile.read(database, tableName).split(",");
	int m = 0;
	for (int i = 0; i < cols.length; i++) {
	    boolean found = false;
	    for (int j = m; j < coulmsOfTable.length; j++) {
		if (cols[i].equalsIgnoreCase(coulmsOfTable[j])) {
		    m = i;
		    found = true;
		    break;
		}
	    }
	    if (!found) {
		return false;
	    }
	}
	return true;
    }

    public static boolean checkColsFound2(String tableName, String[] cols) {
	String database = UsedDataBase.getUsedDataBase();
	String[] coulmsOfTable = SchemaFile.read(database, tableName).split(",");
	for (int i = 0; i < cols.length; i++) {
	    boolean found = false;
	    for (int j = 0; j < coulmsOfTable.length; j++) {
		if (cols[i].equalsIgnoreCase(coulmsOfTable[j])) {
		    found = true;
		    break;
		}
	    }
	    if (!found) {
		return false;
	    }
	}
	return true;
    }
}