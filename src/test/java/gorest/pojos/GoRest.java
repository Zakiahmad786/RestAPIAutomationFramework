package gorest.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import utils.RandomDataGenerator;
import utils.RandomDataTypeNames;

import java.util.Arrays;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRest {
    private String name= RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME);
    private String gender=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.GENDER);
   // private String gender= Stream.of("male","female","others").findAny();
    //private String gender= Arrays.asList("male","female","others").get(RandomDataGenerator.getRandomNumer(0,3));
    private String email=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL);
    private String status=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.STATUS);


}
