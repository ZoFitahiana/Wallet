package service.function_sql;

import configuration.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GetTotalAmountType {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();

    }
    public static  void getTotalAmountType(String id , LocalDateTime startDate , LocalDateTime endDate){
        getConnection();
        try{
            String sql = "SELECT\n" +
                    "     COALESCE(SUM(CASE WHEN type = 'CREDIT' THEN amount ELSE 0 END), 0) AS TotalAmountCredit,\n" +
                    "     COALESCE(SUM(CASE WHEN type = 'DEBIT' THEN amount ELSE 0 END), 0) AS TotalAmountDebit \n" +
                    "from transaction WHERE accountId =  ? AND date BETWEEN ? AND ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,startDate);
            statement.setObject(3,endDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                BigDecimal Total_Amount_Credit = resultSet.getBigDecimal("TotalAmountCredit");
                BigDecimal Total_Amount_Debit = resultSet.getBigDecimal("TotalAmountDebit");
                System.out.println("Total_Amount_Credit : "+ Total_Amount_Credit +  " , Total_Amount_Debit : " + Total_Amount_Debit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
