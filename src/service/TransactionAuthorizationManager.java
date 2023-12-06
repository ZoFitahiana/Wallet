package service;

import configuration.ConnectionDB;
import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionAuthorizationManager {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    public static void authorizeTransaction(Account account , Transaction transaction){
        getConnection();
        if (transaction.getType().equals("DEBIT") && account.getBalance().compareTo(transaction.getAmount()) < 0 ) {
            if (account.getType().equals("Bank")) {
                BigDecimal newBalance = account.getBalance().subtract(transaction.getAmount());
                try {
                    String sql = "INSERT INTO transaction (transactionId, amount, label, type, date,accountId) " +
                            "VALUES (?, ?, ?, ?, ?,?) " +
                            "ON CONFLICT (transactionId) " +
                            "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                            "type = EXCLUDED.type, date = EXCLUDED.date , accountId = EXCLUDED.accountId";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, transaction.getTransactionId());
                    statement.setBigDecimal(2, transaction.getAmount());
                    statement.setString(3, transaction.getLabel());
                    statement.setString(4, transaction.getType());
                    statement.setObject(5, transaction.getDate());
                    statement.setString(6, transaction.getAccountId());
                    statement.executeUpdate();
                    String SQL = "update account set balance = ? WHERE accountId = ?";
                    PreparedStatement STATEMENT = connection.prepareStatement(SQL);
                    STATEMENT.setBigDecimal(1,newBalance);
                    STATEMENT.setString(2, account.getAccountId());
                    STATEMENT.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else{
                System.out.println("Your balance is insufficient");
            }
        }
        else{
          switch (transaction.getType()){
              case "DEBIT" :
                  BigDecimal newBalance = account.getBalance().subtract(transaction.getAmount());
                  try {
                      String sql = "INSERT INTO transaction (transactionId, amount, label, type, date,accountId) " +
                              "VALUES (?, ?, ?, ?, ?,?) " +
                              "ON CONFLICT (transactionId) " +
                              "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                              "type = EXCLUDED.type, date = EXCLUDED.date , accountId = EXCLUDED.accountId";
                      PreparedStatement statement = connection.prepareStatement(sql);
                      statement.setString(1, transaction.getTransactionId());
                      statement.setBigDecimal(2, transaction.getAmount());
                      statement.setString(3, transaction.getLabel());
                      statement.setString(4, transaction.getType());
                      statement.setObject(5, transaction.getDate());
                      statement.setString(6, transaction.getAccountId());
                      statement.executeUpdate();
                      String SQL = "update account set balance = ? WHERE accountId = ?";
                      PreparedStatement STATEMENT = connection.prepareStatement(SQL);
                      STATEMENT.setBigDecimal(1,newBalance);
                      STATEMENT.setString(2, account.getAccountId());
                      STATEMENT.executeUpdate();
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  break;
              case "CREDIT":
                  BigDecimal newBalances = account.getBalance().add(transaction.getAmount());
                  try {
                      String sql = "INSERT INTO transaction (transactionId, amount, label, type, date,accountId) " +
                              "VALUES (?, ?, ?, ?, ?,?) " +
                              "ON CONFLICT (transactionId) " +
                              "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                              "type = EXCLUDED.type, date = EXCLUDED.date , accountId = EXCLUDED.accountId";
                      PreparedStatement statement = connection.prepareStatement(sql);
                      statement.setString(1, transaction.getTransactionId());
                      statement.setBigDecimal(2, transaction.getAmount());
                      statement.setString(3, transaction.getLabel());
                      statement.setString(4, transaction.getType());
                      statement.setObject(5, transaction.getDate());
                      statement.setString(6, transaction.getAccountId());
                      statement.executeUpdate();
                      String SQL = "update account set balance = ? WHERE accountId = ?";
                      PreparedStatement STATEMENT = connection.prepareStatement(SQL);
                      STATEMENT.setBigDecimal(1,newBalances);
                      STATEMENT.setString(2, account.getAccountId());
                      STATEMENT.executeUpdate();
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  break;
          }
        }

    }
}
