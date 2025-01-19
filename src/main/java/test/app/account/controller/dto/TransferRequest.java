package test.app.account.controller.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferRequest {

  private Long accountTo;
  private BigDecimal amount;
}
