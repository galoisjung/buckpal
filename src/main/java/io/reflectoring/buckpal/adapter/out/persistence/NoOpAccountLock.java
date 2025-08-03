package io.reflectoring.buckpal.adapter.out.persistence;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.port.out.AccountLock;
import org.springframework.stereotype.Component;

@Component
public class NoOpAccountLock implements AccountLock {
    @Override
    public void releaseAccount(Account.AccountId accountId) {

    }

    @Override
    public void lockAccount(Account.AccountId accountId) {

    }
}
