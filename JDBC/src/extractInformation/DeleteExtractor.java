package extractInformation;

public class DeleteExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    // TODO Auto-generated method stub
    return UsedDataBase.getUsedDataBase();
  }

  @Override
  public String getTableName(final String[] sql) {
    // TODO Auto-generated method stub
    return sql[2];
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
    if (sql.length == 4) {
      return null;
    }
    return sql[4];
  }

}
