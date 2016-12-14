package eval;
import java.util.regex.Pattern;

public class Postfix {
	
	
/******************************************************/
	public String[] getpostfix(String cond) {
		ExpressionEvaluator eval = new ExpressionEvaluator();
		String str1 = organize(cond);
		String str2 = numeric(str1);
		String out = eval.infixToPostfix(str2).trim();
		String[] str3 = returnLogic(out, str1.split(" "));
		return str3;
	}
/******************************************************/
	public static String organize (String before){
		String after = before.trim().replaceAll(" +"," ");
		after = after.replaceAll("\\(", " \\( ");
		after = after.replaceAll("\\)", " \\) ");
		after = after.replaceAll("AND", " AND ");
		after = after.replaceAll("OR", " OR ");
		after = after.replaceAll("NOT", " NOT ");
		after = after.replaceAll("and", " AND ");
		after = after.replaceAll("or", " OR ");
		after = after.replaceAll("not", " NOT ");
		after = after.replaceAll(" +"," ");
		return after.trim();
	}
	/*******************************************/
	public static String numeric(String expre) {
		String[] arr1 = expre.split(" ");
		String[] arr2 = new String[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			if(arr1[i].equals("(")) {
				arr2[i]=arr1[i];
			} else if(arr1[i].equals(")")) {
				arr2[i]=arr1[i];
			} else if(arr1[i].equalsIgnoreCase("AND")) {
				arr2[i]="*";
			} else if(arr1[i].equalsIgnoreCase("OR")) {
				arr2[i]="+";
			} else if(arr1[i].equalsIgnoreCase("NOT")) {
				arr2[i]="-";
			} else {
				String number = "" + i;
				arr2[i] = number ;
			}
		}
		String num = "";
		for (int i = 0; i < arr2.length; i++) {
			num += " " + arr2[i];
		}
		return num.trim();
	}
/************************************************/
	public static String[] returnLogic (String num , String[] logic) {
		String[] arr = num.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if(isNumber(arr[i])) {
				arr[i] = logic[Integer.parseInt(arr[i])];
			}
		}
		return arr;
	}
/********************************************/
	public static boolean isNumber(final String word){
		return Pattern.matches("^[0-9]*$", word);
	}
}
