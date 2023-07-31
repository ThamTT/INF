package object.request;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commom.EndPointAPI;
import core.CoreAPI;
import io.restassured.response.Response;
import pages.CommonPage;

import java.io.IOException;
import java.util.Map;

public class LichSuThayDoiHanMucRequest extends CoreAPI {
    CommonPage commonPage = new CommonPage();
    CoreAPI apiBase = new CoreAPI();

    public void getIDBanGhi(String token, Map<String, String> params) throws IOException {
        Response response = sendRequestUsingTokenAndHaveParam(
                EndPointAPI.GET_LIST_HISTORY, "GET", null, "Yes", token, params);
        ArrayNode records = (ArrayNode) apiBase.getResponseJsonData(response).get("response").get("data");
        String idRecord1 = records.get(0).get("id").asText();
        commonPage.setValueSection("IDRecordHistory", idRecord1);
    }
}
