package entities;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationInfo {
    private final String city;
    private final LocalDate firstMeetingDate;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final LocalDate secondMeetingDate;
}
