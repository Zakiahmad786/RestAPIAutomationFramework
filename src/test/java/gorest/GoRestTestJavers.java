package gorest;

import com.fasterxml.jackson.core.JsonProcessingException;
import gorest.pojos.GoRest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GoRestTestJavers extends GorestAPIs{
    @Test
    void createUserTest2Javers() throws JsonProcessingException {
        /*GoRest request=Payloads.getCreateGorestPayloadFromPojo();
        Response res=createUser(request);
        Assert.assertEquals(res.jsonPath().getString("name"),request.getName());

        System.out.println(res.jsonPath().getString("names"));
        //Here we are using wrong name so it will fail and will give NullPointerException
        //With the help of Optional we can print the exception instead of using Options
        Optional field=Optional.ofNullable(res.jsonPath().get("idd"));
        if (field.isPresent()){
            System.out.println("Id is: "+field);
        }
        else{
            System.out.println("Field is not present");
        }*/
        GoRest request=Payloads.getCreateGorestPayloadFromPojo();
        Response response=createUser(request);
        Map<String,Object> expectedValueMap=new HashMap<>();
        expectedValueMap.put("name",request.getName());
        expectedValueMap.put("gender",request.getGender());
        expectedValueMap.put("email",request.getEmail());
        expectedValueMap.put("status",request.getStatus());

        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValueMap);
    }
}
