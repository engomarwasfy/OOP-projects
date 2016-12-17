package eval;
import java.util.Stack;

public class ExpressionEvaluator {
  private final Stack operation = new Stack();
  private String postfix = "";
  private char get;
  private int count = 0;
  public String infixToPostfix(final String expression) {
    if (expression.length() <= 1) {
      throw new RuntimeException("invalid");
    }
    char[] express = expression.toCharArray();
    if (checkoperator(express[0])) {
      throw new RuntimeException("invalid");
    }
    if (checkoperator(express[express.length - 1])) {
      throw new RuntimeException("invalid");
    }
    if (expression.contains(" ")) {
      for (count = 0; count < express.length; count++) {
        express = expression.toCharArray();
        if (checkoperator(express[count]) && checkoperator(express[count + 2])
            && count < express.length - 2) {
          throw new RuntimeException("invalid");
        }
        if (operation.size() >= 2) {
          final char test = (char) operation.pop();
          if (checkPrecedence(test) && test != '(') {
            if (postfix.charAt(postfix.length() - 1) == ' ') {
              postfix += (char) operation.pop();
            } else {
              postfix += " " + (char) operation.pop();
            }
          }
          operation.push(test);
        }
        get = express[count];
        if (get == '(') {
          operation.push(get);
        } else if (get == ')') {
          while ((char) operation.peek() != '(') {
            if (operation.isEmpty()) {
              throw new RuntimeException("invalid");
            }
            if (postfix.charAt(postfix.length() - 1) == ' ') {
              postfix += (char) operation.pop();
            } else {
              postfix += " " + (char) operation.pop();
            }
          }
          operation.pop();
        } else if (get == '+' || get == '-' || get == '*' || get == '/') {
          if (operation.isEmpty()) {
            operation.push(get);
          } else if (!operation.isEmpty()) {
            if (checkPrecedence(get)) {
              if (postfix.charAt(postfix.length() - 1) == ' ') {
                postfix += (char) operation.pop();
              } else {
                postfix += " " + (char) operation.pop();
              }
              operation.push(get);
            } else {
              operation.push(get);
            }
          }
        } else {
          if (get == ' ' && postfix.length() > 1) {
            if (postfix.charAt(postfix.length() - 1) != ' ') {
              postfix += get;
            }
          } else {
            postfix += get;
          }
        }
      }
    } else {
      for (count = 0; count < express.length; count++) {
        if (checkoperator(express[count]) && checkoperator(express[count + 1])
            && count < express.length - 1) {
          throw new RuntimeException("invalid");
        }
        if (operation.size() >= 2) {
          final char test = (char) operation.pop();
          if (checkPrecedence(test) && test != '(') {
            if (postfix.charAt(postfix.length() - 1) != ' ') {
              postfix += " " + (char) operation.pop() + " ";
            } else {
              postfix += (char) operation.pop() + " ";
            }
          }
          operation.push(test);
        }
        get = express[count];
        if (get == '(') {
          operation.push(get);
        } else if (get == ')') {
          while ((char) operation.peek() != '(') {
            if (operation.isEmpty()) {
              throw new RuntimeException("invalid");
            }
            if (postfix.charAt(postfix.length() - 1) != ' ') {
              postfix += " " + (char) operation.pop() + " ";
            } else {
              postfix += (char) operation.pop() + " ";
            }
          }
          operation.pop();
        } else if (get == '+' || get == '-' || get == '*' || get == '/') {
          if (postfix.charAt(postfix.length() - 1) != ' ') {
            postfix += " ";
          }
          if (operation.isEmpty()) {
            operation.push(get);
          } else if (!operation.isEmpty()) {
            if (checkPrecedence(get)) {
              if (checkPrecedence(get)) {
                if (postfix.charAt(postfix.length() - 1) != ' ') {
                  postfix += " " + (char) operation.pop() + " ";
                } else {
                  postfix += (char) operation.pop() + " ";
                }
                operation.push(get);
              } else {
                operation.push(get);
              }
            } else {
              operation.push(get);
            }
          }
        } else {
          if (get == ' ' && postfix.length() > 1) {
            if (postfix.charAt(postfix.length() - 1) != ' ') {
              postfix += get;
            }
          } else {
            postfix += get;
          }
        }
      }
    }
    if (operation.size() > 2) {
      throw new RuntimeException("invalid");
    }
    while (operation.size() != 0) {
      if ((char) operation.peek() == '(') {
        throw new RuntimeException("invalid");
      }
      postfix += " " + (char) operation.pop();
    }

    return postfix;
  }

  public boolean checkPrecedence(final char operator) {
    if (operator == '+' || operator == '*') {
      if ((char) operation.peek() == '+' || (char) operation.peek() == '*') {
        return true;
      }
      if ((char) operation.peek() == '-') {
        return true;
      }
    }
    if (operator == '-') {
      if ((char) operation.peek() == '+' || (char) operation.peek() == '*') {
        return false;
      }
      if ((char) operation.peek() == '-') {
        return true;
      }
    }
    return false;
  }

  /**
   * @param operator mm.
   * @return gg.
   */
  public boolean checkoperator(final char operator) {
    if (operator == ' ') {
      return false;
    }
    if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
      return true;
    }
    return false;
  }

  /**
   * @param operator mm.
   * @return gg.
   */
  public boolean checkoperator2(final String operator) {
    if ("+".equals(operator) || "*".equals(operator) || "-".equals(operator)
        || "/".equals(operator)) {
      return true;
    }
    return false;
  }

  /**
   * @param operator mm.
   * @return bbb.
   */
  public boolean checkoperator3(final Object operator) {
    if ("+".equals(operator) || "*".equals(operator) || "-".equals(operator)
        || "/".equals(operator)) {
      return true;
    }
    return false;
  }
}
