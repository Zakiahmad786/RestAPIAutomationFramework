package gorest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;
import gorest.pojos.GoRest;
import gorest.pojos.GorestPoiji;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;
import restUtils.RestUtils;
import utils.ExcelUtils;
import utils.JsonUtils;
import utils.RandomDataGenerator;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @DataProvider(name="gorestData")
    public Iterator<GoRest> getCreateGorestUserData() throws IOException {
        List<LinkedHashMap<String,String>> excelDataAsListOfMap= ExcelUtils.getExcelDataAsListOfMap("PayloadExcel","Sheet1");
        List<GoRest> goRestData=new ArrayList<>();
        for (LinkedHashMap<String,String> data:excelDataAsListOfMap){
            GoRest goRest=GoRest.builder()
                    .name(data.get("name"))
                    .gender(data.get("gender"))
                    .email(data.get("email"))
                    .email(data.get("status"))
                    .build();
            goRestData.add(goRest);
        }
        return goRestData.iterator();
    }
    @Test(dataProvider = "gorestData")
    public void createGoRestDataAndCVerify(GoRest goRest){

        Response response=createUser(goRest);
        Map<String,Object> expectedValueMap=new HashMap<>();
        expectedValueMap.put("name",goRest.getName());
        expectedValueMap.put("gender",goRest.getGender());
        expectedValueMap.put("email",goRest.getEmail());
        expectedValueMap.put("status",goRest.getStatus());
        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValueMap);
    }

    @DataProvider(name="gorestDataPoiji")
    public Iterator<GoRest> getCreateGorestUserDataPoiji() throws IOException {
        List<GoRest> data= Poiji.fromExcel(new File("src/test/resources/testData/PayloadExcel.xlsx"), GoRest.class);
        return data.iterator();
    }
    @Test(dataProvider = "gorestDataPoiji")
    public void createGoRestDataAndCVerifyPoiji(GoRest goRest) {

        //This code is used to generate the random data
       /* //String cellValue=goRest.getId();
        String cellValue = goRest.getName();
        int size = 6;
        if (cellValue.contains("RandomNumber")){
        if (cellValue.contains("_")) {
            size = Integer.parseInt(cellValue.split("_")[1]);
        }
        cellValue = RandomDataGenerator.getRandomNumber(size);
    }
        goRest.setName(Integer.parseInt(cellValue));*/
        Response response=createUser(goRest);
        Map<String,Object> expectedValueMap=new HashMap<>();
        expectedValueMap.put("name",goRest.getName());
        expectedValueMap.put("gender",goRest.getGender());
        expectedValueMap.put("email",goRest.getEmail());
        expectedValueMap.put("status",goRest.getStatus());
        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValueMap);
    }

}
