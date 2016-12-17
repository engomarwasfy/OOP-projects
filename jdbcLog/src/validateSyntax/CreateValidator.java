package validateSyntax;

public class CreateValidator extends Validator {

  @Override
  public boolean validate(final String[] sql) {
    if (sql.length == 4 && sql[1].equalsIgnoreCase("database")
        && sql[3].equals(";")) {
      return true;
    }
    if (sql.length >= 8 && sql[1].equalsIgnoreCase("table")) {
      if (isValidName(sql[2])) {

        if (sql[3].equals("(")) {
          String str = "";
          for (int i = 4; i < sql.length - 1; i++) {
            if (!sql[i].equals(")") && !sql[i + 1].equals(";")) {
              str = str + " " + sql[i];
            }
          }
          str = str.trim();
          final String[] arr = str.split(",");
          for (int i = 0; i < arr.length; i++) {
            final String[] arr2 = arr[i].split(" ");
            if (isValidName(arr2[0]) && isvalidDataType(arr2[1])) {
            } else {
              return false;
            }
          }
          return true;
        }
      }
    }
    return false;

  }

}
