package validateSyntax;

import java.util.regex.Pattern;

public abstract class Validator {
    abstract public boolean validate(String[] sql);

    public boolean isValidName(final String name) {

	final String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
	return Pattern.matches(regex, name);
    }

    public boolean isValidCommaSeperated(final String name) {
	final String arr[] = name.split(",");
	for (int i = 0; i < arr.length; i++) {
	    if (!isValidName(arr[i]) && !isNumber(arr[i])) {

		return false;
	    }
	}
	return true;

    }

    public boolean isValidCondition(final String word) {
	if (word.contains("AND") || word.contains("OR") || word.contains("Not")) {
	    return true;
	}
	if (word.equals("'true'")) {
	    return true;
	}
	final StringBuilder leftOperand = new StringBuilder();
	String rightOperand = "";
	for (int i = 0; i < word.length(); i++) {
	    if (isOperator(word.charAt(i) + "")) {
		if (i + 1 == word.length()) {
		    return false;
		}
		rightOperand = word.substring(i + 1, word.length());
		if (rightOperand.charAt(0) == '\'' && rightOperand.charAt(rightOperand.length() - 1) == '\'') {
		    rightOperand = rightOperand.substring(1, rightOperand.length() - 1);
		}
		break;
	    }
	    leftOperand.append(word.charAt(i));

	}
	return (isValidName(leftOperand.toString()) || isNumber(leftOperand.toString()))
		&& (isValidName(rightOperand) || isNumber(rightOperand));

    }

    public boolean isOperator(final String op) {
	if (op.equals("=") || op.equals(">") || op.equals("<")) {
	    return true;
	}
	return false;
    }

    public boolean isNumber(final String word) {
	return Pattern.matches("^[0-9]*$", word);

    }

    public boolean isValidCommaSeperatedEquals(final String name) {
	final String arr[] = name.split(",");
	for (int i = 0; i < arr.length; i++) {
	    if (!isValidCondition(arr[i]) || !arr[i].contains("=")) {
		return false;
	    }
	}
	return true;

    }

}
