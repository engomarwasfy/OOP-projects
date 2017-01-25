package Controller;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JFileChooser;

public class DinamicLinkage {

  public Class<?> loadChosenClass() {
    final JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    final int returnVal = chooser.showOpenDialog(chooser);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      final File file = chooser.getSelectedFile();
      URL myUrl;
      try {
        String nameOfClass = "";
        myUrl = file.toURL();
        final String str = myUrl + "";// full path
        int i = 0;
        for (i = str.length() - 1; i >= 0; i--) {
          if (str.charAt(i) == '/') {
            break;
          }
        }
        nameOfClass = str.substring(++i, str.length() - 6);
        URL name;

        name = new URL(str.substring(0, i));
        return CheckClassExist(name, nameOfClass);
      } catch (final MalformedURLException e1) {

      }

    }
    return null;
  }

  Class<?> CheckClassExist(final URL name, final String nameOfClass) {
    final URL[] my = { name };
    final URLClassLoader classloader = new URLClassLoader(my);
    try {
      return classloader.loadClass(nameOfClass);
    } catch (final ClassNotFoundException e1) {
      // TODO Auto-generated catch block
      System.out.println("Cant load from this path " + name);
      return null;
    }

  }

}
