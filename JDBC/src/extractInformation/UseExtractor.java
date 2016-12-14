package extractInformation;

public class UseExtractor implements IExtractor {

    @Override
    public String getDataBaseName(String[] sql) {
	UsedDataBase.setUsedDataBase(sql[1]);
	return sql[1];
    }

    @Override
    public String getTableName(String[] sql) {
	// TODO Auto-generated method stub
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
