package Controller;

/*Import house keeping stuff that we need to connect with the Database*/
import java.sql.*;
/*Import Scanner Class so as to take input from the user*/
import java.util.Scanner;

import JDBC.JConnection;
import JDBC.JStatement;

public class Main {

	static Connection connection = new JConnection();
	static Scanner scan;
	static Statement statement = new JStatement();
	static ResultSet resultSet ;
	static ResultSetMetaData resultSetMetaData;

	public static void main(String args[]) throws SQLException {

		/*Create object 'scan' to take input from the keyboard*/
		scan = new Scanner(System.in);

		try {
			
			/*
			 * Call the function 'getConnection' from the static class 
			 *'GetConnection' to return the connection to the database   
			 */
			connection = GetConnection.getConnection(); // function at the bottom of file.
			
			System.out.println("Connection established");
			
			/*Create statement object to execute queries*/
			statement = connection.createStatement();

		} catch (Exception e) {

			System.out.println("Check your connection again.");

		}

		while (true) {

			/*transfer control to the function 'executeQuery'*/
			executeQuery();

		}

	}

	public static void executeQuery() {

		System.out.print("$> ");

		try {
			
			/*get query input from the user*/
			String query = scan.nextLine();
			
			/*split the query into array of string object using the space delimiter*/
			String[] queryBifercation = query.split(" ");

			/*if the first word of the query is 'SELECT' or 'SHOW'*/
			if (queryBifercation[0].equalsIgnoreCase("SELECT") || queryBifercation[0].equalsIgnoreCase("SHOW")) {

				/*execute the query and store the result set returned in resultSet*/
				resultSet = statement.executeQuery(query);
				System.out.println("query success");
				
				/*call the 'printResultSet' function*/
				printResultSet();

			} else {
				
				/*
				 * if first word is not 'SELECT' or 'SHOW', call execute 
				 * query with the execute update command
				 */
				statement.executeUpdate(query);
				System.out.println("query success");

			}

		} catch (Exception e) {

			System.out.println("Statement Cannot be executed.");

		}

	}

	public static void printResultSet() throws SQLException {
		
		/*get the meta data from the result set*/
		resultSetMetaData = resultSet.getMetaData();
		
		/*
		 * 	resultSetMetaData 	-> contains column name data
		 * 	resultSet 			-> contains column data
		 *  getColumnCount() 	-> gets the number of columns in the Result Set
		 *  getColumnName(i) 	-> gets the name of the i'th column
		 */
		for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
			//if i is greater than one
			if (i > 1)	// first column must not have a tab before it
				System.out.print("\t");
			String columnName = resultSetMetaData.getColumnName(i);
			System.out.print(columnName);
		}
		System.out.println();
		while (resultSet.next()) {
			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
				if (i > 1)
					System.out.print("\t");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
	}
	
}

class GetConnection {

	static Connection conn;
	static String USERNAME;
	static String PASSWORD;
	static String CONNECTION;

	public static Connection getConnection() {
		
		USERNAME = "dbuser"; // enter username here
		PASSWORD = "dbpassword"; // enter password here 
		CONNECTION = "jdbc:mysql://localhost/"; // link

		try {
			
			/*Coerce the driver on the java application*/
			Class.forName("JDBC.JDriver");
			
			/*Get the connection from the DriverManager Class and call the function 
			 * 'getConnection'*/
			conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			
			/*return the found connection to the function which called it*/
			return conn;

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
