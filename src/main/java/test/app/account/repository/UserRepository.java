package test.app.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.app.account.entity.Email;
import test.app.account.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmails(List<Email> emails);
}
