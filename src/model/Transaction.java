package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private String transactionId ;
    private BigDecimal amount;
    private String label;
    private String type;
    private LocalDateTime date;
    private String accountId ;

    public Transaction(String transactionId, BigDecimal amount, String label, String type, LocalDateTime date, String accountId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.label = label;
        this.type = type;
        this.date = date;
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Transaction that = (Transaction) object;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(amount, that.amount) && Objects.equals(label, that.label) && Objects.equals(type, that.type) && Objects.equals(date, that.date) && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, label, type, date, accountId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
