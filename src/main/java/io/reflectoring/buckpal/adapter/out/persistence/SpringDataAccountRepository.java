package io.reflectoring.buckpal.adapter.out.persistence;

import io.reflectoring.buckpal.application.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<AccountJpaEntity, Long> {
}
