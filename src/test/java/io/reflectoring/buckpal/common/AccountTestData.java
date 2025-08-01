package io.reflectoring.buckpal.common;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.domain.model.ActivityWindow;
import io.reflectoring.buckpal.application.domain.model.Money;

public class AccountTestData {

    public static AccountBuilder defaultAccount() {
        return new AccountBuilder()
                .withAccountId(new Account.AccountId(42L))
                .withBaselineBalance(Money.of(999L))
                .withActivityWindow(new ActivityWindow(
                        ActivityTestData.defaultActivity().build(),
                        ActivityTestData.defaultActivity().build()));
    }

    public static class AccountBuilder {
        private Account.AccountId accountId;
        private Money baselineBalance;
        private ActivityWindow activityWindow;

        public AccountBuilder withAccountId(Account.AccountId accountId) {
            this.accountId = accountId;
            return this;
        }

        public AccountBuilder withBaselineBalance(Money baselineBalance) {
            this.baselineBalance = baselineBalance;
            return this;
        }

        public AccountBuilder withActivityWindow(ActivityWindow activityWindow) {
            this.activityWindow = activityWindow;
            return this;
        }

        public Account build() {
            return Account.withId(this.accountId, this.baselineBalance, this.activityWindow);
        }
    }
}
