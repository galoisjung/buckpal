package io.reflectoring.buckpal.common;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.domain.model.Activity;
import io.reflectoring.buckpal.application.domain.model.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivityTestData {

    public static ActivityBuilder defaultActivity(){
        return new ActivityBuilder()
                .withOwnerAccount(new Account.AccountId(42L))
                .withSourceAccount(new Account.AccountId(42L))
                .withTargetAccount(new Account.AccountId(41L))
                .withTimestamp(LocalDateTime.now())
                .withMoney(Money.of(999L));
    }


    public static class ActivityBuilder {
        private Activity.ActivityId id;
        private Account.AccountId ownerAccountId;
        private Account.AccountId sourceAccountId;
        private Account.AccountId targetAccountId;
        private LocalDateTime timestamp;
        private Money money;

        public ActivityBuilder withId(Activity.ActivityId id) {
            this.id = id;
            return this;
        }

        public ActivityBuilder withOwnerAccount(Account.AccountId ownerAccountId) {
            this.ownerAccountId = ownerAccountId;
            return this;
        }

        public ActivityBuilder withSourceAccount(Account.AccountId sourceAccountId) {
            this.sourceAccountId = sourceAccountId;
            return this;
        }

        public ActivityBuilder withTargetAccount(Account.AccountId targetAccountId) {
            this.targetAccountId = targetAccountId;
            return this;
        }

        public ActivityBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ActivityBuilder withMoney(Money money) {
            this.money = money;
            return this;
        }

        public Activity build() {
            return new Activity(
                    this.id,
                    this.ownerAccountId,
                    this.sourceAccountId,
                    this.targetAccountId,
                    this.timestamp,
                    this.money);
        }
    }
}
