package XmlMethods;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface IDBMS {
    public void update(File xml, String[] col, String[] data, String condition)
	    throws SAXException, IOException, ParserConfigurationException;

    /**
     * @param xml
     *            table XML file.
     * @param col
     *            array of the wanted columns to be selected.
     * @param condiation
     *            if null -> selectAll of col[].
     * @param numOfCols
     *            number of columns of the table of the data.
     * @return selected array of strings.
     */
    public String[][] select(final File xml, final String[] col, final String condition)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException;

    public void dropTable(File xml) throws IOException;

    public void dropDataBase(File x) throws IOException;

    /**
     * @param xml
     *            table XML file.
     * @param deleteThisRows
     *            from small to large numbers (Ascendancy).
     */
    public void delete(File xml, int[] deleteThisRows)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException;

    public void createDataBase(String name);

    public void createTable(String dataBaseName, String xmlName, String[] col)
	    throws TransformerException, IOException, ParserConfigurationException;

    public void insertIntoTable(File xml, String[] col, String[] data)
	    throws ParserConfigurationException, SAXException, IOException, TransformerException;
}
