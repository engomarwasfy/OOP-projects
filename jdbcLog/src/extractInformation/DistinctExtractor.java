package extractInformation;

public class DistinctExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    // TODO Auto-generated method stub
    return UsedDataBase.getUsedDataBase();
  }

  @Override
  public String getTableName(final String[] sql) {
    // TODO Auto-generated method stub
    return sql[4];
  }

  @Override
  public String[] getCol(final String[] sql) {

    return sql[2].split(",");
  }

  @Override
  public String[] getData(final String[] sql) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getCondition(final String[] sql) {
    // TODO Auto-generated method stub
    if (sql.length == 6) {
      return null;
    }
    return sql[6];
  }

}
