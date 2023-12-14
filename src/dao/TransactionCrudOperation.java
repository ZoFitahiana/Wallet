package dao;

import configuration.ConnectionDB;
import model.Account;
import model.Transaction;
import service.TransactionAuthorizationManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TransactionCrudOperation implements CrudOperation<Transaction>{
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    @Override
    public Transaction findById(Transaction id) {
        getConnection();

        try{
            String sql = "select * from transaction where transactionId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id.getTransactionId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String transactionId = resultSet.getString("transactionId");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String label = resultSet.getString("label");
                String type = resultSet.getString("type");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String accountId = resultSet.getString("accountId");
                String categoriesId = resultSet.getString("categoriesId");
                System.out.println("Transaction : { transactionId = " + transactionId + " , amount = "+ amount + ", label = "+ label + ", type = "+ type + ", date = " +date + ", accountId = "+ accountId + ", categoriesId = "+ categoriesId + "}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null ;
    }

    @Override
    public List<Transaction> findAll() {
        getConnection();
        List<Transaction> transactionList = new ArrayList<>();
        try{
            String sql = "select * from transaction ";
            PreparedStatement statement = connection.prepareStatement(sql);
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
                transactionList.add(transaction);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Transaction transaction : transactionList){
            System.out.println(transaction);
        }
        return null;
    }

    @Override
    public Transaction save(Transaction toSave) {
        getConnection();
        try{
            String sql = "select * from account where accountId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getAccountId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String accountId = resultSet.getString("accountId");
                String name = resultSet.getString("name");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                AccountCrudOperation accountCrudOperation = new AccountCrudOperation();
                List<Transaction> transactionList = accountCrudOperation.getTransactionsForAccount(resultSet.getString("accountId"));
                String currencyId = resultSet.getString("currencyId");
                String type = resultSet.getString("type");
                Account account = new Account(accountId,name,balance,lastUpdate,transactionList,currencyId,type);
                TransactionAuthorizationManager.authorizeTransaction(account, toSave);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> tosave) {
        getConnection();
        try {
            for (Transaction transaction : tosave) {
                String sql = "select * from account where accountId = ? ";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, transaction.getAccountId());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String accountId = resultSet.getString("accountId");
                    String name = resultSet.getString("name");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                    AccountCrudOperation accountCrudOperation = new AccountCrudOperation();
                    List<Transaction> transactionList = accountCrudOperation.getTransactionsForAccount(resultSet.getString("accountId"));
                    String currencyId = resultSet.getString("currencyId");
                    String type = resultSet.getString("type");
                    Account account = new Account(accountId, name, balance, lastUpdate, transactionList, currencyId, type);
                    TransactionAuthorizationManager.authorizeTransaction(account, transaction);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public Transaction update(Transaction toUpdate) {
        getConnection();
        try{
            String sql = "update transaction set amount = ? , label = ? , type = ? , date = ? , accountId = ?,categoriesId = ? where  transactionId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1,toUpdate.getAmount());
            statement.setString(2,toUpdate.getLabel());
            statement.setString(3,toUpdate.getType());
            statement.setObject(4,toUpdate.getDate());
            statement.setString(5,toUpdate.getAccountId());
            statement.setString(6,toUpdate.getCategoriesId());
            statement.setString(7,toUpdate.getTransactionId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Account with accountId " + toUpdate.getTransactionId() + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(toUpdate);
    }
}
