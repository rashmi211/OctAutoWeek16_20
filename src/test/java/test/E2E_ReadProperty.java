package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class E2E_ReadProperty {
	public static String getProperty(String key) throws IOException {
		FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct16_20\\src\\main\\java\\utility\\Env.properties");
		Properties pr=new Properties();
		pr.load(fr);
		String s=pr.getProperty(key);
	    return s;
	
	
	}

}
