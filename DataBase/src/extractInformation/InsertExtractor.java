package extractInformation;

public class InsertExtractor implements IExtractor {

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
		if(sql[3].equalsIgnoreCase("values")){
		return null;
		} else {
			return sql[4].split(",");
		}
		
	}

	@Override
	public String[] getData(String[] sql) {
		if(sql[3].equalsIgnoreCase("values")){
			return sql[5].split(",");
			} else {
				return sql[8].split(",");
			}
		
	}

	@Override
	public String getCondition(String[] sql) {
		return null;
	}

}
