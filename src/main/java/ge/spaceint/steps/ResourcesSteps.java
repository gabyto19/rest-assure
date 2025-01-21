package ge.spaceint.steps;

import ge.spaceint.models.requests.Resource;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static ge.spaceint.data.Constants.*;
import static ge.spaceint.utils.HelperFunctions.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@SuppressWarnings("UnusedReturnValue")
public class ResourcesSteps {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    RequestSpecification requestSpecifications;
    Response response;
    Resource updatedResource;
    String payload;

    @Step("Build Request Specifications For Resources")
    public ResourcesSteps setUpRequestSpecifications(){
        requestSpecifications = requestSpecBuilder
                .setBaseUri(BASE_URL)
                .setBasePath(RESOURCES_PATH)
                .setContentType(JSON)
                .setAccept(JSON)
                .build();
        return this;
    }

    @Step("Build Payload Object")
    public ResourcesSteps buildPayloadObject(){
        updatedResource = Resource
                .builder()
                .color(COLOR_VALUE)
                .build();
        return this;
    }

    @Step("Serialize Payload")
    public ResourcesSteps serializePayloadObject(){
        payload = serialize(updatedResource);
        return this;
    }

    @Step("Update Resource with id - {RESOURCE_ID}")
    public ResourcesSteps performPutResourcesRequest(){
        response = given()
                .spec(requestSpecifications)
                .when()
                .body(payload)
                .put(String.valueOf(RESOURCE_ID))
                .then()
                .statusCode(200)
                .extract().response();
        return this;
    }

    @Step("Delete Resource with id - {RESOURCE_ID}")
    public ResourcesSteps performDeleteRequest(){
        response = given()
                .spec(requestSpecifications)
                .when()
                .delete(String.valueOf(RESOURCE_ID))
                .then()
                .statusCode(204)
                .extract().response();
        return this;
    }

    @Step("Validate Resource Color - {COLOR_VALUE}")
    public ResourcesSteps validateUpdate() {
        Assert.assertEquals(response.jsonPath().getString("color"), COLOR_VALUE);
        return this;
    }

    @Step("Validate Date")
    public ResourcesSteps validateUpdateDate(){
        Assert.assertEquals(parseDate(response.jsonPath().getString("updatedAt")), getCurrentUtcDate());
        return this;
    }
}
