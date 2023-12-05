package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String accountId;
    private BigDecimal balance ;
    private String currencyId;

    public Account(String accountId, BigDecimal balance, String currency) {
        this.accountId = accountId;
        this.balance = balance;
        this.currencyId = currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return balance == account.balance && Objects.equals(accountId, account.accountId) && Objects.equals(currencyId, account.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, currencyId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", currency='" + currencyId + '\'' +
                '}';
    }
}
