package liveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class Activity
{
    RequestSpecification requestSpecification;
    int id;


    @BeforeClass
    public void setUp()
    {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://api.github.com/user/keys")
                .setAuth(oauth2("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"))
                .build();
    }

    @Test(priority = 1)
    public void postRequest()
    {
        String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCWHxPvR7EuOP7ttqgYMyQeGLpCYFTedLR6dVuaq9vlOdzda/xOT9+beNQ3g5hmmifmiWynn3ZRBJUvXCvcb3D7kxXKd6YtN6mJEepqrjt9YFerIDtu91uRCuO0z4YU3m6yOtb8Ll/RSDDA4Qt2MCEhJ96wwreOM5CokJ7amOXjUo1iO6J2jOnoHxwNdz3pa2zI0N/yW6xI28kgclHzDM6bbbAp2+E4pKV4zz2Sa3DwUzwyOFKIas1OPspxLVwrsNgYPcWMVZ0fTRA6C6OslzpxvKnZ5C7FObsXaQo/2iZSTUL+v2PQFxJ6kD/JOfvfZBk52lHBjBDjLXlkqcMvoGnb\"}";

        Response response = given().spec(requestSpecification)
                .body(reqBody)
                .when()
                .post();

        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(201);

        id = response.then().extract().path("id");
    }

    @Test(priority = 2)
    public void getRequest()
    {
        Response response = given().spec(requestSpecification)
                .when()
                .pathParam("keyId", id)
                .get("/{keyId}");

        response.then().statusCode(200);
    }

    @Test(priority = 3)
    public void deleteRequest()
    {
        Response response = given().spec(requestSpecification)
                .when()
                .pathParam("keyId", id)
                .delete("/{keyId}");

        response.then().statusCode(204);

    }


}
