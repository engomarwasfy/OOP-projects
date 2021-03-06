package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

import bridge.Bridge;
import bridge.Director;
import validateSyntax.OrganizeInput;
import validateSyntax.Parser;

public class FileCommand {
	public static void main(String[] args) throws IOException {
		FileCommand.executeCommands(new File("SQL.txt"));
	}
public static void executeCommands(File file) throws IOException{

	BufferedReader br = null;
	br = new BufferedReader(new FileReader(file));
	ArrayList<String> arr = new ArrayList<>();
	String str=null;
	while((str = br.readLine()) != null ){
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
       bridge.dirct(director, arr1);
     }
	}
}
	
}
