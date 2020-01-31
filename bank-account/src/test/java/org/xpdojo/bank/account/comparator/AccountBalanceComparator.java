package org.xpdojo.bank.account.comparator;

import org.xpdojo.bank.account.Account;

import java.util.Comparator;

public class AccountBalanceComparator implements Comparator<Account> {

    public static AccountBalanceComparator ofBalances() {
        return new AccountBalanceComparator();
    }

    @Override
    public int compare(Account lhs, Account rhs) {
        return lhs.balance().compareTo(rhs.balance());
    }


}
