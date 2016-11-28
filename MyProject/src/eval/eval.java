package eval;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import XmlMethods.XmlParsingMethods;

public class eval {
	Postfix post = new Postfix();
	
	public ArrayList<Integer> getindexes(File xml,String cond) {
		String[] arr = post.getpostfix(cond);
		ArrayList<Integer> out = evalPostfix(xml,arr);
		return out;
	}
	/*********************************************************/
	 public static ArrayList<Integer> evalPostfix(File xml,String[] exp) {
		 Set set = new Set();
		 XmlParsingMethods parse = new XmlParsingMethods();
	    Stack<String> list = new Stack<String> ();
	    String n1;     //result of 1st popping
	    String n2;     // result of 2nd popping
	    int[] out1 ;
	    int[] out2 ;
	    int[] out = new int[0] ;
	    ArrayList<Integer> arr1 = new ArrayList<>();
	    ArrayList<Integer> arr = new ArrayList<>();
	    ArrayList<Integer> arr2 = new ArrayList<>();
	    for (int i = 0; i < exp.length; i++) {
	        String ch = exp[i];
            if (!(ch.equals("*") || ch.equals("+") || ch.equals("-"))) {
                list.push(ch);
            } else {
                n1 = list.pop();
                n2 = list.pop();
                
                if(n2.equals("empty")) {
                	arr1 = parse.parseCondition(xml, n1);
                	out1 = new int[arr1.size()];
                	out1 = convert(arr1);
                	switch (ch) {
	                    case "+":
	                    	arr = set.union(out1, out);
	                    	out = new int[arr.size()];
	                    	out = convert(arr);
	                        list.push("empty");
	                        break;
	                    case "-":
	                    	if (out.length == 0) {
	                    		out = out1;
	                    	} else {
	                    		arr = set.not(out, out1);	
	                    		out = new int[arr.size()];
	                    		out = convert(arr);
	                    	}
	                        list.push("empty");
	                        break;
	                    case "*":
	                    	if (out.length == 0) {
	                    		out = out1;
	                    	} else {
	                    		arr = set.and(out1, out);	
	                    		out = new int[arr.size()];
	                    		out = convert(arr);
	                    	}
	                        list.push("empty");
	                        break;
	                    default:
	                        throw new RuntimeException();
                	}
                } else {
                	arr1 = parse.parseCondition(xml, n1);
                	out1 = new int[arr1.size()];
                	out1 = convert(arr1);
                	arr2 = parse.parseCondition(xml, n2);
                	out2 = new int[arr2.size()];
                	out2 = convert(arr2);
	                switch (ch) {
	                    case "+":
	                    	arr = set.union(out1, out2);
	                    	out = new int[arr.size()];
	                    	out = convert(arr);
	                        list.push("empty");
	                        break;
	                    case "-":
	                    	arr = set.not(out2, out1);
	                    	out = new int[arr.size()];
	                    	out = convert(arr);
	                        list.push("empty");
	                        break;
	                    case "*":
	                    	arr = set.and(out1, out2);
	                    	out = new int[arr.size()];
	                    	out = convert(arr);
	                        list.push("empty");
	                        break;
	                    default:
	                        throw new RuntimeException();
	                }
                }
            }
        }
	    return convert2(out);
	}
	 /*********************************************************/
	 public static int[] convert(ArrayList<Integer> list) {
		 int[] arr = new int[list.size()];
		 for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		 return arr;
	 }
	 /*********************************************************/
	 public static ArrayList<Integer> convert2(int[] list) {
		 ArrayList<Integer> arr = new ArrayList<Integer>();
		 for (int i = 0; i < list.length; i++) {
			arr.add(list[i]);
		}
		 return arr;
	 }
}
