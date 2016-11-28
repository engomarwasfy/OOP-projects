package XmlMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
    public void update(final File xml, final String[] col, final String[] data, final String condition)
	    throws SAXException, IOException, ParserConfigurationException {

	update1.update(xml, data, condition);

    }

    @Override
    public String[][] select(final File xml, final String[] col, final String condition)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException {

	final XmlParsingMethods x = new XmlParsingMethods();
	final ArrayList<Integer> index = x.parseCondition(xml, condition);
	final int[] indexes = new int[index.size()];
	for (int i = 0; i < index.size(); i++) {
	    indexes[i] = index.get(i);
	}

	if (condition == null && col.length == 1 && col[0].equals("*")) {
	    String[] cols = null;
	    try {
		cols = SchemaFile
			.read(UsedDataBase.getUsedDataBase(), xml.getName().substring(0, xml.getName().indexOf('.')))
			.split(",");
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return select.select(xml, cols, indexes);
	}
	if (condition != null && col.length == 1 && col[0].equals("*"))
	    try {
		{
		    final String tableName = xml.getName().substring(0, xml.getName().indexOf('.'));
		    String arr[] = null;
		    try {
			arr = SchemaFile.read(UsedDataBase.getUsedDataBase(), tableName).split(",");
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    return select.select(xml, arr, indexes);
		}
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	return select.select(xml, col, indexes);
    }

    /**
     * []col the columns that will be filled with data "unfilled = null". []data
     * the data that will be added depend on []col.
     * 
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    @Override
    public void insertIntoTable(final File xml, final String[] col, final String[] data)
	    throws ParserConfigurationException, SAXException, IOException, TransformerException {
	insertRow.insertIntoTable(xml, col, data);
    }

    /**
     * @throws IOException
     *
     */
    @Override
    public void dropTable(final File xml) throws IOException {
	dropTable.delete(xml);
    }

    /**
     * @throws IOException
     *
     */
    @Override
    public void dropDataBase(final File x) throws IOException {
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
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws TransformerException
     *
     */
    @Override
    public void createTable(final String dataBaseName, final String xmlName, final String[] col)
	    throws TransformerException, IOException, ParserConfigurationException {
	createTable.createTable(dataBaseName, xmlName, col);
    }

    @Override
    public void delete(final File xml, final int[] deleteThisRows)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException {
	// i added arfument 0 here

	delete.delete(xml, deleteThisRows);
    }
}
