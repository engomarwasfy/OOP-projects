package eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42;

public class DeleteExtractor implements IExtractor {

    @Override
    public String getDataBaseName(String[] sql) {
	// TODO Auto-generated method stub
	return UsedDataBase.getUsedDataBase();
    }

    @Override
    public String getTableName(String[] sql) {
	// TODO Auto-generated method stub
	return sql[2];
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
	if(sql.length == 4) {
	    return null;
	}
	return sql[4];
    }

}
