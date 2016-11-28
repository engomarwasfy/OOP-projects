package validateSyntax;

public class dropValidator extends Validator {

    @Override
    public boolean validate(String[] sql) {
	if ((sql.length == 4 && sql[1].equalsIgnoreCase("database") || sql[1].equalsIgnoreCase("table"))
		&& isValidName(sql[2]) && sql[3].equals(";")) {
	    return true;
	}
	return false;
    }

}
