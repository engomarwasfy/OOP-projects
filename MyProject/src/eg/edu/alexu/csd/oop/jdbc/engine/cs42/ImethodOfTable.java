package eg.edu.alexu.csd.oop.jdbc.engine.cs42;

import java.util.ArrayList;

public interface ImethodOfTable {
    
    public int[] deleteRows(int[] deleteThisRow);
    
    public ArrayList<ArrayList<String>> select(int[] selectThisRow , String[] cols);
    
    public int[] update(int[] updateThisRow , String[] colsData);
    
    public void insertRow(String[] cols , String[] colsData);
    
    public void addCoulm(String colName , String colType);
    
    public void removeCoulm(String colName);

}
