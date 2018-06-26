package Main.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {

	private static Properties properties = null;
	
	public static synchronized Properties getProperties(String prop) throws RuntimeException {
		initialize(prop);
		return properties;
	}

	private static synchronized void initialize(String prop) {
		
		FileInputStream is = null;
		
		try {
			is = new FileInputStream(prop);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (is == null) {
		    System.out.println("The property is null.");
			return;
		}
		
		properties = new Properties();
		
		try {
			properties.load(is);
		} catch (IOException e) {
		    System.out.println("Properties loading fails.");
			throw new RuntimeException(e);
		} finally{
			try{
				if(is != null)
					is.close();
			} catch(Exception ex){
			    System.out.println("Properties loading fails because of runtime exception.");
				throw new RuntimeException(ex);
			}
		}
	}

}
