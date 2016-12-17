package extractInformation;

public class AlterExtractor implements IExtractor {

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
    final String[] colum = new String[1];
    if (sql[3].equalsIgnoreCase("add")) {
      colum[0] = sql[4];
    } else {
      colum[0] = sql[5];
    }
    return colum;
  }

  @Override
  public String[] getData(final String[] sql) {
    final String[] dataType = new String[1];
    if (sql[3].equalsIgnoreCase("add")) {
      dataType[0] = sql[5];
      return dataType;
    }
    return null;
  }

  @Override
  public String getCondition(final String[] sql) {
    return null;
  }

}
