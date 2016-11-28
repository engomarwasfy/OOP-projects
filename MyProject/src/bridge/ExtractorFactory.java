package bridge;

import extractInformation.CreateExtractor;
import extractInformation.DeleteExtractor;
import extractInformation.DropExtractor;
import extractInformation.IExtractor;
import extractInformation.InsertExtractor;
import extractInformation.SelectExtractor;
import extractInformation.UpdateExtractor;

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
	} else {
	    return null;
	}
    }
}
