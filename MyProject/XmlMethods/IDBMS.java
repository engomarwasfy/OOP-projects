package XmlMethods;


import java.io.File;

public interface IDBMS {
  public void update(File xml ,String []col,String[]data,String condition);
  /**
   * @param xml table XML file.
   * @param col array of the wanted columns to be selected.
   * @param condiation if null -> selectAll of col[].
   * @param numOfCols number of columns of the table of the data.
   * @return selected array of strings.
   */
  public String[][] select(final File xml, final String[] col, final String condition);
  public void dropTable(File xml);
  public void dropDataBase(File x);

  /**
   * @param xml table XML file.
   * @param deleteThisRows from small to large numbers (Ascendancy).
   */
  public void delete (File xml ,int[] deleteThisRows);
  public void createDataBase(String name);
  public void createTable(String dataBaseName ,String xmlName,String []col);
  public void insertIntoTable(File xml ,String []col, String[]data);
}
