package org.xpdojo.bank.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCreationResponse {

    private final Long accountNumber;

    public AccountCreationResponse(@JsonProperty("accountNumber") final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }
}
