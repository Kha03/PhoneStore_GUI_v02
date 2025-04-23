package iuh.fit.se.techgalaxy.frontend.customerV02.dto.response;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.Account;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.CustomerStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseV2 {
    String id;
    CustomerStatus userStatus;
    String name;
    String phone;
    Gender gender;
    String avatar;
    LocalDate dateOfBirth;
    private AccountResponse account;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountResponse{
        private String email;
    }
}
