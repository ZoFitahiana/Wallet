package Repository;

import Configuration.ConnectionDB;
import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountCrudOperation implements CrudOperation<Account>{
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    @Override
    public List<Account> findAll() {
        getConnection();
        try {
            String sql = "select * from account";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String id = resultSet.getString("accountId");
                int balance = resultSet.getInt("balance");
                String currencyId = resultSet.getString("currencyId");
                System.out.println("Account : { accountId = "+id +" ,balance = " + balance +" ,currencyId = " + currencyId + "}" );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Account save(Account toSave) {
        getConnection();
        try{
            String sql = "insert into account (accountId,balance,currencyId) values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getAccountId());
            statement.setInt(2,toSave.getBalance());
            statement.setString(3,toSave.getCurrencyId());
            int row = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Save account successful");
        return null;
    }

    @Override
    public Account update(Account toUpdate) {
            getConnection();
            try {
                String sql = "update account set balance = ?, currencyId = ? where accountId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, toUpdate.getBalance());
                statement.setString(2, toUpdate.getCurrencyId());
                statement.setString(3, toUpdate.getAccountId());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new RuntimeException("Account with accountId " + toUpdate.getAccountId() + " not found");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Update account successful");
            return null; }
    }
