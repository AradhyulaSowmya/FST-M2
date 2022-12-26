package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import scala.sys.process.ProcessBuilderImpl;

import java.io.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2
{
    final static String ROOTURL = "https://petstore.swagger.io/v2/user";

    @Test(priority = 1)
    public void postRequest() throws IOException
    {
        FileInputStream input = new FileInputStream("src/test/java/activities/info.json");

        String requestBody = new String(input.readAllBytes());

        Response response = given().contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ROOTURL);

        input.close();

        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("9902"));
    }

    @Test(priority = 2)
    public void getRequest()
    {
        File outputJson = new File("src/test/java/activities/getinfo.json");

        Response response = given().contentType(ContentType.JSON)
                .pathParam("username", "justinch")
                .when()
                .get(ROOTURL + "/{username}");

        String resBody = response.getBody().asPrettyString();

        try 
        {
            // Create JSON file
            outputJson.createNewFile();
            // Write response body to external file
            FileWriter writer = new FileWriter(outputJson.getPath());
            writer.write(resBody);
            writer.close();
        } 
        catch (IOException excp) 
        {
            excp.printStackTrace();
        }

        response.then().body("id", equalTo(9902));
        response.then().body("username", equalTo("justinch"));
        response.then().body("firstName", equalTo("Justin"));
        response.then().body("lastName", equalTo("Case"));
        response.then().body("email", equalTo("justincase@mail.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9812763450"));
    }

    @Test(priority = 3)
    public void deleteRequest()
    {
        Response response = given().contentType(ContentType.JSON)
                .pathParam("username", "justinch")
                .when().delete(ROOTURL + "/{username}");

        response.then().body("code",equalTo(200));
        response.then().body("message", equalTo("justinch"));
    }
}
