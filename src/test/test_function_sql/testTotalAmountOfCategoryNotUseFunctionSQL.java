package test.test_function_sql;

import configuration.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static service.function_sql.getTotalAmountOfCategory.TotalAmountOfCategoryAndName;

public class testTotalAmountOfCategoryNotUseFunctionSQL {
    public  static  void TestTotalAmountOfCategoryNotUseFunction() throws SQLException {
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2023,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,1,1,12,30,0);
        TotalAmountOfCategoryAndName(id,start,end);
    }
}
