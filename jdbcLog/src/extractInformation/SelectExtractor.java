package extractInformation;

public class SelectExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    // TODO Auto-generated method stub
    return UsedDataBase.getUsedDataBase();
  }

  @Override
  public String getTableName(final String[] sql) {
    if (sql[1].equalsIgnoreCase("distinct")) {
      return sql[4];
    }
    return sql[3];
  }

  // returns {*} if *
  @Override
  public String[] getCol(final String[] sql) {
    if (sql[1].equalsIgnoreCase("distinct")) {
      return sql[2].split(",");
    }
    return sql[1].split(",");
  }

  @Override
  public String[] getData(final String[] sql) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getCondition(final String[] sql) {
    // TODO Auto-generated method stub
    if (sql.length == 5 || sql.length == 6) {
      return null;
    }
    if (sql[1].equalsIgnoreCase("distinct")) {
      return sql[6];
    }
    return sql[5];
  }

}
