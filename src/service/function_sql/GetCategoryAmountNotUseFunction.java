package service.function_sql;

import configuration.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GetCategoryAmountNotUseFunction {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }
    public static void getCategoryAmountNotUseFunction(String id , LocalDateTime startDate , LocalDateTime endDate){
        getConnection();
        try {
            String sql = "SELECT * FROM getCategoryAmounts(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,startDate);
            statement.setObject(3,endDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String category = resultSet.getString("category_name");
                BigDecimal TotalAmount = resultSet.getBigDecimal("total_amount");
                System.out.println("Category_name : " + category + " , TotalAmount :  " + TotalAmount );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

