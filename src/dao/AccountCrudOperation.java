package dao;

import configuration.ConnectionDB;
import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperation implements CrudOperation<Account>{
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    @Override
    public Account findById(Account id) {
        getConnection();
        try{
            String sql = "select * from  account   where accountId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id.getAccountId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String accountId = resultSet.getString("accountId");
                String name = resultSet.getString("name");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                List<Transaction> transactionList = getTransactionsForAccount(resultSet.getString("accountId"));
                String currencyId = resultSet.getString("currencyId");
                String type = resultSet.getString("type");
                System.out.println("Account { accountId = "+ accountId+", name = "+name+", balance = "+balance+", lastUpdate = "+ lastUpdate +", transactionList = "+ transactionList+", currencyId = "+ currencyId + ", type = "+ type+" }; ");}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
     public List<Transaction> getTransactionsForAccount(String id) {
        getConnection();
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM transaction INNER JOIN account ON transaction.accountId = account.accountId WHERE transaction.accountId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String transactionId = resultSet.getString("transactionId");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String label = resultSet.getString("label");
                String type = resultSet.getString("type");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String accountId = resultSet.getString("accountId");
                String categoriesId = resultSet.getString("categoriesId");
                Transaction transaction = new Transaction(transactionId,amount,label,type,date,accountId,categoriesId);
                transactions.add(transaction);
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Account> findAll() {
        getConnection();
        try {
            String sql = "select * from account";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String accountId = resultSet.getString("accountId");
                String name = resultSet.getString("name");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                List<Transaction> transactionList = getTransactionsForAccount(resultSet.getString("accountId"));
                String currencyId = resultSet.getString("currencyId");
                String type = resultSet.getString("type");
                System.out.println("Account { accountId = "+ accountId+", name = "+name+", balance = "+balance+", lastUpdate = "+ lastUpdate +", transactionList = "+ transactionList+", currencyId = "+ currencyId + ", type = "+ type+" }; ");}
           } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Account save(Account toSave) {
        getConnection();
        try{
            String sql = "INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, type) VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (accountId) DO UPDATE SET name = EXCLUDED.name, balance = EXCLUDED.balance, lastUpdate = EXCLUDED.lastUpdate, currencyId = EXCLUDED.currencyId, type = EXCLUDED.type";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getAccountId());
            statement.setString(2,toSave.getName());
            statement.setBigDecimal(3,toSave.getBalance());
            statement.setObject(4,toSave.getLastUpdate());
            statement.setString(5,toSave.getCurrencyId());
            statement.setString(6,toSave.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(toSave);
    }

    @Override
    public List<Account> saveAll(List<Account> tosave) {
        getConnection();
        try {
            for (Account account : tosave){
                String sql = "INSERT INTO account (accountId, name, balance, lastUpdate, currencyId,type) VALUES ( ?, ?, ?, ?, ?, ?) ON CONFLICT (accountId) DO UPDATE SET name = EXCLUDED.name, balance = EXCLUDED.balance, lastUpdate = EXCLUDED.lastUpdate, currencyId = EXCLUDED.currencyId,type = EXCLUDED.type";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,account.getAccountId());
                statement.setString(2,account.getName());
                statement.setBigDecimal(3,account.getBalance());
                statement.setObject(4,account.getLastUpdate());
                statement.setString(5,account.getCurrencyId());
                statement.setString(6,account.getType());
                statement.executeUpdate();
                findById(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Account update(Account toUpdate) {
            getConnection();
            try {
                String sql = "update account set name = ? , balance = ? , lastUpdate = ? ,currencyId = ? ,type = ?  where accountId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,toUpdate.getName());
                statement.setBigDecimal(2,toUpdate.getBalance());
                statement.setObject(3,toUpdate.getLastUpdate());
                statement.setString(4,toUpdate.getCurrencyId());
                statement.setString(5,toUpdate.getType());
                statement.setString(6,toUpdate.getAccountId());
                statement.executeUpdate();

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new RuntimeException("Account with accountId " + toUpdate.getAccountId() + " not found");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return findById(toUpdate); }

    public static void balanceAndDateOfAccount(Account account){
        getConnection();
        try {
            String sql = "select transaction.date , history.balance from history \n" +
                    "inner join account ON history.accountId = account.accountId \n" +
                    "inner join transaction ON history.transactionId = transaction.transactionId \n" +
                    "where account.accountId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getAccountId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                BigDecimal balance = resultSet.getBigDecimal("balance");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                System.out.println("{ Date & hour : " + date + " , Balance : " + balance + " } ;");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }
