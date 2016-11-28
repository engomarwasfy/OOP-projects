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
		return after;

	}

}
