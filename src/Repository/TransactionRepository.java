package Repository;

import java.util.List;

import Model.Transaction;

public interface TransactionRepository {

    List<Transaction> findAllByAccountId(String accountId);

    Transaction findById(String transactionId);

    void save(Transaction transaction);
}
