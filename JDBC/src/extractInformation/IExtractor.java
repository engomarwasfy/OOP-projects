package extractInformation;

public interface IExtractor {
  public String getDataBaseName(String[] sql);

  public String getTableName(String[] sql);

  public String[] getCol(String[] sql);

  public String[] getData(String[] sql);

  public String getCondition(String[] sql);

}
