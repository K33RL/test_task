package test.app.account.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private Long userId;

  private BigDecimal balance;

}
