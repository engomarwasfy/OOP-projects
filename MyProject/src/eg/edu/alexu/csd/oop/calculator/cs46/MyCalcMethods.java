package eg.edu.alexu.csd.oop.calculator.cs46;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import eg.edu.alexu.csd.oop.calculator.Calculator;
/**
*
*/
public class MyCalcMethods implements Calculator {
/**
*
*/
    private File fout;
    /**
    *
    */
    private String s = null;
    /**
    *
    */
    private String result;
    /**
    *
    */
    private String server;
    /**
    *
    */
    private LinkedList<String> ls = new LinkedList<String>();
    /**
    *
    */
    private int count = -1;
    /**
    *
    */
    private int pointer = -1;
    /**
    *
    */
    private Integer countint;
    /**
    *
    */
    private Integer pointerint;

    @Override
	public final void input(final String s1) {
	this.s = s1;
	if (getResult() != null || s == "null") {
	    if (count == s("4")) {
		ls.removeFirst();
		ls.addLast(s);
		pointer = count;
	    } else {
		ls.addLast(s);
		count++;
		pointer = count;
	    }
	}

    }

    @Override
	public final String getResult() {

	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine engine = mgr.getEngineByName("JavaScript");
	try {
		current();
	    result = (engine.eval(s + "+0.0")).toString();
	} catch (Exception e) {
	    throw new RuntimeException();
	}

	return result;
    }

    @Override
	public final String current() {
	// TODO Auto-generated method stub
	// will return null as initialized before if no input
	if (pointer == -1) {
	    return null;
	}
	s = ls.get(pointer);
	return ls.get(pointer);
    }

    @Override
	public final String prev() {
	// TODO Auto-generated method stub
	if (pointer == 0 || pointer == -1) {
	    return null;
	}
	pointer--;
	try {
	    server = ls.get(pointer);
	} catch (Exception e) {

	    return null;
	}
	return server;
    }

    @Override
	public final String next() {
	// TODO Auto-generated method stub
	if (pointer == ls.size() - 1 || pointer == -1) {
	    return null;
	}
	pointer++;
	try {
	    server = ls.get(pointer);
	} catch (Exception e) {

	    return null;
	}
	return server;
    }

    @Override
	public final void save() {
	// TODO Auto-generated method stub
	try {
	    countint = new Integer(count);
	    pointerint = new Integer(pointer);
	    writeFile(ls);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Override
	public final void load() {
	// TODO Auto-generated method stub
	try {
	    fout = new File("out.txt");
	    ls = readFile(fout);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
    /**
    *@throws IOException s
    *@param s1 ls
    */
    public final void writeFile(final LinkedList<String> s1)throws IOException {

	fout = new File("out.txt");
	FileOutputStream fos = new FileOutputStream(fout, false);
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	bw.append(countint.toString());
	bw.newLine();
	bw.append(pointerint.toString());
	bw.newLine();

	for (int i = 0; i < s1.size(); i++) {
	    bw.append(s1.get(i));
	    bw.newLine();
	}
	bw.close();
    }
    /**
    *@param in f
    *@return s
    *@throws IOException s
    */
    public final LinkedList<String> readFile(final File in) throws IOException {
	LinkedList<String> ls1 = new LinkedList<String>();
	FileInputStream fis = new FileInputStream(in);

	// Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	count = Integer.parseInt(br.readLine());
	pointer = Integer.parseInt(br.readLine());

	String line = null;
	while ((line = br.readLine()) != null) {
	    ls1.addLast(line);
	}

	br.close();
	return ls1;
    }
    /**
	 * @return s int
	 * @param x
	 *            string
	 */
    public final int s(final String x) {
		return Integer.parseInt(x);
	}



}
