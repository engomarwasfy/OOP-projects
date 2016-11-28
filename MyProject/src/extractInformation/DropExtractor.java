package extractInformation;

public class DropExtractor implements IExtractor {

    @Override
    public String getDataBaseName(String[] sql) {
	if (sql[1].equalsIgnoreCase("database")) {
	    return sql[2];
	}
	return null;
    }

    @Override
    public String getTableName(String[] sql) {
	if (sql[1].equalsIgnoreCase("table")) {
	    return sql[2];
	}
	return null;
    }

    @Override
    public String[] getCol(String[] sql) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String[] getData(String[] sql) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getCondition(String[] sql) {
	// TODO Auto-generated method stub
	return null;
    }

}
