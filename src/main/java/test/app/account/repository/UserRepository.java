package test.app.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.app.account.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
