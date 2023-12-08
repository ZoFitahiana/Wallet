package test;

import model.Account;
import dao.AccountCrudOperation;
import model.Transaction;
import service.HistoryOfBalance;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestOfAccount {
    public static void  TestAccount(){
        AccountCrudOperation accounts = new AccountCrudOperation();

        // Variable using of test account :

        BigDecimal amount = new BigDecimal("100.45");
        BigDecimal balance1 = new BigDecimal("100.456");
        BigDecimal balance2 = new BigDecimal("200.456");
        BigDecimal balance3 = new BigDecimal("300.456");
        BigDecimal balance4 = new BigDecimal("400.456");
        LocalDateTime date = LocalDateTime.now();
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction1 = new Transaction("TXN004", amount, "Groceries", "CREDIT",date,"ACC001");
        Transaction transaction2 = new Transaction("TXN003", amount, "Dinner", "DEBIT",date,"ACC001");
        Transaction transaction3 = new Transaction("TXN003", amount, "Dinner", "CREDIT",date,"ACC002");
        Transaction transaction4 = new Transaction("TXN003", amount, "Dinner", "DEBIT",date,"ACC002");
        Transaction transaction5 = new Transaction("TXN003", amount, "Dinner", "CREDIT",date,"ACC003");
        Transaction transaction6 = new Transaction("TXN003", amount, "Dinner", "DEBIT",date,"ACC003");
        ListOfTransaction.add(transaction1);
        ListOfTransaction.add(transaction2);
        ListOfTransaction.add(transaction3);
        ListOfTransaction.add(transaction4);
        ListOfTransaction.add(transaction5);
        ListOfTransaction.add(transaction6);

        //----------------------------------------Find by id------------------------------------ :
        System.out.println("Find by Id account : ");
        Account account = new Account("ACC002", "John Doe", balance2,date,ListOfTransaction, "EUR", "Bank");
        accounts.findById(account);

        //------------------------------------------Save account--------------------------------- :
        System.out.println("This account is saved  : ");
        Account Account5 = new Account("ACC005", "RKT Zo", balance2,date,ListOfTransaction, "EUR", "Bank");
        accounts.save(Account5);

        //------------------------------------------save list of account----------------------------:
        System.out.println("This list of account is saved : ");
        Account setAccount5 = new Account("ACC005", "Rk Fabien", balance2,date,ListOfTransaction, "AR", "Bank");
        Account account6 = new Account("ACC006", "RKT Zo", balance3,date,ListOfTransaction, "AR", "Cash");
        List<Account> listAccount = new ArrayList<>();
        listAccount.add(setAccount5);
        listAccount.add(account6);
        accounts.saveAll(listAccount);

        //--------------------------------------------Update account------------------------------- :
        System.out.println("This account has been successfully updated :");
        Account setAccount6 = new Account("ACC006", "Rakotonirina Zo", balance4,date,ListOfTransaction, "AR", "Cash");
        accounts.update(setAccount6);

        //--------------------------------------------Find all account------------------------------:
        System.out.println("List of account : ");
        accounts.findAll();

        //-------------------------------------------List balance & date of transition--------------;
        System.out.println("Date of transition & balance of account : ");
        AccountCrudOperation.balanceAndDateOfAccount(account);

        //--------------------------------------------List balance of account between start & end date-----------;
        System.out.println("List balance of account between start & end date : ");
        LocalDateTime start = LocalDateTime.of(2023, 12, 7, 10, 30);
        LocalDateTime end = LocalDateTime.now();
        Account account3 = new Account("ACC003", "Alice Johnson", balance3,date,ListOfTransaction, "EUR", "Mobile Money");
        HistoryOfBalance.historyOfBalanceOfAccount(start,end,account3);
    }

}
