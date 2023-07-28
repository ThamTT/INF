package object.request;

import com.fasterxml.jackson.databind.JsonNode;
import commom.EndPointAPI;
import core.CoreAPI;
import io.restassured.response.Response;

public class CashingService extends CoreAPI {
    public static JsonNode dataInputProfile;

    public void postDataInputProfile(String testCaseTitle) {
        dataInputProfile = readJsonFile("DataProject.json", testCaseTitle, "body");
    }
    public Response postUpdateUserProfile(String token) {
        return (Response) sendRequestWithToken(EndPointAPI.POST_TAKE_REQUEST_URL, "POST", dataInputProfile, "yes", token);
    }
}
