package org.xpdojo.bank.account.endpoints;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xpdojo.bank.account.domain.AccountCreationResponse;
import org.xpdojo.bank.account.domain.AccountSummary;
import org.xpdojo.bank.account.repository.AccountRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @ApiOperation(value = "Gets all accounts data", response = AccountSummary.class)
    @GetMapping("/accounts")
    public List<AccountSummary> getAllAccounts() {
        return repository.getAllAccounts().stream().map(acc -> new AccountSummary(acc)).collect(toList());
    }

}
