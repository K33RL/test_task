package test.app.account.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.app.account.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

  Optional<Phone> findByPhone(String value);

}
