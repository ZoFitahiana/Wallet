package service.transaction;

import configuration.ConnectionDB;
import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveHistoryTransaction {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    public static void registerTransactionHistory(Transaction creditor,Transaction debtor){
        getConnection();
        try{
            String sql = "insert into TransferHistory (creditorTransactionId,debtorTransactionId,dateOfTransaction) values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,creditor.getTransactionId());
            statement.setString(2,debtor.getTransactionId());
            statement.setObject(3,creditor.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
