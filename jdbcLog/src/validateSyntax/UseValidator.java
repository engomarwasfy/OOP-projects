package validateSyntax;

import extractInformation.UsedDataBase;

public class UseValidator extends Validator {

  @Override
  public boolean validate(final String[] sql) {
    if (sql.length == 3 && isValidName(sql[1]) && sql[2].equals(";")) {
      UsedDataBase.setUsedDataBase(sql[1]);
      return true;
    }
    return false;
  }

}
