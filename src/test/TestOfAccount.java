package test;

import model.Account;
import repository.AccountCrudOperation;

import java.math.BigDecimal;

public class TestOfAccount {
    public static void  TestAccount(){
        AccountCrudOperation accounts = new AccountCrudOperation();

        //Save account
        BigDecimal balance = new BigDecimal("123.456");
        Account account = new Account("123456UIO",balance,"USD");
        accounts.save(account);

        //Update account
        BigDecimal newBalance = new BigDecimal("200.00");
        Account SetAccount = new Account("123456UIO",newBalance,"EUR");
        accounts.update(SetAccount);

        //Find all account
        accounts.findAll();

    }

}
