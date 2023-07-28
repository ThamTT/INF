package stepdefinitions.api;

import core.CoreAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import object.request.LoginRequest;
import org.junit.Assert;

public class StepsLoginAPI {
    LoginRequest loginRequest = new LoginRequest();
    CoreAPI apiBase = new CoreAPI();
    Response response;
    int status;
    public static String tokenAccess;

    @Given("Send Api Login with username and password {string}")
    public void sendApiLoginWithUsernameAndPassword(String value) {
        loginRequest.getDataInputCreateProject(value);

        response = loginRequest.postAPILogin();
        status = response.getStatusCode();
        try {
            tokenAccess = apiBase.getResponseJsonData(response).get("response").get("accessToken").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("Verify Login success with status code is {int}")
    public void verifyLoginSuccessWithStatusCodeIs(int sttCode) {
        Assert.assertEquals(sttCode, status);
    }
}
