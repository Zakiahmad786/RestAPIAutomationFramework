package gorest;

import utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {

    public static Map<String,Object> dataFromJsonFile;

    static {
        String env=System.getProperty("env");
        try {
            dataFromJsonFile= JsonUtils.getJsonDataAsMap(env+"/gorestApiData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
