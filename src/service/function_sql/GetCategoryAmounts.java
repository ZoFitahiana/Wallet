package service.function_sql;

import configuration.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GetCategoryAmounts {
    private static Connection connection;
    public static void getConnection() {
        ConnectionDB Db = new ConnectionDB();
        connection = Db.createConnection();
    }
    public  static  void getCategoryAmounts(String id , LocalDateTime startDate , LocalDateTime endDate) throws SQLException {
        getConnection();
        try {
            String sql = "select categories.name AS category_name,\n" +
                    "       COALESCE(SUM(CASE WHEN transaction.amount IS NOT NULL THEN transaction.amount ELSE 0 END), 0) AS total_amount from categories \n" +
                    "LEFT JOIN transaction ON categories.categoriesId = transaction.categoriesId\n" +
                    "LEFT JOIN account ON account.accountId = transaction.accountId \n" +
                    "WHERE account.accountId = ? AND transaction.date BETWEEN  ? AND ? \n" +
                    "GROUP BY categories.name , account.accountId  ";
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
