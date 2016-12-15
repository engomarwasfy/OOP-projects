package validateSyntax;

public class OrganizeInput {

    public static String organize(String before) {
  if(!before.contains(";")){
      before = before +";";
  }
  String after = before.trim().replaceAll(" +", " ");
  after = after.trim().replaceAll(" ,", ",");
  after = after.replaceAll(", ", ",");
  after = after.replaceAll("\\(", " \\( ");
  after = after.replaceAll("\\)", " \\) ");
  after = after.replaceAll(" =", "=");
  after = after.replaceAll("= ", "=");
  after = after.replaceAll(" <", "<");
  after = after.replaceAll("< ", "<");
  after = after.replaceAll(" >", ">");
  after = after.replaceAll("> ", ">");
  after = after.replaceAll(";", " ;");
  after = after.toLowerCase();

  after = after.replaceAll(" +", " ");

  if (after.contains("where")) {
      String str = after.substring(after.lastIndexOf("where"), after.length());
      str = str.substring(5, str.length());
      str = str.replaceAll(" +", "");
      after = after.substring(0, after.indexOf("where")).concat("where " + str);
      after = after.replaceAll(";", " ;");
//      System.out.println(after);
  }

   if (after.contains("WHERE")) {
     String str = after.substring(after.lastIndexOf("WHERE"), after.length());
     str = str.substring(5, str.length());
     str = str.replaceAll(" +", "");
     after = after.substring(0, after.indexOf("WHERE")).concat("WHERE " + str);
     after = after.replaceAll(";", " ;");
//     System.out.println(after);
 }
  return after;

    }

}
