package validateSyntax;

public class UpdateValidator extends Validator {

  @Override
  public boolean validate(final String[] sql) {

    if (sql.length == 7 && isValidName(sql[1]) && sql[2].equalsIgnoreCase("set")
        && isValidCommaSeperatedEquals(sql[3])
        && sql[4].equalsIgnoreCase("where") && isValidCondition(sql[5])
        && sql[6].equals(";")) {
      return true;
    }
    if (sql.length == 5 && isValidName(sql[1]) && sql[2].equalsIgnoreCase("set")
        && isValidCommaSeperatedEquals(sql[3]) && sql[4].equals(";")) {
      return true;
    }
    return false;
  }

}
