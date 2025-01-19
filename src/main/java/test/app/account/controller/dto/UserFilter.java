package test.app.account.controller.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserFilter {

  private LocalDate dateOfBirth;
  private String phone;
  private String email;
  private String name;
}
