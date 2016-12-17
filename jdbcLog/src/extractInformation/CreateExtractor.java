package extractInformation;

public class CreateExtractor implements IExtractor {

  @Override
  public String getDataBaseName(final String[] sql) {
    if (sql[1].equalsIgnoreCase("database")) {
      return sql[2];
    } else {
      // here how to get dataBaseName
      return UsedDataBase.getUsedDataBase();
    }
  }

  @Override
  public String getTableName(final String[] sql) {
    if (sql[1].equalsIgnoreCase("database")) {
      return null;
    } else {
      return sql[2];
    }

  }

  @Override
  // col1 int col2 varchar col3 int
  public String[] getCol(final String[] sql) {
    if (sql.length == 4) {
      return null;
    }
    String str = "";
    for (int i = 4; i < sql.length - 1; i++) {
      if (!sql[i].equals(")") && !sql[i + 1].equals(";")) {
        str = str + " " + sql[i];
      }
    }
    str = str.trim();
    final String[] arr = str.split(",");
    return arr;
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