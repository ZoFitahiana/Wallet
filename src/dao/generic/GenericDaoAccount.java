package dao.generic;

import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GenericDaoAccount extends GenericCrudService<Account>{
    @Override
    String getTableName() {
        return "account";
    }

    @Override
    Account getResultset(Account entity) {
        String accountId = entity.getAccountId();
        String name = entity.getName();
        BigDecimal balance = entity.getBalance();
        LocalDateTime lastUpdate = entity.getLastUpdate();
        List<Transaction> transactionList = entity.getTransactionList();
        String currencyId = entity.getCurrencyId();
        String type = entity.getType();
        return new Account(accountId,name,balance,lastUpdate,transactionList,currencyId,type);
    }

    @Override
    Account setObject(Account entity) {
        return null;
    }
}
