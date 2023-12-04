package Service;

import java.util.List;

import Model.Transaction;
import Repository.TransactionRepository;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(String accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }

    public Transaction getTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void createOrUpdateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}