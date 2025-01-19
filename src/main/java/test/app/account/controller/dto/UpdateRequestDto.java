package test.app.account.controller.dto;

import lombok.Data;
import test.app.account.entity.DataType;
import test.app.account.entity.OperationType;

@Data
public class UpdateRequestDto {

  private OperationType operation;
  private DataType type;
  private String value;
}
