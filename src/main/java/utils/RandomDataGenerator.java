package utils;

import net.datafaker.Faker;

public class RandomDataGenerator {

    public static Faker faker=new Faker();

    public static String getRandomDataFor(RandomDataTypeNames dataTypeNames){
        switch (dataTypeNames){
            case FIRSTNAME:
                           return faker.name().firstName();
            case LASTNAME:
                         return faker.name().lastName();
            case FULLNAME:
                return faker.name().fullName();
            case EMAIL:
                return faker.internet().safeEmailAddress();
            case GENDER:
                return faker.gender().toString();
            case STATUS:
                        return "Inactive";
            default:
                return "Data is not present";
        }
    }
}
