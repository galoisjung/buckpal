package io.reflectoring.buckpal.application.domain.model;

import lombok.Value;

public class Account {
    private AccountId id;

    private Money baselineBalance;

    private ActivityWindow activityWindow;

    public Money calculateBalance(){
        return Money.add(
                this.baselineBalance,

        )
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
