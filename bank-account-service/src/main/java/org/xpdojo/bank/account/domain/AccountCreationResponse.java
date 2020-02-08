package org.xpdojo.bank.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AccountCreationResponse {

    private final Long accountNumber;

    public AccountCreationResponse(@JsonProperty("accountNumber") final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCreationResponse that = (AccountCreationResponse) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "AccountCreationResponse{" +
                "accountNumber=" + accountNumber +
                '}';
    }
}
