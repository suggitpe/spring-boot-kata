package org.xpdojo.bank.account.endpoints;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xpdojo.bank.account.domain.AccountCreationResponse;
import org.xpdojo.bank.account.repository.AccountRepository;

@RestController
public class BankAccountEndpoint {

    private final AccountRepository repository;

    public BankAccountEndpoint(AccountRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Creates accounts and responds with the identification of the account", response = AccountCreationResponse.class)
    @PostMapping(value = "/accounts")
    public AccountCreationResponse createAccount() {
        return new AccountCreationResponse(repository.createAccount());
    }

}
