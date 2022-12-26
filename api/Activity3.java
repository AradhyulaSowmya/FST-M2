package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity3
{
    RequestSpecification requestspec;
    ResponseSpecification responsespec;

    @BeforeClass
    public void setUp()
    {
        requestspec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io/v2/pet")
                .build();

        responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .expectBody("status", equalTo("alive"))
                .build();
    }

    @DataProvider
    public Object[][] petInfoProvider()
    {
        Object[][] testData = new Object[][]
                {
                        { 77232, "Riley", "alive" },
                        { 77233, "Hansel", "alive" }
                };
        return testData;
    }

    @Test(priority = 1)
    public void postRequest()
    {
        String reqBody = "{\"id\": 77232, \"name\": \"Riley\", \"status\": \"alive\"}";

        Response respone = given().spec(requestspec)
                .body(reqBody)
                .when().post();

        reqBody = "{\"id\": 77233, \"name\": \"Hansel\", \"status\": \"alive\"}";

        Response response = given().spec(requestspec)
                .body(reqBody)
                .when().post();

        response.then().spec(responsespec);
    }

    @Test(dataProvider = "petInfoProvider", priority = 2)
    public void getRequest(int id, String name, String status)
    {
        Response response = given().spec(requestspec)
                .pathParam("petId" ,id)
                .when().get("/{petId}");

        System.out.println(response.asPrettyString());

        response.then().spec(responsespec).body("name",equalTo(name));
    }

    @Test(dataProvider = "petInfoProvider", priority = 3)
    public void deleteRequest(int id, String name, String status)
    {
        Response response = given().spec(requestspec).pathParam("petId",id).when().delete("/{petId}");

        response.then().body("code", equalTo(200));
    }
}
