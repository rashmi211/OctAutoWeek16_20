package test;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class E2E_ReadJson {
	public static String getData(String path) throws IOException, ParseException {
		FileReader fr =new FileReader(path);
		JSONParser jp=new JSONParser();
		String reqbody=jp.parse(fr).toString();
		return reqbody;
	}

}
