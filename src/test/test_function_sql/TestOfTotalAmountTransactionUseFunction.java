package test.test_function_sql;

import java.time.LocalDateTime;

import static utils.function_sql.getTotalAmountTransactionTypeUseFunction.TotalAmountByTypeOfTransaction.getTotalAmountTypeNotUseFunctionSQL;

public class TestOfTotalAmountTransactionUseFunction {
    public static  void testOfTotalAmountTransactionByTypeNotUseFunction(){
        System.out.println("Test of total amount transaction by type  use function : ");
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2022,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,4,1,12,30,0);
        getTotalAmountTypeNotUseFunctionSQL(id,start,end);
    }
}
