package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// This class is under development
public class GistCrud {

    private String idCreatedGist;

    public GistCrud() {
        RestAssured.baseURI = "https://api.github.com";
    }

    public static void main(String[] args) {
        GistCrud gistCrud = new GistCrud();
        List<String> idGistForDeleting = gistCrud.getIdOfAllGists();
    }

    public void deleteGist(String gistId) {
        Response response =  RestAssured.given()
                .header("Authorization", "Basic cmVhMnR1dEBnbWFpbC5jb206a2lja21lVUIyMg==")
                .header("Content-Type", "application/json")
                .when()
                .delete("/gists/" + gistId)
                .andReturn();
        System.out.println( response.getStatusLine());
    }

    public List<String> getIdOfAllGists(){
        Response response = RestAssured.given()
                    .header("Authorization", "Basic cmVhMnR1dEBnbWFpbC5jb206a2lja21lVUIyMg==")
                    .when()
                    .get("/gists/public")
                    .andReturn();
        String jsonText = response.getBody().asString();
        System.out.println(jsonText);
        try {
            PrintWriter out = new PrintWriter("D:\\Projects\\testingrest\\src\\test\\java\\resources\\list_of_gists.json");
            out.println(jsonText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonObjects = jsonParser.parse(jsonText).getAsJsonArray();
        List<String> ids = new ArrayList<>();

//        for (int i = 0; i < jsonObjects.size(); i++) {
//        ids.add(jsonObjects.get(i).getAsJsonObject().get("id").getAsString());
//        }
//        ids.forEach(x -> System.out.println(x));
        return ids;
    }



}
