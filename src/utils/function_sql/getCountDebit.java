package utils.function_sql;

import configuration.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class getCountDebit {
    public static BigDecimal CountDebit(String id , LocalDateTime start , LocalDateTime end){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BigDecimal result = null;
        try{
            String sql = "select sum(transaction.amount) as Total_amount_debit from transaction left join account ON transaction.accountId = account.accountId where account.accountId = ? AND transaction.type = 'DEBIT' AND transaction.date BETWEEN ? AND ?  ";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,start);
            statement.setObject(3,end);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getBigDecimal("Total_amount_debit");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null ){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
