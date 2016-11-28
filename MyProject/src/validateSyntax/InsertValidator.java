package validateSyntax;

public class InsertValidator extends Validator {
    @Override
    public boolean validate(final String[] sql) {
	if (sql.length == 11 && sql[1].equalsIgnoreCase("into") && isValidName(sql[2]) && sql[3].equals("(")
		&& isValidCommaSeperated(sql[4]) && sql[5].equals(")") && sql[6].equalsIgnoreCase("values")
		&& sql[7].equals("(") && isValidCommaSeperated(sql[8]) && sql[9].equals(")") && sql[10].equals(";")) {
	    return true;
	}
	if (sql.length == 8 && sql[1].equalsIgnoreCase("into") && isValidName(sql[2])
		&& sql[3].equalsIgnoreCase("values") && sql[4].equals("(") && isValidCommaSeperated(sql[5])
		&& sql[6].equals(")") && sql[7].equals(";")) {
	    return true;
	}
	return false;
    }

}
