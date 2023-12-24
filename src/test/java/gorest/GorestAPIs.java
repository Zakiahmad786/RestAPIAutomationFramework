package gorest;

import gorest.pojos.GoRest;
import io.restassured.response.Response;
import restUtils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class GorestAPIs {

    public Response createUser(Map<String,Object> createGorestPayload){
        String endPoint=(String)Base.dataFromJsonFile.get("createGorestEndpoint");
        return RestUtils.performPost(endPoint,createGorestPayload,new HashMap<>());
    }

    public Response createUser(GoRest createGorestPayload){
        String endPoint=(String)Base.dataFromJsonFile.get("createGorestEndpoint");
        return RestUtils.performPost(endPoint,createGorestPayload,new HashMap<>());
    }
}
