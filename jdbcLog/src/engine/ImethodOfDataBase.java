package engine;

import java.io.IOException;
import java.sql.SQLException;

public interface ImethodOfDataBase {

  public void create(String dbName) throws IOException;

  public void dropTable(String tableName);

  public void createTable(String tableName, String[] cols) throws SQLException;
}
