package test.test_function_sql;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static utils.function_sql.getTotalAmountOfCategory.TotalAmountOfCategoryAndName;

public class testTotalAmountOfCategoryNotUseFunctionSQL {
    public  static  void TestTotalAmountOfCategoryNotUseFunction() throws SQLException {
        System.out.println("Total amount category by type and category name : ");
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2022,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,2,1,12,30,0);
        TotalAmountOfCategoryAndName(id,start,end);
    }
}
