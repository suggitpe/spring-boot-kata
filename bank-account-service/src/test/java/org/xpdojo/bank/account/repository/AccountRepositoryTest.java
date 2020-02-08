package org.xpdojo.bank.account.repository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest {

    @Test
    public void createsAccounts() {
        InMemoryAccountRepository respository = new InMemoryAccountRepository();
        assertThat(respository.createAccount()).isNotNegative();
    }

}