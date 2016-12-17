package extractInformation;

public class DropExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    if (sql[1].equalsIgnoreCase("database")) {
      return sql[2];
    }
    return UsedDataBase.getUsedDataBase();
  }

  @Override
  public String getTableName(final String[] sql) {
    if (sql[1].equalsIgnoreCase("table")) {
      return sql[2];
    }
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
