package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransferHistory {
    private int TransferHistoryId ;
    private String creditorTransactionId;
    private String debtorTransactionId;
    private  LocalDateTime dateOfTransaction;

    public TransferHistory(int transferHistoryId, String creditorTransactionId, String debtorTransactionId, LocalDateTime dateOfTransaction) {
        TransferHistoryId = transferHistoryId;
        this.creditorTransactionId = creditorTransactionId;
        this.debtorTransactionId = debtorTransactionId;
        this.dateOfTransaction = dateOfTransaction;
    }

    public int getTransferHistoryId() {
        return TransferHistoryId;
    }

    public void setTransferHistoryId(int transferHistoryId) {
        TransferHistoryId = transferHistoryId;
    }

    public String getCreditorTransactionId() {
        return creditorTransactionId;
    }

    public void setCreditorTransactionId(String creditorTransactionId) {
        this.creditorTransactionId = creditorTransactionId;
    }

    public String getDebtorTransactionId() {
        return debtorTransactionId;
    }

    public void setDebtorTransactionId(String debtorTransactionId) {
        this.debtorTransactionId = debtorTransactionId;
    }

    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TransferHistory that = (TransferHistory) object;
        return TransferHistoryId == that.TransferHistoryId && Objects.equals(creditorTransactionId, that.creditorTransactionId) && Objects.equals(debtorTransactionId, that.debtorTransactionId) && Objects.equals(dateOfTransaction, that.dateOfTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TransferHistoryId, creditorTransactionId, debtorTransactionId, dateOfTransaction);
    }

    @Override
    public String toString() {
        return "TransferHistory{" +
                "TransferHistoryId=" + TransferHistoryId +
                ", creditorTransactionId='" + creditorTransactionId + '\'' +
                ", debtorTransactionId='" + debtorTransactionId + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                '}';
    }
}
