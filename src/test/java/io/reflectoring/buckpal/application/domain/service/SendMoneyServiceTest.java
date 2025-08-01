package io.reflectoring.buckpal.application.domain.service;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.domain.model.Money;
import io.reflectoring.buckpal.application.port.in.SendMoneyCommand;
import io.reflectoring.buckpal.application.port.out.AccountLock;
import io.reflectoring.buckpal.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.application.port.out.UpdateAccountStatePort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.*;

public class SendMoneyServiceTest {

    private final LoadAccountPort loadAccountPort =
            Mockito.mock(LoadAccountPort.class);

    private final AccountLock accountLock = Mockito.mock(AccountLock.class);

    private final UpdateAccountStatePort updateAccountStatePort = Mockito.mock(UpdateAccountStatePort.class);

    private final SendMoneyService sendMoneyService =
            new SendMoneyService(loadAccountPort, updateAccountStatePort, accountLock, moneyTransferProperties());

    @Test
    void transactionSucceeds() {
        Account sourceAccount = givenSourceAccount();
        Account targetAccount = givenTargetAccount();

        givenWithdrawWillSucceed(sourceAccount);
        givenDepositWillSucceed(targetAccount);

        Money money = Money.of(500L);

        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                money);

        boolean success = sendMoneyService.sendMoney(command);
        assertThat(success).isTrue();

        Account.AccountId sourceAccountId = sourceAccount.getId().get();
        Account.AccountId targetAccountId = targetAccount.getId().get();

        then(accountLock).should().lockAccount(sourceAccountId);



    }

    private void givenDepositWillSucceed(Account account) {
        given(account.deposit(any(Money.class), any(Account.AccountId.class)))
                .willReturn(true);
    }

    private void givenWithdrawWillFail(Account account) {
        given(account.withdraw(any(Money.class), any(Account.AccountId.class)))
                .willReturn(false);
    }


    private void givenWithdrawWillSucceed(Account account) {
        given(account.withdraw(any(Money.class), any(Account.AccountId.class)))
    }


    private Account givenTargetAccount() {
        return givenAnAccountWithId(new Account.AccountId(42L));
    }


    private Account givenSourceAccount() {
        return givenAnAccountWithId(new Account.AccountId(41L));
    }

    private Account givenAnAccountWithId(Account.AccountId id) {
        Account account = Mockito.mock(Account.class);
        given(account.getId())
                .willReturn(Optional.of(id));
        given(loadAccountPort.loadAccount(eq(account.getId().get()), any(LocalDateTime.class)))
                .willReturn(account);

        return account;
    }

    private MoneyTransferProperties moneyTransferProperties() {
        return new MoneyTransferProperties(Money.of(Long.MAX_VALUE));
    }
}
