package service;

import configuration.ConnectionDB;
import model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HistoryOfBalance {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }
    public static void historyOfBalanceOfAccount(LocalDateTime HistoryStartDate, LocalDateTime historyEndDate, Account account){
        getConnection();
        try{
            String sql = "select history.balance from history \n" +
                    "inner join account ON history.accountId = account.accountId \n" +
                    "inner join transaction ON history.transactionId = transaction.transactionId \n" +
                    "where account.accountId = ? AND transaction.date BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,account.getAccountId());
            statement.setObject(2,HistoryStartDate);
            statement.setObject(3,historyEndDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                BigDecimal balance = resultSet.getBigDecimal("balance");
                System.out.println(balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
