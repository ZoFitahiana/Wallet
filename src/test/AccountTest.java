package test;

import dao.AccountCrudOperation;
import model.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import model.Transaction;
import org.junit.jupiter.api.Test;
import utils.GetAccountOfData;

public class AccountTest implements TestOperation{
    AccountCrudOperation accounts = new AccountCrudOperation();
    GetAccountOfData data = new GetAccountOfData();
    BigDecimal balance = new BigDecimal("35.50");
    BigDecimal amount = new BigDecimal("50.25");
    BigDecimal amount2 = new BigDecimal("1");
    LocalDateTime date = LocalDateTime.of(2023,12,6,18,45,0);
    LocalDateTime LastDateUpdate = LocalDateTime.of(2023,12, 6, 12,40, 0);
    LocalDateTime dateToo = LocalDateTime.of(2023,12,8 ,12,0,0);
    @Test
    public void TestFindById() {
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction = new Transaction("TXN002",amount,"Dinner","DEBIT",date,"ACC002","CAT2");
        ListOfTransaction.add(transaction);
        Account account = new Account("ACC002","Jane Smith" ,balance,LastDateUpdate,ListOfTransaction, "EUR", "Cash");
        Account result = accounts.findById(account);
        assertEquals(result,account,"Should return ACC002");
    }
    @Test
    @Override
    public void TestSave() {
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction = new Transaction("TXN002",amount,"Dinner","DEBIT",date,"ACC002","CAT2");
        Transaction transaction2 = new Transaction("TXN006",amount2,"Transfer to  Rk Fabien","DEBIT",dateToo,"ACC002","CAT1");
        ListOfTransaction.add(transaction);
        ListOfTransaction.add(transaction2);
        Account account = new Account("ACC010","Tsarist Rk" ,balance,LastDateUpdate,ListOfTransaction, "AR", "Cash");
        Account result = accounts.save(account);
        assertNotNull(result);
        assertEquals(accounts.findById(account),result,"Should result not null");
    }
@Test
    @Override
    public void TestSaveList() {
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction = new Transaction("TXN002",amount,"Dinner","DEBIT",date,"ACC002","CAT2");
        Transaction transaction2 = new Transaction("TXN006",amount2,"Transfer to  Rk Fabien","DEBIT",dateToo,"ACC002","CAT1");
        ListOfTransaction.add(transaction);
        ListOfTransaction.add(transaction2);
        Account account1 = new Account("ACC011","Fab rich" ,balance,LastDateUpdate,ListOfTransaction, "AR", "Cash");
        Account account2 = new Account("ACC012","John RL" ,balance,LastDateUpdate,ListOfTransaction, "EUR", "Cash");
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        List<Account> result = accounts.saveAll(accountList);
        assertNotNull(result,"Should account saved not null");
    }
@Test
    @Override
    public void TestUpdate() {
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction = new Transaction("TXN002",amount,"Dinner","DEBIT",date,"ACC002","CAT2");
        Transaction transaction2 = new Transaction("TXN006",amount2,"Transfer to  Rk Fabien","DEBIT",dateToo,"ACC002","CAT1");
        ListOfTransaction.add(transaction);
        ListOfTransaction.add(transaction2);
        Account account1 = new Account("ACC011","Fab rich" ,balance,LastDateUpdate,ListOfTransaction, "AR", "Cash");
        Account updated =  new Account("ACC011","Fabien" ,balance,LastDateUpdate,ListOfTransaction, "EUR", "Cash");
        Account result = accounts.update(updated);
        assertEquals(result,accounts.findById(account1));

    }
@Test
    @Override
    public void TestFindAll() {
        List<Account> AccountList = accounts.findAll();
        Long count = data.getAccountOfData("account");
        assertEquals(count,AccountList.size(),"Should account list size to equals count data of table ");
    }
}
