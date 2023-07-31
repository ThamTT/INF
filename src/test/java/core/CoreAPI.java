package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreAPI {
    private static final Logger LOGGER = Logger.getLogger(CoreAPI.class.getName());
    protected static final ObjectMapper objectMapper = new ObjectMapper();
    private String pathFile = System.getProperty("user.dir") + "/src/test/resources/data/dataJson/";

    public Response sendRequest(String endpoint, String method, Object obj) {
        RequestSpecification httpRequest = SerenityRest.given();
            httpRequest.header("Content-Type", "application/json");
        switch (method.toUpperCase()) {
            case "GET":
                return httpRequest.get(endpoint);
            case "POST":
                return httpRequest.body(obj).post(endpoint);
            case "DELETE":
                return httpRequest.delete(endpoint);
            case "PUT":
                return httpRequest.body(obj).put(endpoint);
            default:
                Assert.fail("Please input Method");
                return null;
        }
    }

    public JsonNode readJsonFile(String fileName, String rootObject, String object) {
        String filePath = pathFile + fileName;
        JsonNode node = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(Paths.get(filePath).toFile());
            node = jsonNode.get(rootObject).get(object);
        } catch (Exception error) {
            LOGGER.log(Level.WARNING, error.toString());
        }
        return node;
    }

    public JsonNode getResponseJsonData(Response response) throws IOException {
        if (response != null) return objectMapper.readTree(response.asString());
        else {
            return null;
        }
    }

    public Response sendRequestWithToken(String endpoint, String method, Object obj, String apiKey, String tokenAccess) {
        RequestSpecification httpRequest = SerenityRest.given();
        if (apiKey.equalsIgnoreCase("yes")) {
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", tokenAccess);
        } else {
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", tokenAccess);
        }

        switch (method.toUpperCase()) {
            case "GET":
                return httpRequest.get(endpoint);
            case "POST":
                return httpRequest.body(obj).post(endpoint);
            case "DELETE":
                return httpRequest.delete(endpoint);
            case "PUT":
                return httpRequest.body(obj).put(endpoint);
            default:
                Assert.fail("Please input Method");
                return null;
        }
    }

    public Response sendRequestUsingTokenAndHaveParam(String endpoint, String method, Object obj, String apiKey, String authorizationHeader, Map<String, String> params) {

        List<String> results = new ArrayList<>();
        params.forEach((k, v) -> results.add(k.trim() + "=" + v.trim()));
        endpoint = endpoint + "?" + String.join("&", results);
        System.out.println("endPoind = " + endpoint);
        RequestSpecification httpRequest = SerenityRest.given();
        if (apiKey.equalsIgnoreCase("yes")) {
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", "Bearer " + authorizationHeader);
        }

        switch (method.toUpperCase()) {
            case "GET":
                return httpRequest.get(endpoint);
            case "POST":
                return httpRequest.body(obj).post(endpoint);
            case "DELETE":
                return httpRequest.delete(endpoint);
            case "PUT":
                return httpRequest.body(obj).put(endpoint);
            default:
                Assert.fail("Please input Method");
                return null;
        }
    }
}
