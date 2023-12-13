package service;

import configuration.ConnectionDB;
import dao.TransactionCrudOperation;
import model.Account;
import model.History;
import model.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static service.GetAmountCurrencyValueByDate.getAmount;

public class TransactionAuthorizationManager {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    public static void authorizeTransaction(Account account , Transaction transaction){
        BigDecimal price = getAmount(transaction.getDate());
        getConnection();

        if (transaction.getType().equals("DEBIT") && account.getBalance().compareTo(transaction.getAmount()) < 0 ) {
            if (account.getType().equals("Bank")) {
                BigDecimal newBalance;
                if (!account.getCurrencyId().equals("EUR")){
                    newBalance = account.getBalance().subtract((transaction.getAmount().multiply(price)));
                }else {
                    newBalance = account.getBalance().subtract(transaction.getAmount());
                }
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
                    TransactionCrudOperation transactions = new TransactionCrudOperation();
                    transactions.findById(transaction);

                    String historySql = "insert into history (accountId,transactionId,balance) values(?,?,?)";
                    PreparedStatement statementOfHistory = connection.prepareStatement(historySql);
                    statementOfHistory.setString(1,account.getAccountId());
                    statementOfHistory.setString(2,transaction.getTransactionId());
                    statementOfHistory.setBigDecimal(3,account.getBalance());
                    statementOfHistory.executeUpdate();

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
                  BigDecimal newBalance;
                  if (!account.getCurrencyId().equals("EUR")){
                      newBalance = account.getBalance().subtract((transaction.getAmount().multiply(price)));
                  }else {
                      newBalance = account.getBalance().subtract(transaction.getAmount());
                  }
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
                      TransactionCrudOperation transactions = new TransactionCrudOperation();
                      transactions.findById(transaction);


                      String historySql = "insert into history (accountId,transactionId,balance) values(?,?,?)";
                      PreparedStatement statementOfHistory = connection.prepareStatement(historySql);
                      statementOfHistory.setString(1,account.getAccountId());
                      statementOfHistory.setString(2,transaction.getTransactionId());
                      statementOfHistory.setBigDecimal(3,account.getBalance());
                      statementOfHistory.executeUpdate();


                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  break;
              case "CREDIT":
                  BigDecimal newBalances;
                  if (!account.getCurrencyId().equals("EUR")){
                      newBalances = account.getBalance().add((transaction.getAmount().multiply(price)));
                  }else {
                      newBalances = account.getBalance().add(transaction.getAmount());
                  }
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
                      TransactionCrudOperation transactions = new TransactionCrudOperation();
                      transactions.findById(transaction);


                      String historySql = "insert into history (accountId,transactionId,balance) values(?,?,?)";
                      PreparedStatement statementOfHistory = connection.prepareStatement(historySql);
                      statementOfHistory.setString(1,account.getAccountId());
                      statementOfHistory.setString(2,transaction.getTransactionId());
                      statementOfHistory.setBigDecimal(3,account.getBalance());
                      statementOfHistory.executeUpdate();


                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  break;
          }
        }

    }
}
