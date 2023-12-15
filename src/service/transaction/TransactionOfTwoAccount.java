package service.transaction;

import configuration.ConnectionDB;
import dao.AccountCrudOperation;
import dao.TransactionCrudOperation;
import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


public class TransactionOfTwoAccount{
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    public static void transactionOfAccount(Transaction creditorAccount , Transaction debitorAccount) {
        if (creditorAccount.equals(debitorAccount)){
            System.out.println("Transaction invalid");

        }else{
            getConnection();
            try {
                String sql = "select * from account where accountId = ? ";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,creditorAccount.getAccountId());
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
                    if (account.getBalance().compareTo(creditorAccount.getAmount()) < 0){
                        if (account.getType().equals("Bank")){
                            TransactionCrudOperation transactions = new TransactionCrudOperation();
                            transactions.save(creditorAccount);
                            transactions.save(debitorAccount);
                            SaveHistoryTransaction.registerTransactionHistory(creditorAccount,debitorAccount);
                        }
                        else {
                                System.out.println("Echec transaction ! , your balance is insufficient");
                        }

                    }
                    else{
                        TransactionCrudOperation transactions = new TransactionCrudOperation();
                        transactions.save(creditorAccount);
                        transactions.save(debitorAccount);
                        SaveHistoryTransaction.registerTransactionHistory(creditorAccount,debitorAccount);
                    }
                   }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
