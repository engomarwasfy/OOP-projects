package eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42;

public class SelectExtractor implements IExtractor {

    @Override
    public String getDataBaseName(final String[] sql) {
	// TODO Auto-generated method stub
	return UsedDataBase.getUsedDataBase();
    }

    @Override
    public String getTableName(final String[] sql) {
	// TODO Auto-generated method stub
	return sql[3];
    }

    // returns {*} if *
    @Override
    public String[] getCol(final String[] sql) {

	return sql[1].split(",");
    }

    @Override
    public String[] getData(final String[] sql) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getCondition(final String[] sql) {
	// TODO Auto-generated method stub
	if (sql.length == 5) {
	    return null;
	}
	// return sql[5].replace("'", "");
	return sql[5];
    }

}
