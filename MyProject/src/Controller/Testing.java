package Controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

import XmlMethods.Select;
import XmlMethods.Update;
import validateSyntax.OrganizeInput;
import validateSyntax.Parser;

public class Testing {
    /**
     * Test the Syntax of the command.
     */
    @Test
    public void testSyntex() {
	final String commmandTrue0 = OrganizeInput.organize("CREATE DATABASE db;");
	final String commmandTrue1 = OrganizeInput.organize("USE DATABASE db;");
	final String commmandTrue2 = OrganizeInput
		.organize("INSERT INTO travel( name, city, country) VALUES ( sherif, alex, egypt );");
	final String commmandTrue3 = OrganizeInput
		.organize("UPDATE travel SET name=mostafa ,country=Bambozia  WHERE city='alex';");
	final String commmandTrue4 = OrganizeInput.organize("DELETE FROM travel WHERE city = omar;");
	final String commmandTrue5 = OrganizeInput.organize("DROP TABLE travel;");
	final String commmandTrue6 = OrganizeInput.organize("DROP DATABASE db;");

	// forgotten db.
	final String commmandFalse0 = OrganizeInput.organize("CREATE DATABASE;");
	// no DATABASE.
	final String commmandFalse1 = OrganizeInput.organize("USE db;");
	// no semicolon
	final String commmandFalse2 = OrganizeInput
		.organize("INSERT INTO travel( name, city, country) VALUES ( sherif, alex, egypt )");
	// put -> set
	final String commmandFalse3 = OrganizeInput
		.organize("UPDATE travel PUT name=mostafa ,country=Bambozia  WHERE city='alex';");

	final Parser s = new Parser();
	final String[] arr0 = commmandTrue0.split(" ");
	final String[] arr1 = commmandTrue1.split(" ");
	final String[] arr2 = commmandTrue2.split(" ");
	final String[] arr3 = commmandTrue3.split(" ");
	final String[] arr4 = commmandTrue4.split(" ");
	final String[] arr5 = commmandTrue5.split(" ");
	final String[] arr6 = commmandTrue6.split(" ");

	final String[] arr00 = commmandFalse0.split(" ");
	final String[] arr11 = commmandFalse1.split(" ");
	final String[] arr22 = commmandFalse2.split(" ");
	final String[] arr33 = commmandFalse3.split(" ");

	assertEquals(s.validate(arr0), true);
	assertEquals(s.validate(arr1), true);
	assertEquals(s.validate(arr2), true);
	assertEquals(s.validate(arr3), true);
	assertEquals(s.validate(arr4), true);
	assertEquals(s.validate(arr5), true);
	assertEquals(s.validate(arr5), true);
	assertEquals(s.validate(arr6), true);

	assertEquals(s.validate(arr00), false);
	assertEquals(s.validate(arr11), false);
	assertEquals(s.validate(arr22), false);
	assertEquals(s.validate(arr33), false);
    }

    /**
     * Test of the update Class that update the xml.
     */
    @Test
    public void testUpdate() {
	final Update update = new Update();
	try {
	    update.update(new File("test.xml"), new String[] { "city=london" }, "name=mostafa");
	} catch (final SAXException e) {
	    e.printStackTrace();
	} catch (final IOException e) {
	    e.printStackTrace();
	} catch (final ParserConfigurationException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Testing select class that select data from the xml class.
     * 
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Test
    public void testSelect() throws TransformerException, SAXException, IOException, ParserConfigurationException {
	final Select select = new Select();
	final String[][] receved = select.select(new File("test.xml"), new String[] { "city" }, new int[] { 0, 1, 2 });
	final String[][] expected = { { "Alex" }, { "Alex" }, { "Behera" } };

	for (int i = 0; i < expected.length; i++) {
	    for (int j = 0; j < expected[i].length; j++) {
		assertEquals(expected[i][j], receved[i][j]);
	    }
	}
    }

}
