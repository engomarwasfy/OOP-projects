package validateSyntax;

import java.util.regex.Pattern;

public abstract class Validator {
  abstract public boolean validate(String[] sql);

  public boolean isValidName(final String name) {
    if (name.charAt(0) == '\'' && name.charAt(name.length() - 1) == '\'') {
      return true;
    }
    final String regex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
    return Pattern.matches(regex, name);
  }

  public boolean isValidCommaSeperated(final String name) {
    final String arr[] = name.split(",");
    for (int i = 0; i < arr.length; i++) {
      if (!isValidName(arr[i]) && !isIntNumber(arr[i]) && !isFloatNumber(arr[i])
          && !isDate(arr[i])) {
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
        break;
      }
      leftOperand.append(word.charAt(i));

    }
    return isValidName(leftOperand.toString())
        && (isValidName(rightOperand) || isIntNumber(rightOperand)
            || isDate(rightOperand) || isFloatNumber(rightOperand));

  }

  public boolean isOperator(final String op) {
    if (op.equals("=") || op.equals(">") || op.equals("<")) {
      return true;
    }
    return false;
  }

  public boolean isIntNumber(String word) {
    if (word.charAt(0) == '-') {
      word = word.replaceAll("-", "");
    }
    return Pattern.matches("[+-]^[0-9]*$", word);

  }

  public boolean isFloatNumber(final String word) {
    return Pattern.matches("[+-]?([0-9]*[.])?[0-9]+", word);

  }

  public boolean isDate(final String word) {
    return Pattern.matches(
        "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", word);

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

  public boolean isvalidDataType(final String dataType) {
    if (dataType.equalsIgnoreCase("int")
        || dataType.equalsIgnoreCase("varchar")
        || dataType.equalsIgnoreCase("date")
            || dataType.equalsIgnoreCase("float")) {
      return true;
    }
    return false;

  }

}
