package gorest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.GoRest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restUtils.RestUtils;
import utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GorestTest extends GorestAPIs{

    String bearerToken="2ebffc2e8351c0553254651c1b9f49a6616b8414f1827121146677e5b9c5a278";
    @Test
    public void createUserTest() throws IOException {

        //String payload=Payloads.getCreateUserPayload("Zaki Ahma","Male","zaki198@test.com","inactive");
        //Map<String,Object> payload=Payloads.getCreateUserPayloadFromMap("Test774","Male","za495@test.com","inactive");
       // Map<String,Object> payload=Payloads.getCreateUserPayloadFromMap();
        //GoRest goRest=Payloads.getCreateGorestPayloadFromPojo();
       // Response res = RestUtils.performPost(endPoint,payload,new HashMap<>());
        //GoRest payload=new GoRest();
       GoRest payload=new GoRest().toBuilder().build();
        Response res=createUser(payload);
       // createUser(payload).then().log().all();
        Assert.assertEquals(res.getStatusCode(),201);
    }

    @Test
    void createUserTest1() throws JsonProcessingException {
        GoRest payload=new GoRest();
        Response res=createUser(payload);

        //First way to compare the response...But this is hectic as we have to compare each value

        Assert.assertEquals(res.jsonPath().getString("name"),payload.getName());

        //Now we are converting json response to object with the help of jackson library as below.This will work only when both request and response payload are same.
        //In some cases we might be having some extra field but that we can ignore with the help of JsonIgnoreProperties.
        //Now let's consider we have some different response body with some value then this will fail.
        //We are comparing two objects here and it's better than the 1st way.

        //Let's consider we have a big list of datas and it's failing due to some missing error, then it will be very difficult to check one by one and find our the reason of
        //failure.With the help of Javers conepts we can achieve this.It's an Interface
        ObjectMapper objMap=new ObjectMapper();
        GoRest createUserResponse=objMap.readValue(res.getBody().asString(),GoRest.class);

        Assert.assertEquals(createUserResponse,payload);

        //To get the Javers interface instance we need to write the below code.
        //Javers javers=JaversBuilder().javers().build();
    }
}
