package jdbcTesting;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import JDBC.JDriver;
public class JDriverTest {

  @Test
  public void testAcceptURL() {
    final JDriver driver = new JDriver();
    try {
      final boolean test = driver.acceptsURL("jdbc:xmldb://localhost");
      final boolean test2 = driver.acceptsURL("jdbc:altdb://localhost");
      final boolean test3 = driver.acceptsURL("jdbc:altdb://local");
      assertEquals(true, test);
      assertEquals(true, test2);
      assertEquals(false, test3);
    } catch (final SQLException e) {
    }
  }
  @Test
  public void testConnect() {
    final JDriver driver = new JDriver();
    try {
      Connection con = null;
      con = driver.connect("jdbc:xmldb://localhost", new Properties());
      Assert.assertNotNull(con);
    } catch (final SQLException e) {
    }
  }
}













