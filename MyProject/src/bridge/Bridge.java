package bridge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import XmlMethods.Handler;
import XmlMethods.XmlParsingMethods;
import extractInformation.IExtractor;
import extractInformation.UsedDataBase;
/***********************/
public class Bridge {
    Handler handler;
    IExtractor extractor;
    PrintTable print;

    public Bridge() {
	handler = new Handler();
	print = new PrintTable();
    }

    public void dirct(final Director dir, final String[] sql)
	    throws TransformerException, SAXException, IOException, ParserConfigurationException {
	extractor = dir.getExtractor();

	if (dir.getExtract().equals("select")) {
	    final String[][] selected = handler.select(
		    Methods.getXmlFile(UsedDataBase.getUsedDataBase(), extractor.getTableName(sql)),
		    extractor.getCol(sql), extractor.getCondition(sql));

	    print.show(selected, extractor.getTableName(sql), extractor.getCol(sql));

	} else if (dir.getExtract().equals("create")) {

	    if (extractor.getCol(sql) == null) {
		handler.createDataBase(extractor.getDataBaseName(sql));
	    } else {
		// create table must have cols ?????????????????????????????????
		handler.createTable(extractor.getDataBaseName(sql), extractor.getTableName(sql), extractor.getCol(sql));
	    }

	} else if (dir.getExtract().equals("delete")) {
	    // can't do this right know cause i don't know which rows to be
	    // deleted
	    final XmlParsingMethods x = new XmlParsingMethods();
	    final ArrayList<Integer> index = x.parseCondition(
		    Methods.getXmlFile(UsedDataBase.getUsedDataBase(), extractor.getTableName(sql)),
		    extractor.getCondition(sql));

	    final int[] indexes = new int[index.size()];
	    for (int i = 0; i < index.size(); i++) {
		indexes[i] = index.get(i);
	    }

	    handler.delete(Methods.getXmlFile(UsedDataBase.getUsedDataBase(), extractor.getTableName(sql)), indexes);

	} else if (dir.getExtract().equals("drop")) {
	    if (extractor.getTableName(sql) == null) {
		handler.dropDataBase(new File(extractor.getDataBaseName(sql)));
	    } else {
		handler.dropTable(Methods.getXmlFile(UsedDataBase.getUsedDataBase(), extractor.getTableName(sql)));
	    }

	} else if (dir.getExtract().equals("update")) {
	    handler.update(Methods.getXmlFile(extractor.getDataBaseName(sql), extractor.getTableName(sql)),
		    extractor.getCol(sql), extractor.getData(sql), extractor.getCondition(sql));
	} else if (dir.getExtract().equals("insert")) {
	    handler.insertIntoTable(Methods.getXmlFile(UsedDataBase.getUsedDataBase(), extractor.getTableName(sql)),
		    extractor.getCol(sql), extractor.getData(sql));

	}
    }
}