package restUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportManager;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String endPoint,Object requestPayload,Map<String,String>headers){

        return RestAssured.given()
                .baseUri(endPoint)
                .contentType("application/json")
                .headers("Authorization","Bearer "+bearerToken)
                .body(requestPayload);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is "+queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is "+queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request Body is ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    private static void printResponseLogInReport(Response response){
        ExtentReportManager.logInfoDetails("Response status is "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response headers are ");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response Body is ");
        ExtentReportManager.logInfoDetails("Response Body is "+response.getBody().prettyPrint());

    }


    static String bearerToken="2ebffc2e8351c0553254651c1b9f49a6616b8414f1827121146677e5b9c5a278";
    public static Response performPost(String endPoint, String requestPayload, Map<String,String> headers){

        RequestSpecification requestSpecification=getRequestSpecification(endPoint,requestPayload,headers);
        Response response= requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
    public static Response performPost(String endPoint, Map<String, Object> requestPayload, Map<String,String> headers){

        RequestSpecification requestSpecification=getRequestSpecification(endPoint,requestPayload,headers);
        Response response =requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
    public static Response performPost(String endPoint, Object requestPayload, Map<String,String> headers){

        RequestSpecification requestSpecification=getRequestSpecification(endPoint,requestPayload,headers);
        Response response =requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
}