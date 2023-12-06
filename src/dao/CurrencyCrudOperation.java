package dao;

import configuration.ConnectionDB;
import model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CurrencyCrudOperation implements CrudOperation<Currency>{
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }

    @Override
    public Currency findById(Currency id) {
        getConnection();
        try{
            String sql = "select * from currency where currencyId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id.getCurrencyId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String currencyId = resultSet.getString("currencyId");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                System.out.println("Currency : { "+ "CurrencyId = " + currencyId + ", name = "+name + ", code = " + code +"}");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Currency> findAll() {
        getConnection();
        try{
            String sql = "select * from currency";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String currencyId = resultSet.getString("currencyId");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                System.out.println("Currency : { "+ "CurrencyId = " + currencyId + ", name = "+name + ", code = " + code +"}");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Currency save(Currency toSave) {
        getConnection();
        try{
            String sql = "INSERT INTO currency (currencyId, name, code) \n" +
                    "VALUES (?, ?, ?)\n" +
                    "ON CONFLICT (currencyId) \n" +
                    "DO UPDATE SET name = EXCLUDED.name, code = EXCLUDED.code;\n";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getCurrencyId());
            statement.setString(2,toSave.getName());
            statement.setString(3,toSave.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(toSave);
    }

    @Override
    public List<Currency> saveAll(List<Currency> tosave) {
        getConnection();
       try{
            for (Currency currency : tosave){
                String sql = "INSERT INTO currency (currencyId, name, code) \n" +
                        "VALUES (?, ?, ?)\n" +
                        "ON CONFLICT (currencyId) \n" +
                        "DO UPDATE SET name = EXCLUDED.name, code = EXCLUDED.code;\n";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,currency.getCurrencyId());
                statement.setString(2,currency.getName());
                statement.setString(3,currency.getCode());
                statement.executeUpdate();
                findById(currency);
            }
        }catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return null;
    }

    @Override
    public Currency update(Currency toUpdate) {
        getConnection();
        try{
            String sql = "update currency set name = ? , code = ? where currencyId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,toUpdate.getName());
            statement.setString(2,toUpdate.getCode());
            statement.setString(3,toUpdate.getCurrencyId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Account with accountId " + toUpdate.getCurrencyId() + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(toUpdate);
    }
}
