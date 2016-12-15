package extractInformation;

public class InsertExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {

    return UsedDataBase.getUsedDataBase();
  }

  @Override
  public String getTableName(final String[] sql) {
    return sql[2];
  }

  @Override
  public String[] getCol(final String[] sql) {
    if (sql[3].equalsIgnoreCase("values")) {
      return null;
    } else {
      return sql[4].split(",");
    }

  }

  @Override
  public String[] getData(final String[] sql) {
    if (sql[3].equalsIgnoreCase("values")) {
      return sql[5].split(",");
    } else {
      return sql[8].split(",");
    }

  }

  @Override
  public String getCondition(final String[] sql) {
    return null;
  }

}
