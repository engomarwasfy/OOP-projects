package XmlMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import extractInformation.UsedDataBase;

public class Handler implements IDBMS {
  private final CreateDataBase dataBase;
  private final CreateTable createTable;
  private final DropDateBase_DropTable dropTable;
  private final Delete delete;
  private final InsertIntoTable insertRow;
  private final Select select;
  private final Update update1;

  public Handler() {
    dataBase = new CreateDataBase();
    createTable = new CreateTable();
    dropTable = new DropDateBase_DropTable();
    delete = new Delete();
    insertRow = new InsertIntoTable();
    select = new Select();
    update1 = new Update();
  }

  @Override
  public void update(final File xml, final String[] col, final String[] data,
      final String condition) {
    try {
      update1.update(xml, data, condition);
    } catch (SAXException | IOException | ParserConfigurationException e) {
    	 System.out.println("sql command failed");
    }
  }

  @Override
  public String[][] select(final File xml, final String[] col,final String condition) {
    
	  final XmlParsingMethods x = new XmlParsingMethods();
    final ArrayList<Integer> index = x.parseCondition(xml, condition);
    final int[] indexes = new int[index.size()];
    for (int i = 0; i < index.size(); i++) {
      indexes[i] = index.get(i);
    }
    
    if (condition == null && col.length == 1 && col[0].equals("*")) {
    	String []cols = SchemaFile.read(UsedDataBase.getUsedDataBase(), xml.getName().substring(0, xml.getName().indexOf('.'))).split(",");
      return select.select(xml,cols , indexes);
    }
    if(condition != null && col.length == 1 && col[0].equals("*")){
      final String tableName = xml.getName().substring(0, xml.getName().indexOf('.'));
      final String arr[] =SchemaFile.read(UsedDataBase.getUsedDataBase(), tableName)
      .split(",");
      return select.select(xml, arr,indexes);
    }

    return select.select(xml, col, indexes);
  }

  /**
   * []col the columns that will be filled with data "unfilled = null". []data
   * the data that will be added depend on []col.
   */
  @Override
  public void insertIntoTable(final File xml, final String[] col,
      final String[] data) {
    insertRow.insertIntoTable(xml, col, data);
  }

  /**
   *
   */
  @Override
  public void dropTable(final File xml) {
    dropTable.delete(xml);
  }

  /**
   *
   */
  @Override
  public void dropDataBase(final File x) {
    dropTable.delete(x);
  }

  /**
   *
   */
  @Override
  public void createDataBase(final String name) {
    dataBase.createDataBase(name);
  }

  /**
   *
   */
  @Override
  public void createTable(final String dataBaseName, final String xmlName,
      final String[] col) {
    createTable.createTable(dataBaseName, xmlName, col);
  }

  @Override
  public void delete(final File xml, final int[] deleteThisRows) {
    // i added arfument 0 here

    delete.delete(xml, deleteThisRows);
  }
}
