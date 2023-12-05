package test;

import model.Transaction;
import dao.TransactionCrudOperation;

public class TestOfTransaction {
public static  void TestTransaction(){

    TransactionCrudOperation transactions = new TransactionCrudOperation();

    // save transaction
    Transaction transaction = new Transaction("txn004",60,"Purchase of goods","123456");
    transactions.save(transaction);

    // update transaction
    Transaction SetTransaction =  new Transaction("txn004",100,"Salary deposit","123456");
    transactions.update(SetTransaction);

    // Find all transaction
    transactions.findAll();

}
}
