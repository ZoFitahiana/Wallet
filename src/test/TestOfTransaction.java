package test;

import model.Transaction;
import dao.TransactionCrudOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static service.transaction.TransactionOfTwoAccount.transactionOfAccount;

public class TestOfTransaction {

    public static  void TestTransaction(){

    TransactionCrudOperation transactions = new TransactionCrudOperation();



    //----------------------------------------Test save transaction----------------------------:
    System.out.println("Save transaction : ");
    LocalDateTime date = LocalDateTime.now();
    BigDecimal amount = new BigDecimal("400.45");
    BigDecimal creditAmount = new BigDecimal("300.45");
    BigDecimal debitAmount = new BigDecimal("200");


    //----------------------------------------Test balance < amount of transaction accepted for account type is  BANK
    System.out.println("Test of transaction for balance  < amount to the  account type is BANK : ");
    Transaction transaction = new Transaction("TXN006",debitAmount, "Dinner", "DEBIT",date,"ACC001","CAT1");
    transactions.save(transaction);


    //----------------------------------------Find transaction by id---------------------------------:
    System.out.println("Finde transaction by ID : ");
    transactions.findById(transaction);

    //----------------------------------------Test save list of transaction-----------------------:
    System.out.println("Save list of transaction :");
    List<Transaction> ListOfTransaction = new ArrayList<>();
    Transaction transaction4 = new Transaction("TXN004", creditAmount, "Groceries", "CREDIT",date,"ACC003","CAT10");
    Transaction transaction5 = new Transaction("TXN008", debitAmount, "Dinner", "DEBIT",date,"ACC003","CAT2");
    ListOfTransaction.add(transaction4);
    ListOfTransaction.add(transaction5);
    transactions.saveAll(ListOfTransaction);

    //---------------------------------------Test update Transaction--------------------------------:
    System.out.println("Update transaction :");
    Transaction setTransaction5 = new Transaction("TXN004", amount, "Bonus", "CREDIT",date,"ACC003","CAT3");
    transactions.update(setTransaction5);

    //---------------------------------------Test Find all-------------------------------------:
    System.out.println("List of transaction :");
    transactions.findAll();

    //----------------------------------------Transaction of  account creditor and account debitor :
    System.out.println("Transaction of  account creditor and account debitor :");
    BigDecimal amountOfTransaction = new BigDecimal("1");
    LocalDateTime dateCurrencyValue = LocalDateTime.of(2023,12,8,12, 0, 0);

    Transaction creditorAccount = new Transaction("TXN006",amountOfTransaction,"Transfer to  Rk Fabien","DEBIT",dateCurrencyValue,"ACC002","CAT1");
    Transaction debitorAccount = new Transaction("TXN005",amountOfTransaction,"Have money by Rakotonirina Zo","CREDIT",dateCurrencyValue,"ACC003","CAT10");

    transactionOfAccount(creditorAccount,debitorAccount);
}


}
