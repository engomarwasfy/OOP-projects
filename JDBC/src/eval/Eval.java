package eval;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import engine.XmlParsingMethods;

public class Eval {
  private final Postfix post = new Postfix();

  public ArrayList<Integer> getindexes(final File xml, final String cond) {
    final String[] arr = post.getpostfix(cond);
    final ArrayList<Integer> out = evalPostfix(xml, arr);
    return out;
  }

  /*********************************************************/
  public static ArrayList<Integer> evalPostfix(final File xml, final String[] exp) {
    final Set set = new Set();
    new XmlParsingMethods();
    final Stack<String> list = new Stack<String>();
    String n2; // result of 2nd popping
    int[] out1;
    int[] out2;
    int[] out = new int[0];
    final ArrayList<Integer> arr1 = new ArrayList<>();
    ArrayList<Integer> arr = new ArrayList<>();
    final ArrayList<Integer> arr2 = new ArrayList<>();
    for (int i = 0; i < exp.length; i++) {
      final String ch = exp[i];
      if (!(ch.equals("*") || ch.equals("+") || ch.equals("-"))) {
        list.push(ch);
      } else {
        list.pop();
        n2 = list.pop();

        if (n2.equals("empty")) {
          // arr1 = parse.parseCondition(xml.getName().substring(0,
          // xml.getName().indexOf('.')), n1);
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
          // arr1 = parse.parseCondition(xml, n1);
          out1 = new int[arr1.size()];
          out1 = convert(arr1);
          // arr2 = parse.parseCondition(xml, n2);
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
  public static int[] convert(final ArrayList<Integer> list) {
    final int[] arr = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      arr[i] = list.get(i);
    }
    return arr;
  }

  /*********************************************************/
  public static ArrayList<Integer> convert2(final int[] list) {
    final ArrayList<Integer> arr = new ArrayList<Integer>();
    for (int i = 0; i < list.length; i++) {
      arr.add(list[i]);
    }
    return arr;
  }
}
