package tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.http.ContentType.JSON;

public class RestAssuredGitTest {
    private String idCreatedGist;
    private final String TOKEN = "Basic cmVhMnR1dEBnbWFpbC5jb206a2lja21lVUIyMg==";

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com";
    }

    // Creating of the new gists. We use POST method here in the test case
    @Test
    public void testPost() throws IOException {
       String json = new String(Files.readAllBytes(Paths.get("D:\\Projects\\testingrest\\src\\test\\java\\resources\\gist.json")));
       Response response =  RestAssured.given()
               .contentType(JSON)
               .header("Authorization", TOKEN)
               .body(json)
               .when()
               .post("/gists")
               .andReturn();
        String jsonText = response.getBody().asString();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(jsonText).getAsJsonObject();
        idCreatedGist = jsonObject.get("id").getAsString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    // Deleting of the created list. We use DELETE method in the test case
    @Test (dependsOnMethods = "testPost")
    public void testDelete(){
        Response response =  RestAssured.given()
                .header("Authorization", "Basic cmVhMnR1dEBnbWFpbC5jb206a2lja21lVUIyMg==")
                .header("Content-Type", "application/json")
                .when()
                .delete("/gists/" + idCreatedGist)
                .andReturn();
        printResponse(response);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    // This method is used for debugging and provide extra information into StOut
    private void printResponse(Response response){
        System.out.println(response.getStatusLine());
    }

}

