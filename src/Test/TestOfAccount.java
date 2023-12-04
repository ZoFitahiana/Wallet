package Test;

import Model.Account;
import Repository.AccountCrudOperation;

public class TestOfAccount {
    public static void  TestAccount(){
        AccountCrudOperation accounts = new AccountCrudOperation();

        //Save account
        Account account = new Account("123456UIO",3000,"USD");
        accounts.save(account);

        //Update account
        Account SetAccount = new Account("123456UIO",3030,"EUR");
        accounts.update(SetAccount);

        //Find all account
        accounts.findAll();

    }

}
