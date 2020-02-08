package org.xpdojo.bank.account.repository;

import org.xpdojo.bank.account.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.xpdojo.bank.account.Account.anEmptyAccount;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<Long, Account> accounts = new HashMap<>();

    public Long createAccount() {
        Account account = anEmptyAccount(createAccountNumber());
        accounts.put(account.getAccountNumber(), account);
        return account.getAccountNumber();
    }

    private Long createAccountNumber() {
        return Math.abs(new Random().nextLong());
    }
}
