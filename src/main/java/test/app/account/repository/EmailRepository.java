package test.app.account.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.app.account.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

  Optional<Email> findAllByEmail(String value);
}
