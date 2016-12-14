package extractInformation;

public class UpdateExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    // TODO Auto-generated method stub
    return UsedDataBase.getUsedDataBase();
  }

  @Override
  public String getTableName(final String[] sql) {
    // TODO Auto-generated method stub
    return sql[1];
  }

  @Override
  public String[] getCol(final String[] sql) {
    return null;
  }

  @Override
  public String[] getData(final String[] sql) {
    return sql[3].split(",");
  }

  @Override
  public String getCondition(final String[] sql) {
    if (sql.length == 5) {
      return null;
    }
    return sql[5];
  }
}
