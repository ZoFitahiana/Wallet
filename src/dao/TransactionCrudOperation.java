package dao;

import configuration.ConnectionDB;
import model.Transaction;

import java.sql.*;
import java.util.List;

public class TransactionCrudOperation implements CrudOperation<Transaction>{
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }
    @Override
    public List<Transaction> findAll() {
        getConnection();
        try{
            String sql = "select * from transaction ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String transactionId = resultSet.getString("transactionId");
                int amount = resultSet.getInt("amount");
                String description = resultSet.getString("description");
                String accountId = resultSet.getString("accountId");
                System.out.println("Transaction : { transactionId = " + transactionId + " , amount = "+ amount + ", description = "+ description + ", accountId = "+ accountId + "}");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Transaction save(Transaction toSave) {
        getConnection();
        try{
            String sql = "insert into transaction (transactionId,amount,description,accountId) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getTransactionId());
            statement.setInt(2,toSave.getAmount());
            statement.setString(3,toSave.getDescription());
            statement.setString(4,toSave.getAccountId());
            int rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Save transaction successful");
        return null;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        getConnection();
        try{
            String sql = "update transaction set amount = ? , description = ? , accountId = ? where  transactionId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,toUpdate.getAmount());
            statement.setString(2,toUpdate.getDescription());
            statement.setString(3,toUpdate.getAccountId());
            statement.setString(4,toUpdate.getTransactionId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Account with accountId " + toUpdate.getTransactionId() + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Update account successful");
        return null;
    }
}
