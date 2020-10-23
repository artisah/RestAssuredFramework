package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    private static RequestSpecification req;

    public RequestSpecification getRequestSpecification() throws IOException {

        if (req == null) {
            PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalvaluesFromPropertyFile("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .setContentType(ContentType.JSON)
                    .build();
        }

         return req;
    }

    public String getGlobalvaluesFromPropertyFile(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/global.properties");
        prop.load(inputStream);
        return prop.getProperty(key);

    }

    public String getJsonPathvalueFromKey(Response response, String key) {

        String responseString = response.asString();
        JsonPath jsonPath = new JsonPath(responseString);
        return jsonPath.get(key).toString();

    }
}
