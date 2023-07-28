package object.request;

import com.fasterxml.jackson.databind.JsonNode;
import commom.EndPointAPI;
import core.CoreAPI;
import io.restassured.response.Response;

public class LoginRequest extends CoreAPI {
    public static JsonNode dataInputCreate;

    public void getDataInputCreateProject(String testCaseTitle) {
        JsonNode dataObject = readJsonFile("DataProject.json", testCaseTitle, "body");
        dataInputCreate = dataObject;
    }

    public Response postAPILogin() {
        return sendRequest(EndPointAPI.LOGIN_URL, "POST", dataInputCreate, "yes");
    }
//
//    public void getDataInputCreateProjects() throws IOException {
//        String pathFile = "/src/test/resources/data/%s/";
//        String filePath =
//                String.format(
//                        "%s%s%s", System.getProperty("user.dir"), String.format(pathFile, env()), "DataProject.json");
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode node = null;
//        JsonNode jsonNode = mapper.readTree(Paths.get(filePath).toFile());
//        node = jsonNode.get("puts");
//
//        List<String> arr = null;
//        ((ObjectNode) node).put("recIdList", (BigDecimal) arr);
//        dataInputCreate = node;
//        System.out.println("data = " + dataInputCreate);
//    }
}
