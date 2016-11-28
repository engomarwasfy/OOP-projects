package validateSyntax;

import extractInformation.UsedDataBase;

public class UseValidator extends Validator {

    @Override
    public boolean validate(String[] sql) {
	if ((sql.length == 4) && isValidName(sql[1]) && isValidName(sql[2]) && sql[3].equals(";")) {
	    UsedDataBase.setUsedDataBase(sql[2]);
	    return true;
	}
	return false;
    }

}
