package org.xpdojo.bank.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.xpdojo.bank.account.Money.amountOf;
import static org.xpdojo.bank.account.Transaction.*;

public class Account {

    private final Long accountNumber;
    private String description = "";
    private Money overdraftFacility = amountOf(0.0d);
    private final List<Transaction> transactions = new ArrayList<>();

    public static Account anEmptyAccount(final Long accountNumber) {
        return new Account(accountNumber);
    }

    public static Account anAccountWith(final Long accountNumber, final Money amount) {
        return new Account(accountNumber, amount);
    }

    private Account(final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    private Account(final Long accountNumber, final Money anAmount) {
        this(accountNumber);
        transactions.add(anOpeningBalanceOf(anAmount, LocalDateTime.now()));
    }

    public Money balance() {
        if (transactions.size() == 0) {
            return amountOf(0.0d);
        }
        return transactions.stream().map(transaction -> transaction.balanceImpact()).reduce(Money::add).get();
    }

    public void applyTransaction(final Transaction transaction) {
        transactions.add(transaction);
    }

    public void deposit(final Money amount) {
        deposit(amount, LocalDateTime.now());
    }

    public void deposit(final Money anAmount, LocalDateTime transactionTime) {
        transactions.add(aDepositOf(anAmount, transactionTime));
    }

    public void withdraw(final Money amount) {
        withdraw(amount, LocalDateTime.now());
    }

    public void withdraw(final Money anAmount, LocalDateTime transactionTime) {
        if (balance().add(overdraftFacility).isLessThan(anAmount)) {
            throw new IllegalStateException("You cannot withdraw more than your overdraft will allow");
        }
        transactions.add(aWithDrawlOf(anAmount, transactionTime));
    }

    public TransferMechanism transfer(final Money amount) {
        return transfer(amount, LocalDateTime.now());
    }

    public TransferMechanism transfer(final Money amount, LocalDateTime transactionTime) {
        return new TransferMechanism(amount, transactionTime);
    }

    class TransferMechanism {

        private final Money transactionAmount;
        private final LocalDateTime transactionTime;

        public TransferMechanism(Money transferAmount, LocalDateTime transferDateTime) {
            this.transactionAmount = transferAmount;
            this.transactionTime = transferDateTime;
        }

        public void to(Account destinationAccount) {
            destinationAccount.deposit(transactionAmount, transactionTime);
            withdraw(transactionAmount, transactionTime);
        }

    }

    public void printBalanceStatementWith(StatementWriter writer) {
        writer.printBalanceOf(balance());
    }

    public void printFullStatementWith(StatementWriter writer) {
        writer.printFullStatementWith(transactions);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getOverdraftFacility() {
        return overdraftFacility;
    }

    public void setOverdraftFacility(Money overdraftFacility) {
        this.overdraftFacility = overdraftFacility;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(description, account.description) &&
                Objects.equals(overdraftFacility, account.overdraftFacility) &&
                Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, description, overdraftFacility, transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", description='" + description + '\'' +
                ", overdraftFacility=" + overdraftFacility +
                ", transactions=" + transactions +
                '}';
    }


}
