package utils.function_sql;

import configuration.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class getTotalAmountOfCategoryUseFunction {

    public static void getCategoryNameAndAmountsUseFunction(String id , LocalDateTime startDate , LocalDateTime endDate){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM getCategoryAmounts(?,?,?)";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,startDate);
            statement.setObject(3,endDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String category = resultSet.getString("category_name");
                BigDecimal TotalAmount = resultSet.getBigDecimal("total_amount");
                System.out.println("Category_name : " + category + " , TotalAmount :  " + TotalAmount );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
