package test;

import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class E2E_UsetStory {
	public static String cookie="";
	public static String storyid="";
	
	
	@Test(priority=1)
	public void loginToJira() throws IOException, ParseException {
		String url=E2E_ReadProperty.getProperty("URL");
		
		String login=E2E_ReadJson.getData("C:\\Users\\HP\\eclipse-workspace1\\practiceOct16_20\\src\\main\\java\\utility\\LoginBody.json");
		
		Response response=RestAssured.given().baseUri(url).body(login).contentType(ContentType.JSON)
		.when().post("/rest/auth/1/session")
		.then().log().body().extract().response();
		
		System.out.println(response.getStatusCode());
		
		JSONObject js=new JSONObject(response.asString());
		 cookie="JSESSIONID="+js.getJSONObject("session").get("value").toString();
		
	}
	@Test(priority=2)
	public void createStory() throws IOException, ParseException {
		String url=E2E_ReadProperty.getProperty("URL");
		
		String body=E2E_ReadJson.getData("C:\\Users\\HP\\eclipse-workspace1\\practiceOct16_20\\src\\main\\java\\utility\\RequestBody.json");
		
		Response response=RestAssured.given().baseUri(url).body(body).header("Cookie",cookie).contentType(ContentType.JSON)
		.when().post("/rest/api/2/issue")
		.then().log().body().extract().response();
		
		System.out.println(response.getStatusCode());
		
		JSONObject js=new JSONObject(response.asString());
		 storyid=js.get("key").toString();
		
	}
	
	@Test(priority=3)
	public void getstory() throws IOException, ParseException{
        String url=E2E_ReadProperty.getProperty("URL");
	
        Response response=RestAssured.given().baseUri(url).header("Cookie",cookie).contentType(ContentType.JSON).pathParam("issueid", storyid)
        		.when().get("/rest/api/2/issue/{issueid}")
        		.then().log().body().extract().response();
        
        System.out.println(response.getStatusCode());
		
	}
	
	@Test(priority=4)
	public void updateStory() throws IOException, ParseException{
		
        String url=E2E_ReadProperty.getProperty("URL");
        
        String body=E2E_ReadJson.getData("C:\\Users\\HP\\eclipse-workspace1\\practiceOct16_20\\src\\main\\java\\utility\\RequestBody.json");
		
        JSONObject j=new JSONObject(body);
        j.getJSONObject("fields").put("summary", "Update Userstory For Oct week 16-20 2023").toString();
        
	
        Response response=RestAssured.given().baseUri(url).header("Cookie",cookie).body(j.toString()).contentType(ContentType.JSON).pathParam("issueid", storyid)
        		.when().put("/rest/api/2/issue/{issueid}")
        		.then().log().body().extract().response();
        
        System.out.println(response.getStatusCode());
		
	}
	@Test(priority=5)
	public void getUpdatedstory() throws IOException, ParseException{
        String url=E2E_ReadProperty.getProperty("URL");
	
        Response response=RestAssured.given().baseUri(url).header("Cookie",cookie).contentType(ContentType.JSON).pathParam("issueid", storyid)
        		.when().get("/rest/api/2/issue/{issueid}")
        		.then().log().body().extract().response();
        
        System.out.println(response.getStatusCode());

	}
	
	@Test(priority=6)
	public void Deletestory() throws IOException, ParseException{
        String url=E2E_ReadProperty.getProperty("URL");
	
        Response response=RestAssured.given().baseUri(url).header("Cookie",cookie).contentType(ContentType.JSON).pathParam("issueid", storyid)
        		.when().delete("/rest/api/2/issue/{issueid}")
        		.then().log().body().extract().response();
        
        System.out.println(response.getStatusCode());

	}
}