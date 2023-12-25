package gorest.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCellName;
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

    @ExcelCellName("name")
    private String name= RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME);

    @ExcelCellName("gender")
    private String gender=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.GENDER);
   // private String gender= Stream.of("male","female","others").findAny();
    //private String gender= Arrays.asList("male","female","others").get(RandomDataGenerator.getRandomNumer(0,3));

    @ExcelCellName("email")
    private String email=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL);

    @ExcelCellName("status")
    private String status=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.STATUS);

    //Here we will be facing one challenges if datas we need to produce random or following some pattern then it won't work

    @ExcelCellName("nameValue")
    @JsonIgnore
    private String nameValue;
}
