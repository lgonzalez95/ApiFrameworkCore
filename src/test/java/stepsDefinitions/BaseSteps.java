package stepsDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class BaseSteps {
    static RequestSpecification requestSpecification;

    public static RequestSpecification requestSpecificationSetup() {
        PrintStream log;

        try {
            if (requestSpecification == null) {
                log = new PrintStream(new FileOutputStream("Logs.log"));
                requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                        .addHeader("Authorization", "Bearer " + getGlobalValue("access_token"))
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .setContentType(ContentType.JSON).build();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestSpecification;
    }

    public static String getGlobalValue(String propertyValue) throws IOException {
        Properties prop = new Properties();
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/global.properties");
        prop.load(propertiesFile);
        return prop.getProperty(propertyValue);
    }

//    @After("@CreateUsersValid")
//    public void testEnd(){
//       // requestSpecification = given().spec(requestSpecificationSetup()).delete();
//    }
}
