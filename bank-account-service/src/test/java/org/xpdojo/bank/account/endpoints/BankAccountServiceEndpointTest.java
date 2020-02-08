package org.xpdojo.bank.account.endpoints;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xpdojo.bank.account.domain.AccountCreationResponse;
import org.xpdojo.bank.account.domain.AccountSummary;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("With the bank account service we can")
public class BankAccountServiceEndpointTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void createAccounts(){
        AccountCreationResponse response = given()
                .header("Content-Type", "application/json")
                .when().log().all()
                .post("/accounts")
                .then().log().all()
                .extract().body().as(AccountCreationResponse.class);
        assertThat(response.getAccountNumber()).isNotNull();
    }

    @Test
    @Order(2)
    public void retrievesAccounts(){
        List<AccountSummary> accounts = given()
                .header("Content-Type", "application/json")
                .when().log().all()
                .get("/accounts")
                .then().log().all()
                .extract().body()
                .jsonPath().getList(".", AccountSummary.class);
        assertThat(accounts.size()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void retrievesAccountById(){
//        AccountSummary account = given()
//                .header("Content-Type", "application/json")
//                .when().log().all()
//                .get("accounts/30001234")
//                .then().log().all()
//                .extract().body().as(AccountSummary.class);
//        assertThat(account.getAccountNumber()).isEqualTo(30001234L);
    }

}
