package test;

import model.Account;
import dao.AccountCrudOperation;
import model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestOfAccount {
    public static void  TestAccount(){
        AccountCrudOperation accounts = new AccountCrudOperation();

        BigDecimal amount = new BigDecimal("100.45");
        BigDecimal balance = new BigDecimal("123.456");
        LocalDateTime date = LocalDateTime.now();

        // Find by id :
        System.out.println("Find by Id account : ");
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction1 = new Transaction("TXN004", amount, "Groceries", "DEBIT",date);
        Transaction transaction2 = new Transaction("TXN003", amount, "Dinner", "DEBIT",date);
        ListOfTransaction.add(transaction1);
        ListOfTransaction.add(transaction2);
        Account account = new Account("ACC001", "John Doe", balance,date,ListOfTransaction, "USD", "TXN001", "Bank");
        accounts.findById(account);

        //Save account :
        System.out.println("This account is saved  : ");
        Account account4 = new Account("ACC004", "RKT Zo", balance,date,ListOfTransaction, "AR", "TXN004", "Bank");
        accounts.save(account4);

        //save list of account :
        System.out.println("This list of account is saved : ");
        Account account5 = new Account("ACC005", "Rk Fabien", balance,date,ListOfTransaction, "USD", "TXN004", "Bank");
        Account setAccount4 = new Account("ACC004", "RKT Zo", balance,date,ListOfTransaction, "AR", "TXN002", "Cash");
        List<Account> listAccount = new ArrayList<>();
        listAccount.add(account5);
        listAccount.add(account4);
        accounts.saveAll(listAccount);

        // Update account :
        System.out.println("This account has been successfully updated :");
        Account setAccount5 = new Account("ACC005", "RZ Fabien", balance,date,ListOfTransaction, "AR", "TXN003", "Cash");
        accounts.update(setAccount5);

        // Find all account :
        System.out.println("List of account : ");
        accounts.findAll();


    }

}
