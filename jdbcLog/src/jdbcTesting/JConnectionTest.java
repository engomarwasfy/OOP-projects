package jdbcTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class JConnectionTest {

  @Test
  public void test() {
    final String url = "jdbc:mySubprotocol:myDataSource";
    try {
      final Connection con = DriverManager.getConnection(url, "myLogin", "myPassword");
      con.close();
      Assert.assertNull(con);
    } catch (final SQLException e) {
    }


  }

}
