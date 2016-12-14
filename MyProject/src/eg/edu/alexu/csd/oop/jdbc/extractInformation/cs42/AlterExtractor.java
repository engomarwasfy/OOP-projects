package eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42;

public class AlterExtractor implements IExtractor{

    @Override
    public String getDataBaseName(String[] sql) {
	 return UsedDataBase.getUsedDataBase();
    }

    @Override
    public String getTableName(String[] sql) {
	return sql[2];
    }

    @Override
    public String[] getCol(String[] sql) {
	String[] colum = new String[1];
	if(sql[3].equalsIgnoreCase("add")) {
	    colum[0] = sql[4];
	} else {
	    colum[0] = sql[5];
	}
	return colum;
    }

    @Override
    public String[] getData(String[] sql) {
	String[] dataType = new String[1];
	if(sql[3].equalsIgnoreCase("add")) {
	    dataType[0] = sql[5];
	    return dataType;
	}
	return null;
    }

    @Override
    public String getCondition(String[] sql) {
	return null;
    }

}
