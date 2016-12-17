package jdbcTesting;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import JDBC.JStatement;


public class JStatmentTest {

  private final JStatement statement = new JStatement();


  private void createUseDatabase(final String databaseName)
      throws SQLException {
    statement.execute("CREATE DATABASE " + databaseName); // you should now
    statement.execute("USE " + databaseName); // Set the state of your
    statement.close();
  }

  @Test //
  public void test_1_Excute() throws SQLException {
   createUseDatabase("DB");
    try {
      statement.execute(
          "CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 date)");
      statement.close();
    } catch (final Throwable e) {
      Assert.fail("something went wrong");
    }
    try {
      statement.execute(
          "CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 date)");
      Assert.fail("Created existing table successfully!");
    } catch (final SQLException e) {

    } catch (final Throwable e) {
      Assert.fail("something went wrong");
    }

    try {
      statement.execute("CREATE TABLE incomplete_table_name1");
      Assert.fail("Create invalid table succeed");
    } catch (final SQLException e) {
    } catch (final Throwable e) {
      Assert.fail("something went wrong");
    }
  }

  @Test //
  public void test_2_ExcuteUpdate() throws SQLException {
    createUseDatabase("DB");
    try {
      statement.execute(
          "CREATE TABLE table_name3(column_name1 varchar, column_name2 int, column_name3 float)");
      final int count = statement
          .executeUpdate("INSERT INTO table_name3 VALUES ('value1', 3, 1.3)");
      Assert.assertEquals("Insert returned a number != 1", 1, count);
      statement.close();
    } catch (final Throwable e) {
      Assert.fail("something went wrong");
    }
  }


  @Test //
  public void test_3_AddBatch() throws SQLException {
    createUseDatabase("DB");
    try {
      statement.execute(
          "CREATE TABLE students(name varchar, id int)");
      statement.addBatch(
          "INSERT INTO students(name, id) VALUES ('shaban', 1)");
      statement.addBatch(
              "INSERT INTO students(name, id) VALUES ('omar', 2)");
      statement.addBatch(
              "INSERT INTO students(name, id) VALUES ('mostafa', 3)");
      statement.close();
      Assert.fail("something went wrong");

    } catch (final SQLException e) {
    } catch (final Throwable e) {
    }
  }

  @Test //
  public void test_4_ExcuteBatch() throws SQLException {
    createUseDatabase("DB");
    try {
      statement.execute(
          "CREATE TABLE students(name varchar, id int)");
      statement.addBatch(
          "INSERT INTO students(name, id) VALUES ('shaban', 1)");
      statement.addBatch(
              "INSERT INTO students(name, id) VALUES ('omar', 2)");
      statement.addBatch(
              "INSERT INTO students(name, id) VALUES ('mostafa', 3)");
      statement.addBatch("UPDATE students SET name='shaban', id=9");
      final int[] result = statement.executeBatch();
      Assert.assertEquals(3, result.length);
      statement.close();
      Assert.fail("something went wrong");

    } catch (final SQLException e) {
    } catch (final Throwable e) {
    }
  }

  @Test //
  public void test_5_ClearBatch() throws SQLException {
    createUseDatabase("DB");
    try {
      statement.execute(
          "CREATE TABLE students(name varchar, id int)");
      statement.addBatch(
          "INSERT INTO students(name, id) VALUES ('shaban', 1)");
      statement.addBatch(
              "INSERT INTO students(name, id) VALUES ('omar', 2)");
      statement.addBatch(
              "INSERT INTO students(name, id) VALUES ('mostafa', 3)");
      statement.addBatch("UPDATE students SET name='shaban', id=9");
      statement.clearBatch();
      final int[] result = statement.executeBatch();
      Assert.assertEquals(0, result.length);
      statement.close();
      Assert.fail("something went wrong");
    } catch (final SQLException e) {
    } catch (final Throwable e) {
    }
  }

}