package jdbcTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import JDBC.JResultset;

public class JResultSetTest {

  @Test
  public void testPosition() throws SQLException {
    final String arr[][] = new String[3][5];
    arr[0][0] = "Gamal";
    arr[0][1] = "Ghanem";
    arr[0][2] = "23";
    arr[0][3] = "82";
    arr[0][4] = "1996-1-3";
    arr[1][0] = "shaban";
    arr[1][1] = "sheta";
    arr[1][2] = "40";
    arr[1][3] = "84";
    arr[1][4] = "1995-5-8";
    arr[2][0] = "Omar";
    arr[2][1] = "Wasfy";
    arr[2][2] = "50";
    arr[2][3] = "88";
    arr[2][4] = "1996-1-22";
    final ArrayList<String> colNames = new ArrayList<>();
    colNames.add("FirstName");
    colNames.add("LastName");
    colNames.add("id");
    colNames.add("grade");
    colNames.add("birth");
    final ArrayList<String> colTypes = new ArrayList<>();
    colTypes.add("varchar");
    colTypes.add("varchar");
    colTypes.add("int");
    colTypes.add("int");
    colTypes.add("date");
    final JResultset test = new JResultset(arr, colNames, "table_name",
        colTypes);
    assertTrue(test.isBeforeFirst());
    test.afterLast();
    assertTrue(test.isAfterLast());
    test.absolute(-1);
    assertFalse(test.absolute(5));
    assertTrue(test.isAfterLast());
  }

  @Test
  public void testGetString() throws SQLException {
    final String arr[][] = new String[3][5];
    arr[0][0] = "Gamal";
    arr[0][1] = "Ghanem";
    arr[0][2] = "23";
    arr[0][3] = "82";
    arr[0][4] = "1996-1-3";
    arr[1][0] = "Shaban";
    arr[1][1] = "Sheta";
    arr[1][2] = "40";
    arr[1][3] = "84";
    arr[1][4] = "1995-5-8";
    arr[2][0] = "Omar";
    arr[2][1] = "Wasfy";
    arr[2][2] = "50";
    arr[2][3] = "88";
    arr[2][4] = "1996-1-22";
    ///////////////////////////////////////////////
    final ArrayList<String> colNames = new ArrayList<>();
    colNames.add("FirstName");
    colNames.add("LastName");
    colNames.add("id");
    colNames.add("grade");
    colNames.add("birth");
    ///////////////////////////////////////////////
    final ArrayList<String> colTypes = new ArrayList<>();
    colTypes.add("varchar");
    colTypes.add("varchar");
    colTypes.add("int");
    colTypes.add("int");
    colTypes.add("date");
    ///////////////////////////////////////////////
    final JResultset test = new JResultset(arr, colNames, "table_name",
        colTypes);
    test.next();
    final String name = test.getString(1);
    assertEquals(name, "Gamal");
    final String lName = test.getString("LastName");
    assertEquals("Ghanem", lName);
    test.last();
    final String name2 = test.getString("FirstName");
    assertEquals("Omar", name2);
    final String lName2 = test.getString(2);
    assertEquals("Wasfy", lName2);
    test.absolute(2);
    assertEquals("Sheta", test.getString(2));

  }

  @Test
  public void testGetInt() throws SQLException {
    final String arr[][] = new String[3][5];
    arr[0][0] = "Gamal";
    arr[0][1] = "Ghanem";
    arr[0][2] = "23";
    arr[0][3] = "82";
    arr[0][4] = "1996-1-3";
    arr[1][0] = "Shaban";
    arr[1][1] = "Sheta";
    arr[1][2] = "40";
    arr[1][3] = "84";
    arr[1][4] = "1995-5-8";
    arr[2][0] = "Omar";
    arr[2][1] = "Wasfy";
    arr[2][2] = "50";
    arr[2][3] = "88";
    arr[2][4] = "1996-1-22";
    final ArrayList<String> colNames = new ArrayList<>();
    colNames.add("FirstName");
    colNames.add("LastName");
    colNames.add("id");
    colNames.add("grade");
    colNames.add("birth");
    final ArrayList<String> colTypes = new ArrayList<>();
    colTypes.add("varchar");
    colTypes.add("varchar");
    colTypes.add("int");
    colTypes.add("int");
    colTypes.add("date");
    final JResultset test = new JResultset(arr, colNames, "table_name",
        colTypes);
    test.next();
    final int id = test.getInt(3);
    assertEquals(23, id);
    test.next();
    final int id1 = test.getInt("id");
    assertEquals(40, id1);
    test.absolute(3);
    final int grade = test.getInt("grade");
    assertEquals(88, grade);
  }

}
