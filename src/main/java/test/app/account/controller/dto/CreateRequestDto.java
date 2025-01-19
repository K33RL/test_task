package test.app.account.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateRequestDto {
  @Pattern(regexp = "[0-9]{10}$")
  private String phone;
  @Email
  private String email;
}
