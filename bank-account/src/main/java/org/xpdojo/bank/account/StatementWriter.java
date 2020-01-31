package org.xpdojo.bank.account;

import java.util.List;

public interface StatementWriter {

    void printBalanceOf(Money balance);

    void printFullStatementWith(List<Transaction> transactions);

}
