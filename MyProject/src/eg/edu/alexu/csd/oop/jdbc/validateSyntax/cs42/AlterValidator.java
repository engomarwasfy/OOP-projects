package eg.edu.alexu.csd.oop.jdbc.validateSyntax.cs42;

public class AlterValidator extends Validator {

    @Override
    public boolean validate(String[] sql) {
	if(sql.length ==7) {
	    if(sql[1].equalsIgnoreCase("table")) {
		if(isValidName(sql[2])) {
		    if(sql[3].equalsIgnoreCase("add")) {
			if(isValidName(sql[4])) {
			    if(isvalidDataType(sql[5])) {
				if(sql[6].equals(";")) {
				    return true;
				}
			    }
			}
		    } else if(sql[3].equalsIgnoreCase("drop")) {
			if(sql[4].equalsIgnoreCase("COLUMN")) {
			    if(isValidName(sql[5])) {
				if(sql[6].equals(";")) {
				    return true;
				}
			    }
			}
		    }
		}
	    }
	}
	return false;
    }

}
