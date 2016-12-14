package Controller;

import java.util.Scanner;

import eg.edu.alexu.csd.oop.jdbc.bridge.cs42.Bridge;
import eg.edu.alexu.csd.oop.jdbc.bridge.cs42.Director;
import eg.edu.alexu.csd.oop.jdbc.validateSyntax.cs42.OrganizeInput;
import eg.edu.alexu.csd.oop.jdbc.validateSyntax.cs42.Parser;

public class Main {
    public static void main(final String[] args) {

	final Parser s = new Parser();
	final Scanner sc = new Scanner(System.in);
	while (true) {
	    try {
		System.out.print("SQL>>");
		final String before = sc.nextLine();
		if (before.trim().length() != 0) {
		    final String after = OrganizeInput.organize(before);
		    final String[] arr = after.split(" ");
		    if (s.validate(arr)) {
			final Director director = new Director();
			director.direct(arr[0].toLowerCase());
			final Bridge bridge = new Bridge();
			bridge.dirct(director, arr,"xmldb");
		    }
		    if (s.validate(arr)) {
			System.out.println("Syntax is valid");
		    }
		}
	    } catch (final Exception e) {
		System.out.println("sql command failed");
	    }
	}

    }

}
