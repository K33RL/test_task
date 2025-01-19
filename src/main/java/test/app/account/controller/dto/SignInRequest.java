package test.app.account.controller.dto;

import lombok.Data;

@Data
public class SignInRequest {

  private String email;

  private String password;
}
