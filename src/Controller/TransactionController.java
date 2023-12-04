package Controller;

import java.util.List;

import Model.Transaction;
import Service.TransactionService;

public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public List<Transaction> getAllTransactions(String accountId) {
        return transactionService.getAllTransactions(accountId);
    }

    public Transaction getTransactionById(String transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    public void createOrUpdateTransaction(Transaction transaction) {
        transactionService.createOrUpdateTransaction(transaction);
    }
}