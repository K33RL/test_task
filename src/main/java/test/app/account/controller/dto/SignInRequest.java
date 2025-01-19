package test.app.account.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные необходимые для аутентификации пользователя")
public class SignInRequest {

  @Schema(description = "Email", example = "email@email.com")
  private String email;
  @Schema(description = "Password", example = "q1w2e3")
  private String password;
}
