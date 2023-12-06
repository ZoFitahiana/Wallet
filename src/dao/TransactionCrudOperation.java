package dao;

import configuration.ConnectionDB;
import model.Transaction;

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
                System.out.println("Transaction : { transactionId = " + transactionId + " , amount = "+ amount + ", label = "+ label + ", type = "+ type + ", date = " +date + "}");
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
                Transaction transaction = new Transaction(transactionId,amount,label,type,date);
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
            String sql = "INSERT INTO transaction (transactionId, amount, label, type, date) " +
                    "VALUES (?, ?, ?, ?, ?) " +
                    "ON CONFLICT (transactionId) " +
                    "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                    "type = EXCLUDED.type, date = EXCLUDED.date";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getTransactionId());
            statement.setBigDecimal(2,toSave.getAmount());
            statement.setString(3,toSave.getLabel());
            statement.setString(4,toSave.getType());
            statement.setObject(5,toSave.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(toSave);
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> tosave) {
        getConnection();
        try{
            for (Transaction transaction :tosave){
                String sql = "INSERT INTO transaction (transactionId, amount, label, type, date) " +
                        "VALUES (?, ?, ?, ?, ?) " +
                        "ON CONFLICT (transactionId) " +
                        "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                        "type = EXCLUDED.type, date = EXCLUDED.date";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,transaction.getTransactionId());
                statement.setBigDecimal(2,transaction.getAmount());
                statement.setString(3,transaction.getLabel());
                statement.setString(4,transaction.getType());
                statement.setObject(5,transaction.getDate());
                statement.executeUpdate();
                findById(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tosave;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        getConnection();
        try{
            String sql = "update transaction set amount = ? , label = ? , type = ? , date = ? where  transactionId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1,toUpdate.getAmount());
            statement.setString(2,toUpdate.getLabel());
            statement.setString(3,toUpdate.getType());
            statement.setString(5,toUpdate.getTransactionId());
            statement.setObject(4,toUpdate.getDate());
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
