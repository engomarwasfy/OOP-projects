package validateSyntax;

public class DistinctValidator extends Validator {

  @Override
  public boolean validate(final String[] sql) {
    if (sql.length == 8 && sql[1].equalsIgnoreCase("DISTINCT")
        && isValidCommaSeperated(sql[2]) && sql[3].equalsIgnoreCase("from")
        && isValidName(sql[4]) && sql[5].equalsIgnoreCase("where")
        && isValidCondition(sql[6]) && sql[7].equals(";")) {
      return true;

    }
    if (sql.length == 6 && sql[1].equalsIgnoreCase("DISTINCT")
        && isValidCommaSeperated(sql[2]) && sql[3].equalsIgnoreCase("from")
        && isValidName(sql[4]) && sql[5].equals(";")) {
      return true;

    }
    return false;
  }

}
