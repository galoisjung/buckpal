package io.reflectoring.buckpal.application.domain.service;

import io.reflectoring.buckpal.application.port.in.SendMoneyCommand;
import io.reflectoring.buckpal.application.port.in.SendMoneyUseCase;
import io.reflectoring.buckpal.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.application.port.out.UpdateAccountStatePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {
    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    private final MoneyTransferProperties moneyTransferProperties;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        return false;
    }

    private void checkThreshold(SendMoneyCommand command) {
        if (command.money().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())) {
            throw new ThresholdExceededException(moneyTransferProperties.getMaximumTransferThreshold(), command.money());
        }
    }
}
