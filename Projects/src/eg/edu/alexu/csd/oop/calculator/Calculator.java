package eg.edu.alexu.csd.oop.calculator;

/**
*
*/
public interface Calculator {
    /* Take input mathematical expression from user */
    /**
     * @param s
     *            s
     */
    void input(String s);

    /*
     * Return the result of the current operations or throws a runtime exception
     */
    /**
     * @return s
     */
    String getResult();

    /* Return the current formula */
    /**
     * @return s
     */
    String current();

    /**
     * @return s
     */
    String prev();

    /**
     * @return s
     */
    String next();

    /* Save the last 5 done Operations in a fle */
    /**
    *
    */
    void save();

    /* Load from fle the saved 5 operations */
    /**
    *
    */
    void load();
}