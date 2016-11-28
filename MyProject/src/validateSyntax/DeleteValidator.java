package validateSyntax;

public class DeleteValidator extends Validator {

    @Override
    public boolean validate(String[] sql) {
	if (sql.length == 6 && sql[1].equalsIgnoreCase("from") && isValidName(sql[2])
		&& sql[3].equalsIgnoreCase("where") && isValidCondition(sql[4]) && sql[5].equals(";")) {
	    return true;
	}
	return false;

    }
}
