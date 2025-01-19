package test.app.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.app.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
