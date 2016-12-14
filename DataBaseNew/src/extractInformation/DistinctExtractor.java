package extractInformation;

public class DistinctExtractor implements IExtractor {

    @Override
    public String getDataBaseName(String[] sql) {
	return UsedDataBase.getUsedDataBase();
    }

    @Override
    public String getTableName(String[] sql) {
	return sql[4];
    }

    @Override
    public String[] getCol(String[] sql) {
	return sql[2].split(",");
    }

    @Override
    public String[] getData(String[] sql) {
	return null;
    }

    @Override
    public String getCondition(String[] sql) {
	return null;
    }

}
