package engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public class JsonFile implements IFile {

    @Override
    public ArrayList<ArrayList<String>> read(String databaseName, String tableName)
	    throws ParserConfigurationException, SAXException, IOException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void write(ArrayList<ArrayList<String>> data, String dataBaseName, String xmlName, ArrayList<String> cols,
	    ArrayList<String> types) throws ParserConfigurationException, TransformerConfigurationException,
	    TransformerFactoryConfigurationError {
	// TODO Auto-generated method stub

    }

    @Override
    public ArrayList<String> getcols(String dataBaseName, String tableName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<String> getcolsTypes(String dataBaseName, String tableName) {
	// TODO Auto-generated method stub
	return null;
    }

}
