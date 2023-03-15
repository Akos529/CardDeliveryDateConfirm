package utils;

import com.github.javafaker.Faker;
import entities.RegistrationInfo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Locale;

@UtilityClass
public class DataGenerator {
    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale){
            Faker faker = new Faker(new Locale(locale));
            LocalDate firstMeetingDate = LocalDate.now().plusDays(5);
            LocalDate secondMeetingDate = LocalDate.now().plusDays(7);
            return new RegistrationInfo (
                    faker.address().city(),
                    firstMeetingDate,
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.phoneNumber().cellPhone(),
                    secondMeetingDate);
        }
    }
}
