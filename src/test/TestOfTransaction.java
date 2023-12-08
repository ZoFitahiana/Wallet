package test;

import model.Transaction;
import dao.TransactionCrudOperation;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestOfTransaction {
public static  void TestTransaction(){

    TransactionCrudOperation transactions = new TransactionCrudOperation();


    // Test save transaction :
    System.out.println("Save transaction : ");
    LocalDateTime date = LocalDateTime.now();
    BigDecimal amount = new BigDecimal("400.45");
    BigDecimal creditAmount = new BigDecimal("300.45");
    BigDecimal debitAmount = new BigDecimal("200.45");

    // Test balance < amount of transaction accepted for account type is  BANK
    System.out.println("Test of transaction for balance  < amount to the  account type is BANK : ");
    Transaction transaction = new Transaction("TXN006",debitAmount, "Dinner", "DEBIT",date,"ACC001");
    transactions.save(transaction);

  // Test balance < amout of transaction accpted for account type is  not BANK
    System.out.println("Test of transaction for balance  < amount and account type is not BANK : ");
    Transaction transactionS = new Transaction("TXN002", debitAmount, "Dinner", "DEBIT",date,"ACC003");
    transactions.save(transactionS);

    // Find transaction by id :
    System.out.println("Finde transaction by ID : ");
    transactions.findById(transaction);

    // Test save list of transaction :
    System.out.println("Save list of transaction :");
    List<Transaction> ListOfTransaction = new ArrayList<>();
    Transaction transaction4 = new Transaction("TXN004", creditAmount, "Groceries", "CREDIT",date,"ACC002");
    Transaction transaction5 = new Transaction("TXN005", debitAmount, "Dinner", "DEBIT",date,"ACC003");
    ListOfTransaction.add(transaction4);
    ListOfTransaction.add(transaction5);
    transactions.saveAll(ListOfTransaction);

    // Test update Transaction  :
    System.out.println("Update transaction :");
    Transaction setTransaction5 = new Transaction("TXN005", amount, "Bonus", "CREDIT",date,"ACC003");
    transactions.update(setTransaction5);

    // Test Find all :
    System.out.println("List of transaction :");
    transactions.findAll();
}
}
