package validateSyntax;

public class SelectValidator extends Validator {

  @Override
  public boolean validate(final String[] sql) {
    if (sql.length == 7 && (sql[1].equals("*") || isValidCommaSeperated(sql[1]))
        && sql[2].equalsIgnoreCase("from") && isValidName(sql[3])
        && sql[4].equalsIgnoreCase("where") && isValidCondition(sql[5])
        && sql[6].equals(";")) {
      return true;

    }
    if (sql.length == 5 && (sql[1].equals("*") || isValidCommaSeperated(sql[1]))
        && sql[2].equalsIgnoreCase("from") && isValidName(sql[3])
        && sql[4].equals(";")) {
      return true;

    }
    return false;

  }

}
