package org.xpdojo.bank.account.endpoints;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xpdojo.bank.account.Account;
import org.xpdojo.bank.account.domain.AccountCreationResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.xpdojo.bank.account.Account.anAccountWith;
import static org.xpdojo.bank.account.Money.amountOf;

@RestController
public class BankAccountEndpoint {

    @ApiOperation(value = "Creates accounts and responds with the identification of the account", response = AccountCreationResponse.class)
    @PostMapping(value = "/accounts")
    public AccountCreationResponse createAccount() {
        Account account = anAccountWith(12345L, amountOf(20.0));
        return new AccountCreationResponse(account.getAccountNumber());
    }

}
