package eg.edu.alexu.csd.oop.jdbc.bridge.cs42;

import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.AlterExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.CreateExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.DeleteExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.DropExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.IExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.InsertExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.SelectExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.UpdateExtractor;
import eg.edu.alexu.csd.oop.jdbc.extractInformation.cs42.UseExtractor;

public class ExtractorFactory {

    public static IExtractor getExtractor(String extractor) {
	if (extractor.equals("create")) {
	    return new CreateExtractor();
	} else if (extractor.equals("delete")) {
	    return new DeleteExtractor();
	} else if (extractor.equals("drop")) {
	    return new DropExtractor();
	} else if (extractor.equals("insert")) {
	    return new InsertExtractor();
	} else if (extractor.equals("select")) {
	    return new SelectExtractor();
	} else if (extractor.equals("update")) {
	    return new UpdateExtractor();
	} else if (extractor.equals("alter")) {
	    return new AlterExtractor();
	} else if (extractor.equals("use")) {
	    return new UseExtractor();
	} else {
	    return null;
	}
    }
}
