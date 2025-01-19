package test.app.account.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import test.app.account.controller.dto.UserFilter;
import test.app.account.entity.User;

@Repository
@RequiredArgsConstructor
public class UserFilterRepositoryImpl implements UserFilterRepository {

  private final EntityManager entityManager;

  @Override
  public List<User> findAllByFilterPageable(UserFilter filter, PageRequest pageRequest) {
    var criteriaBuilder = entityManager.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(User.class);
    var user = criteriaQuery.from(User.class);
    criteriaQuery.select(user);

    List<Predicate> predicates = new ArrayList<>() {
    };
    if (filter.getName() != null) {
      predicates.add(criteriaBuilder.like(user.get("name"), filter.getName()));
    }
    if (filter.getEmail() != null) {
      predicates.add(criteriaBuilder.equal(user.get("email"), filter.getEmail()));
    }
    if (filter.getPhone() != null) {
      predicates.add(criteriaBuilder.equal(user.get("phone"), filter.getPhone()));
    }
    if (filter.getDateOfBirth() != null) {
      predicates.add(criteriaBuilder.greaterThan(user.get("dateOfBirth"), filter.getDateOfBirth()));
    }

    criteriaQuery.where(predicates.toArray(Predicate[]::new));

    return entityManager.createQuery(criteriaQuery).setMaxResults(pageRequest.getPageSize())
        .setFirstResult(pageRequest.getPageNumber()).getResultList();
  }
}
