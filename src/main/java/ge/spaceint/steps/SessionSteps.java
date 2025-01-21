package ge.spaceint.steps;

import ge.spaceint.models.requests.UserLogin;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static ge.spaceint.data.Constants.*;
import static ge.spaceint.utils.HelperFunctions.serialize;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@SuppressWarnings("UnusedReturnValue")
public class SessionSteps {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    RequestSpecification requestSpecifications;
    UserLogin user;
    Response response;
    String payload;

    @Step("Setting Up Request Specifications")
    public SessionSteps setUpRequestSpecifications(){
        requestSpecifications = requestSpecBuilder
                .setBaseUri(BASE_URL)
                .setBasePath(LOGIN_PATH)
                .setContentType(JSON)
                .setAccept(JSON)
                .build();
        return this;
    }

    @Step("Building Register Payload Object")
    public SessionSteps buildLoginPayloadObject(){
        user = UserLogin
                .builder()
                .email(EVE_EMAIL)
                .password(EVE_PASSWORD)
                .build();
        return this;
    }

    @Step("Building Register Payload Object With Wrong Email")
    public SessionSteps buildLoginPayloadObject_WrongEmail(){
        user = UserLogin
                .builder()
                .email(EVE_EMAIL_WRONG)
                .password(EVE_PASSWORD)
                .build();
        return this;
    }

    @Step("Serialize Payload Object")
    public SessionSteps serializeLoginPayloadObject(){
        payload = serialize(user);
        return this;
    }

    @Step("Perform POST request")
    public SessionSteps performPostLoginRequest(){
        response = given()
                .spec(requestSpecifications)
                .when()
                .body(payload)
                .post()
                .then()
                .statusCode(200)
                .extract().response();
        return this;
    }

    @Step("Perform POST request")
    public SessionSteps performPostLoginRequest_WrongEmail(){
        response = given()
                .spec(requestSpecifications)
                .when()
                .body(payload)
                .post()
                .then()
                .statusCode(400)
                .extract().response();
        return this;
    }

    @Step("Validate Unauthorized Access")
    public SessionSteps validateUnauthorizedLogin(){
        Assert.assertEquals(response.jsonPath().getString("error"), ERROR_MESSAGE);
        return this;
    }

    @Step("Get Session Token Id")
    public String getSessionToken(){
        return response.jsonPath().getString("token");
    }
}
