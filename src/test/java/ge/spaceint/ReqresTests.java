package ge.spaceint;

import ge.spaceint.steps.ResourcesSteps;
import ge.spaceint.steps.SessionSteps;
import ge.spaceint.steps.UsersSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReqresTests {
    UsersSteps usersSteps;
    SessionSteps sessionSteps;
    ResourcesSteps resourcesSteps;
    String token;

    @BeforeClass
    public void setUp(){
        usersSteps = new UsersSteps();
        sessionSteps = new SessionSteps();
        resourcesSteps = new ResourcesSteps();
    }

    @Test(priority = 1)
    public void loginUserTest() {
        // We can get session token for later use
        token = sessionSteps
                .setUpRequestSpecifications()
                .buildLoginPayloadObject_WrongEmail()
                .serializeLoginPayloadObject()
                .performPostLoginRequest_WrongEmail()
                .validateUnauthorizedLogin()
                .buildLoginPayloadObject()
                .serializeLoginPayloadObject()
                .performPostLoginRequest()
                .getSessionToken();
    }

    @Test(priority = 2)
    public void getUsersTest(){
        usersSteps
                .setUpRequestSpecifications()
                .performGetUsersRequest()
                .deserializeResponse()
                .validateUsersPage()
                .validateUsersPerPage();
    }

    @Test(priority = 3)
    public void updateAndDeleteResourcesTest(){
        resourcesSteps
                .setUpRequestSpecifications()
                .buildPayloadObject()
                .serializePayloadObject()
                .performPutResourcesRequest()
                .validateUpdate()
                .validateUpdateDate()
                .performDeleteRequest();
    }
}
