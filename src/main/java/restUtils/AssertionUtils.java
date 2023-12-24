package restUtils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.response.Response;
import reporting.ExtentReportManager;
import reporting.Setup;

import java.util.*;

public class AssertionUtils {

    public static void assertExpectedValuesWithJsonPath(Response response, Map<String,Object> expectedValueMap){

        List<AssertionKeys> actualValueMap=new ArrayList<>();
        boolean allMatched=true;

        actualValueMap.add(new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT"));


        Set<String> jsonPaths=expectedValueMap.keySet();
        for (String jsonPath:jsonPaths){
            Optional<Object> actualValue=Optional.ofNullable(response.jsonPath().get(jsonPath));
            if (actualValue.isPresent()){
                Object value=actualValue.get();
                if (value.equals(expectedValueMap.get(jsonPath))){
                    actualValueMap.add(new AssertionKeys(jsonPath,expectedValueMap.get(jsonPath),value,"MATCHED"));
                }
                else{
                    allMatched=false;
                    actualValueMap.add(new AssertionKeys(jsonPath,expectedValueMap.get(jsonPath),value,"NOT_MATCHED"));
                }
            }
            else {
                allMatched=false;
                actualValueMap.add(new AssertionKeys(jsonPath,expectedValueMap.get(jsonPath),"VALUE_NOT_FOUND","NOT_MATCHED"));
            }
        }
        if (allMatched){
            ExtentReportManager.logPassDetails("All assertions are passed.");
        }
        else{
            ExtentReportManager.logFailureDetails("All assertions are not passed.");
        }
        String[][] finalAssertionsMap=actualValueMap.stream().map(assertions -> new String[] {assertions.getJsonPath(),String.valueOf(assertions.getExpectedValue()),
                        String.valueOf(assertions.getActualValue()),assertions.getResult()})
                    .toArray(String[][] :: new);
            Setup.extentTest.get().info(MarkupHelper.createTable(finalAssertionsMap));

    }
}
