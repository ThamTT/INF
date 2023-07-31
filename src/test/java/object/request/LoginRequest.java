package object.request;

import com.fasterxml.jackson.databind.JsonNode;
import commom.EndPointAPI;
import core.CoreAPI;
import io.restassured.response.Response;

import java.util.Map;

public class LoginRequest extends CoreAPI {
    public static JsonNode dataInputCreate;

    public void getDataInputCreateProject(String testCaseTitle) {
        String fileName = testCaseTitle.split("_")[0];
        String groupName = testCaseTitle.split("_")[1];
        String testcase = testCaseTitle.split("_")[2];
        JsonNode dataObject = readJsonFile(fileName, groupName, testcase);
        dataInputCreate = dataObject;
    }

    public Response postAPILogin() {
        return sendRequest(EndPointAPI.LOGIN_URL, "POST", dataInputCreate);
    }

}
