package service.function_sql;

import configuration.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class getCountCredit {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }
    public static BigDecimal CountCredit(String id , LocalDateTime start , LocalDateTime end){
        getConnection();
        try{
            String sql = "select sum(transaction.amount) as Total_amount_credit from transaction inner join account ON transaction.accountId = account.accountId where account.accountId = ? AND  transaction.type = 'CREDIT'  AND transaction.date BETWEEN ? AND ? GROUP BY transaction.type ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,start);
            statement.setObject(3,end);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return resultSet.getBigDecimal("Total_amount_credit");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
