package eg.edu.alexu.csd.oop.calculator;
public interface Calculator {
/* Take input mathematical expression from user */
public void input(String s);
/* Return the result of the current operations or throws a runtime exception */
public String getResult();
/* Return the current formula */
public String current ();
/* Return the last operation in String format, or Null if no more history available */
public String prev();
/* Return the next operation in String format, or Null if no more history available */
public String next();
/* Save the last 5 done Operations in a fle*/
public void save();
/* Load from fle the saved 5 operations */
public void load();
}