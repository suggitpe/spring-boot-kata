package org.xpdojo.bank.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.xpdojo.bank.account.Money.amountOf;

@DisplayName("With Money we can")
public class MoneyTest {

    @Test
    void addTwoAmountsOfMoneyTogether() {
        assertThat(amountOf(7.0d).add(amountOf(4.0d))).isEqualTo(amountOf(11.0d));
    }

    @Test
    void subtractAnAmountOfMoneyFromAnother() {
        assertThat(amountOf(23.0d).less(amountOf(12.0d))).isEqualTo(amountOf(11.0d));
    }

    @Test
    void compareIfOneAmountIsLessThanAnotherAmount() {
        assertThat(amountOf(12.0d).isLessThan(amountOf(13.0d))).isTrue();
        assertThat(amountOf(12.0d).isLessThan(amountOf(12.0d))).isFalse();
        assertThat(amountOf(12.0d).isLessThan(amountOf(11.0d))).isFalse();
    }

    @Test
    void compareIfTwoAmountsAreTheSame() {
        assertThat(amountOf(12.0d).equals(amountOf(13.0d))).isFalse();
        assertThat(amountOf(12.0d).equals(amountOf(12.0d)));
        assertThat(amountOf(12.0d).equals(amountOf(11.0d))).isFalse();
    }

}
