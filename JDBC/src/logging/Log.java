package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
  private static int x ;
	private static Logger logger ;  
	public static FileHandler  fh  ; 
	static{
		x=0;
		logger = Logger.getLogger("MyLog");
	}
	private static void init(){
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
	public static void log(String data,String type) {  
	    try {  
	    	if(x == 0){
	    		init();
	    	}
	    	x++;
	        // This block configure the logger with handler and formatter
	       

	        // the following statement is used to log any messages  
	        if(type.equals("info")){
		        logger.info(data);  
	        }else{
	        	logger.warning(data);
	        }

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    }  

	}
	
}



