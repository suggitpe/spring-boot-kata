package org.xpdojo.bank.account.repository;

import org.xpdojo.bank.account.Account;

import java.util.List;

public interface AccountRepository {

    Long createAccount();

    List<Account> getAllAccounts();
}
