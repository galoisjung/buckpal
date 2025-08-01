package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.application.port.out.LoadAccountPort;

public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand command);
}
