package Controller;

import java.util.Scanner;

import bridge.Bridge;
import bridge.Director;
import validateSyntax.OrganizeInput;
import validateSyntax.Parser;

public class Main {
  public static void main(final String[] args) {

    final Parser s = new Parser();
    final Scanner sc = new Scanner(System.in);
    while (true) {
      try {
        System.out.print("SQL>>");
        final String before = sc.nextLine();
        final String after = OrganizeInput.organize(before);
        final String[] arr = after.split(" ");
        if (s.validate(arr)) {
          final Director director = new Director();
          director.direct(arr[0].toLowerCase());
          final Bridge bridge = new Bridge();
          bridge.dirct(director, arr);
        }
        if (s.validate(arr)) {
          System.out.println("Done");
        }
        else{
        	System.out.print("sql command failed");
        }
      } catch (final Exception e) {
       System.out.println("sql command failed");
      }
    }

  }

}
