package eg.edu.alexu.csd.oop.jdbc.engine.cs42;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public interface ImethodOfDataBase {

    public void create(String dbName) throws IOException;
    
    public void dropTable(String tableName);
    
    public void createTable(String tableName , String[] cols) ;
}
