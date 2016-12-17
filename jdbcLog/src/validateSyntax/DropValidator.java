package validateSyntax;

public class DropValidator extends Validator {

  @Override
  public boolean validate(final String[] sql) {
    if ((sql.length == 4 && sql[1].equalsIgnoreCase("database")
        || sql[1].equalsIgnoreCase("table")) && isValidName(sql[2])
        && sql[3].equals(";")) {
      return true;
    }
    return false;
  }

}
