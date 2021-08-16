package utils;

import io.restassured.path.json.JsonPath;
import sun.misc.IOUtils;

import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataHelper {
    public static String getJsonPayload(String payloadName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/files/request/createUser.json")));
    }

    public static String getJsonPath(Response response, String key){
        JsonPath jsonPath = new JsonPath(response.asString());
        String elementType = jsonPath.getString(key).getClass().getSimpleName();
        return elementType == "String" ? jsonPath.getString(key) : String.valueOf(jsonPath.getString(key));
    }
}
