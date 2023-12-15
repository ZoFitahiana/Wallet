package test.test_function_sql;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static service.function_sql.GetCategoryAmounts.getCategoryAmounts;

public class TestGetCategoryAmounts {

    public  static  void testGetCategoryAmounts() throws SQLException {
        System.out.println("Category and total amount : ");
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2023,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,1,1,12,30,0);
        getCategoryAmounts(id,start,end);
    }
}
