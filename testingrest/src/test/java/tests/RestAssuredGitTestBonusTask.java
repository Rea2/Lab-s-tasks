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

public class RestAssuredGitTestBonusTask {
    private String idCreatedGist;
    private final String TOKEN = ""; // you should set you TOKEN here

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com";
    }

    // Creating of the new gists. We use POST method
    @Test
    public void testPost() throws IOException {
       String json = new String(Files.readAllBytes(Paths.get("D:\\Projects\\Lab_Single_Tasks\\testingrest\\src\\test\\resources\\gist.json")));
       Response response =  RestAssured.given()
               .header("Authorization", TOKEN)
               .header("content-type", "application/json;charset=utf-8")
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

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }
}

