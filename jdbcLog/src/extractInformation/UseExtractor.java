package extractInformation;

public class UseExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    UsedDataBase.setUsedDataBase(sql[1]);
    return sql[1];
  }

  @Override
  public String getTableName(final String[] sql) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getCol(final String[] sql) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getData(final String[] sql) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getCondition(final String[] sql) {
    // TODO Auto-generated method stub
    return null;
  }

}
