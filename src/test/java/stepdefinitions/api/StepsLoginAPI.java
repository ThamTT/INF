package stepdefinitions.api;

import core.CoreAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import object.request.LichSuThayDoiHanMucRequest;
import object.request.LoginRequest;
import org.junit.Assert;
import pages.CommonPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepsLoginAPI {
    LoginRequest loginRequest = new LoginRequest();
    CoreAPI apiBase = new CoreAPI();
    CommonPage commonPage = new CommonPage();
    LichSuThayDoiHanMucRequest historyRequest = new LichSuThayDoiHanMucRequest();
    Response response;
    int status;
    public static String tokenAccess;
    Map<String, String> mapParams = null;
    @Before
    public void configAll(){
        RestAssured.useRelaxedHTTPSValidation();
    }

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
        commonPage.setValueSection("accessToken", tokenAccess);
        System.out.println("tocken = " + tokenAccess);
    }

    @Then("Verify Login success with status code is {int}")
    public void verifyLoginSuccessWithStatusCodeIs(int sttCode) {
        Assert.assertEquals(sttCode, status);
    }

    @And("Get {string} record by API with param")
    public void getIDRecordByAPI(String info, DataTable dataTable) throws IOException {
        mapParams =  new HashMap<>();
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> stringStringMap : list) {
            mapParams.put(stringStringMap.get("Key"), stringStringMap.get("Value"));
        }
        if(info.equals("ID")){
            historyRequest.getIDBanGhi(commonPage.getValueSection("accessToken"), mapParams);
        }
    }
}
