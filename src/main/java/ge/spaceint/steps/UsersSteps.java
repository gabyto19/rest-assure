package ge.spaceint.steps;

import ge.spaceint.models.responses.Users;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static ge.spaceint.data.Constants.*;
import static ge.spaceint.utils.HelperFunctions.deserialize;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@SuppressWarnings("UnusedReturnValue")
public class UsersSteps {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    RequestSpecification requestSpecifications;
    Response response;
    Users usersResponse;

    @Step("Setting Up Request Specifications")
    public UsersSteps setUpRequestSpecifications(){
        requestSpecifications = requestSpecBuilder
                .setBaseUri(BASE_URL)
                .setBasePath(USERS_PATH)
                .setContentType(JSON)
                .setAccept(JSON)
                .build();
        return this;
    }

    @Step("Perform GET request")
    public UsersSteps performGetUsersRequest(){
        response = given()
                .spec(requestSpecifications)
                .queryParam("page", PAGE_VALUE)
                .queryParam("per_page", PER_PAGE_VALUE)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();
        return this;
    }

    @Step("Print Response Body")
    public UsersSteps printResponseBody(){
        System.out.println(response.asPrettyString());
        return this;
    }

    @Step("Deserialize Response")
    public UsersSteps deserializeResponse() {
        usersResponse = deserialize(response, Users.class);
        return this;
    }

    @Step("Validate Users Response Page Attribute")
    public UsersSteps validateUsersPage(){
        Assert.assertEquals(usersResponse.getPage(), PAGE_VALUE);
        return this;
    }

    @Step("Validate Users Response Per_Page Attribute")
    public UsersSteps validateUsersPerPage(){
        Assert.assertEquals(usersResponse.getData().size(), PER_PAGE_VALUE);
        return this;
    }

}
