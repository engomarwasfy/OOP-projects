package eg.edu.alexu.csd.oop.jdbc.validateSyntax.cs42;

import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.UsedDataBase;

public class UseValidator extends Validator {

    @Override
    public boolean validate(String[] sql) {
	if ((sql.length == 3) && isValidName(sql[1])&& sql[2].equals(";")) {
	    UsedDataBase.setUsedDataBase(sql[1]);
	    return true;
	}
	return false;
    }

}
