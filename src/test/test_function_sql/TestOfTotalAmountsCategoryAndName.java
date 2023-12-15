package test.test_function_sql;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static utils.function_sql.getTotalAmountOfCategoryUseFunction.getCategoryNameAndAmountsUseFunction;

public class TestOfTotalAmountsCategoryAndName {

    public  static  void  testCategoryNameAndAmountsFunction() throws SQLException {
        System.out.println(" Category and total amount   use function :");
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2023,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,1,1,12,30,0);
        getCategoryNameAndAmountsUseFunction(id ,start,end);
    }
}
