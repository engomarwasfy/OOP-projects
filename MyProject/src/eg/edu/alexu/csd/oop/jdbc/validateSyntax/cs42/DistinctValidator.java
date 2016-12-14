package eg.edu.alexu.csd.oop.jdbc.validateSyntax.cs42;

public class DistinctValidator extends Validator {

    @Override
    public boolean validate(String[] sql) {
	if(isValidCommaSeperated(sql[2])) {
	    if(sql[3].equalsIgnoreCase("from")) {
		if(isValidName(sql[4])) {
		    if(sql[5].equals(";")) {
			return true;
		    }
		}
	    }
	}
	return false;
    }

}
