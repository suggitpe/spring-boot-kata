package org.xpdojo.bank.account;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static java.lang.Double.valueOf;

@JsonAutoDetect(fieldVisibility = ANY)
public class Money implements Comparable<Money> {

    private final double value;

    private Money(@JsonProperty("value") final double anAmount) {
        this.value = anAmount;
    }

    public static Money amountOf(final double anAmount) {
        return new Money(anAmount);
    }

    public Money add(final Money anAmount) {
        return amountOf(this.value + anAmount.value);
    }

    public Money less(final Money anAmount) {
        return amountOf(this.value - anAmount.value);
    }

    public boolean isLessThan(Money anAmount) {
        return value < anAmount.value;
    }

    Money negative() {
        return amountOf(value * -1);
    }

    public double value() {
        return value;
    }

    @Override
    public int compareTo(Money otherAmount) {
        return valueOf(value).compareTo(valueOf(otherAmount.value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
