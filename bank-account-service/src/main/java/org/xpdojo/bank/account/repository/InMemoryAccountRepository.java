package org.xpdojo.bank.account.repository;

import com.google.common.collect.Lists;
import org.xpdojo.bank.account.Account;
import org.xpdojo.bank.account.domain.AccountSummary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static org.xpdojo.bank.account.Account.anEmptyAccount;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<Long, Account> accounts = new HashMap<>();

    public Long createAccount() {
        Account account = anEmptyAccount(createAccountNumber());
        accounts.put(account.getAccountNumber(), account);
        return account.getAccountNumber();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts.values().stream().collect(toList());
    }

    private Long createAccountNumber() {
        return Math.abs(new Random().nextLong());
    }
}
