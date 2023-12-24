package gorest;

import gorest.pojos.GoRest;
import net.datafaker.Faker;
import utils.RandomDataGenerator;
import utils.RandomDataTypeNames;

import java.util.HashMap;
import java.util.Map;

public class Payloads {
    public static String getCreateUserPayload(String name,String gender,String email,String status){
        String payload="{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"gender\": \""+gender+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"status\": \""+status+"\"\n" +
                "\n" +
                "}";
        return payload;
    }

    public static Map<String, Object> getCreateUserPayloadFromMap(String name, String gender, String email, String status){

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",name);
        map.put("gender",gender);
        map.put("email",email);
        map.put("status",status);
        return map;
    }

   /* public static Map<String, Object> getCreateUserPayloadFromMap(){

        Map<String,Object> map=new HashMap<String,Object>();
        Faker faker=new Faker();
        map.put("name",faker.name().name());
        map.put("gender","Male");
        map.put("email",faker.internet().emailAddress());
        map.put("status","Inactive");
        return map;
    }*/

    public static Map<String, Object> getCreateUserPayloadFromMap(){

        Map<String,Object> map=new HashMap<String,Object>();
        Faker faker=new Faker();
        map.put("name", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME));
        map.put("gender",RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.GENDER));
        map.put("email",RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL));
        map.put("status","Inactive");
        return map;
    }

    public static GoRest getCreateGorestPayloadFromPojo(){
         return GoRest
                 .builder()
                 .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME))
                 .gender(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.GENDER))
                 .email(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL))
                 .status(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.STATUS))
                 .build();
    }

}
