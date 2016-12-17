package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import bridge.Bridge;
import bridge.Director;
import validateSyntax.OrganizeInput;
import validateSyntax.Parser;

public class FileCommand {
  public static void main(final String[] args)
      throws IOException, TransformerException, SAXException,
      ParserConfigurationException, SQLException {
    FileCommand.executeCommands(new File("SQL.txt"));
  }

  public static void executeCommands(final File file)
      throws IOException, TransformerException, SAXException,
      ParserConfigurationException, SQLException {

    BufferedReader br = null;
    br = new BufferedReader(new FileReader(file));
    final ArrayList<String> arr = new ArrayList<>();
    String str = null;
    while ((str = br.readLine()) != null) {
      arr.add(str);
    }
    final Parser s = new Parser();
    for (int i = 0; i < arr.size(); i++) {

      final String after = OrganizeInput.organize(arr.get(i));
      final String[] arr1 = after.split(" ");
      if (s.validate(arr1)) {
        final Director director = new Director();
        director.direct(arr1[0].toLowerCase());
        final Bridge bridge = new Bridge();
        bridge.dirct(director, arr1, "xmldb");
      }
    }
  }

}
