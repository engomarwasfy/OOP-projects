package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

	private Logger logger;
	public FileHandler fh;
	public static Log Log;

	private Log() {
		try {
			fh = new FileHandler("MyLogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Log getInstance(){
		if(Log==null){
			Log= new Log();
		}
		return Log;
	}

	public void log(String data, String type) {
		// This block configure the logger with handler and formatter

		// the following statement is used to log any messages
		if (type.equals("info")) {
			logger.info(data);
		} else {
			logger.warning(data);
		}

	}

}
