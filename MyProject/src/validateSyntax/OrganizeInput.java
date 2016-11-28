package validateSyntax;

public class OrganizeInput {
	

	public static String organize(String before) {
		
		String after = before.trim().replaceAll(" +"," ");
		after = after.trim().replaceAll(" ,", ",");
		after = after.replaceAll(", ", ",");
		after = after.replaceAll("\\(", " \\( ");
		after = after.replaceAll("\\)", " \\) ");
		after =after.replaceAll(" =", "=");
		after =after.replaceAll("= ", "=");
		after =after.replaceAll(" <", "<");
		after =after.replaceAll("< ", "<");
		after =after.replaceAll(" >", ">");
		after =after.replaceAll("> ", ">");
		after =after.replaceAll(";", " ;");
		after = after.replaceAll(" +"," ");
		if(after.contains("where")){
		String str = after.substring(after.lastIndexOf("where"), after.length());
		str = str.substring(5, str.length());
		str=str.replaceAll(" +","");
		System.out.println(str);
		after = after.substring(0, after.indexOf("where")).concat("where " + str);
		System.out.println(after);
		}
		return after;

	}

}
