package engine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ImethodOfTable {

  public int[] deleteRows(int[] deleteThisRow) throws SQLException;

  public ArrayList<ArrayList<String>> select(int[] selectThisRow, String[] cols)
      throws SQLException;

  public int[] update(int[] updateThisRow, String[] colsData)
      throws SQLException;

  public void insertRow(String[] cols, String[] colsData) throws SQLException;

  public void addCoulm(String colName, String colType) throws SQLException;

  public void removeCoulm(String colName) throws SQLException;

}
