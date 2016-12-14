package driver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class MyDriver implements Driver {

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		String[]arr = url.split(":");
		if(arr.length==3&&arr[0].equals("jdpc")&&arr[1].equals("sql")){
			if(Files.exists(Paths.get(arr[2]))){
				return true;
			}
		}
	 
		
		return false;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		String userName =info.getProperty("username");
		String password =info.getProperty("password");
		//check user name and password
		return DriverManager.getConnection(url, info);
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		Set<Object>set = info.keySet();
		Iterator<Object>it = set.iterator();
		DriverPropertyInfo[]driverPropertyInfos = new DriverPropertyInfo[info.size()];
		for (int i = 0; i < driverPropertyInfos.length; i++) {
			driverPropertyInfos[i] = new DriverPropertyInfo(it.next().toString(),info.getProperty(it.toString()));
		}
		return null;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

}