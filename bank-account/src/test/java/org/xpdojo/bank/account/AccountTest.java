package org.xpdojo.bank.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.xpdojo.bank.account.Account.anAccountWith;
import static org.xpdojo.bank.account.Account.anEmptyAccount;
import static org.xpdojo.bank.account.Money.amountOf;
import static org.xpdojo.bank.account.comparator.AccountBalanceComparator.ofBalances;

@DisplayName("With an Account we can")
public class AccountTest {

    private static final Long ACCOUNT_NUMBER = 2468L;
    private static final Long ANOTHER_ACCOUNT_NUMBER = 13579L;

    @Mock
    StatementWriter statementWriter;

    @BeforeEach
    void setUpMocks() {
        initMocks(this);
    }

    @Test
    void compareTwoEmptyAccounts() {
        Account account = anEmptyAccount(ACCOUNT_NUMBER);
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anEmptyAccount(ANOTHER_ACCOUNT_NUMBER));
    }

    @Test
    void compareTwoAccountsHaveTheSameBalance() {
        Account account = anAccountWith(ACCOUNT_NUMBER, amountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(10.0d)));
    }

    @Test
    void depositAnAmountToIncreaseTheBalance() {
        Account account = anEmptyAccount(ACCOUNT_NUMBER);
        account.deposit(amountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(10.0d)));
    }

    @Test
    void withdrawAnAmountToDecreaseTheBalance() {
        Account account = anAccountWith(ACCOUNT_NUMBER, amountOf(20.0d));
        account.withdraw(amountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(10.0d)));
    }

    @Test
    void throwsExceptionIfYouTryToWithdrawMoreThanTheBalanceIfNoOverDraft() {
        Account account = anAccountWith(ACCOUNT_NUMBER, amountOf(20.0d));
        assertThrows(IllegalStateException.class, () -> account.withdraw(amountOf(30.0d)));
    }

    @Test
    void withdrawAnAmountMoreThanBalanceIfWithinOverdraft() {
        Account account = anAccountWith(ACCOUNT_NUMBER, amountOf(10.0d));
        account.setOverdraftFacility(amountOf(100.0d));
        account.withdraw(amountOf(60.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(-50.0d)));
    }

    @Test
    void throwsExceptionIfYouTryToWithdrawMoreThanYourOverdraft() {
        Account account = anAccountWith(ACCOUNT_NUMBER, amountOf(20.0d));
        account.setOverdraftFacility(amountOf(100.0d));
        assertThrows(IllegalStateException.class, () -> account.withdraw(amountOf(130.0d)));
    }

    @Test
    void transferMoneyFromOneAccountToAnother() {
        Account destinationAccount = anEmptyAccount(ACCOUNT_NUMBER);
        Account sourceAccount = anAccountWith(ACCOUNT_NUMBER, amountOf(50.0d));

        sourceAccount.transfer(amountOf(20.0d)).to(destinationAccount);

        assertThat(sourceAccount).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(30.0d)));
        assertThat(destinationAccount).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(20.0d)));
    }

    @Test
    void throwsExceptionIfYouTryToTransferMoreThanTheBalance() {
        Account sourceAccount = anAccountWith(ACCOUNT_NUMBER, amountOf(20.0d));
        assertThrows(IllegalStateException.class, () -> sourceAccount.transfer(amountOf(30.0d)).to(anEmptyAccount(ANOTHER_ACCOUNT_NUMBER)));
    }

    @Test
    void hasTheRightBalanceAfterANumberOfTransactions() {
        Account account = anEmptyAccount(ACCOUNT_NUMBER);
        account.deposit(amountOf(10.0d));
        account.deposit(amountOf(80.0d));
        account.deposit(amountOf(5.0d));
        account.withdraw(amountOf(15.0d));
        account.withdraw(amountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(ANOTHER_ACCOUNT_NUMBER, amountOf(70.0d)));
    }

    @Test
    void printOutAnAccountBalance() {
        Account account = anAccountWith(ACCOUNT_NUMBER, amountOf(30.0d));
        account.printBalanceStatementWith(statementWriter);
        verify(statementWriter).printBalanceOf(any(Money.class));
    }

    @Test
    void printOutAFullStatement() {
        Account account = anEmptyAccount(ACCOUNT_NUMBER);
        account.printFullStatementWith(statementWriter);
        verify(statementWriter).printFullStatementWith(anyList());
    }

}
