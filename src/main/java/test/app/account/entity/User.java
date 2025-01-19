package test.app.account.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  private Instant dateOfBirth;

  private String password;

  @OneToOne
  @JoinColumn(name = "id")
  private Account account;

  @OneToMany
  @JoinColumn(name = "id")
  private List<Phone> phones = new ArrayList<>();

  @OneToMany
  @JoinColumn(name = "id")
  private List<Email> emails = new ArrayList<>();
}
