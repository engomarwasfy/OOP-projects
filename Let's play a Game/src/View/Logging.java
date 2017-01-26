package View;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {

	 private static int x = 0;
		private static Logger logger = Logger.getLogger("MyLog");
		public static FileHandler  fh ;
		private static void init(){
			 try {
				fh = new FileHandler("MyLogFile.log");
				 logger.addHandler(fh);
			        final SimpleFormatter formatter = new SimpleFormatter();
			        fh.setFormatter(formatter);
			        logger.setUseParentHandlers(false);
			} catch (SecurityException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void log(final String data,final String type) {
		    try {
		    	if(x == 0){
		    		init();
		    	}
		    	x++;
		        // This block configure the logger with handler and formatter


		        // the following statement is used to log any messages
		        if(type.equals("info")){
			        logger.info(data);
		        }else if(type.equals("warn")){
		        	logger.warning(data);;
		        }

		    } catch (final SecurityException e) {
		        e.printStackTrace();
		    }

		}



}
