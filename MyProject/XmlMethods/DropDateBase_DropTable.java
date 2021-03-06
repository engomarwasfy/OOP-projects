package XmlMethods;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import extractInformation.UsedDataBase;

public class DropDateBase_DropTable {
  public static void delete(final File xmlFile) {
    final Path path = xmlFile.toPath();
    try {
      if (xmlFile.isDirectory()) {
        for (final File c : xmlFile.listFiles()) {
          Files.delete(c.toPath());

        }
        Files.delete(path);

      } else if (xmlFile.getName().contains(".xml")) {
        Files.delete(path);
        Files
            .delete(
                new File(UsedDataBase.getUsedDataBase() + File.separatorChar
                    + xmlFile.getName().substring(0,
                        xmlFile.getName().indexOf('.'))
                    + "Schemma.dtd").toPath());
      }
    } catch (final NoSuchFileException x) {
    	 System.out.println("sql command failed");
    } catch (final DirectoryNotEmptyException x) {
    	 System.out.println("sql command failed");
    } catch (final IOException x) {
    	 System.out.println("sql command failed");
    }
  }
}