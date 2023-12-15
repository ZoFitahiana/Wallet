package test.test_function_sql;

import java.time.LocalDateTime;

import static service.function_sql.GetTotalAmountType.getTotalAmountType;

public class TestTotalAmountTypeNotUseFunctionSQL {
   public static  void testOfTotalAmountTransactionByTypeNotUseFunction(){
       System.out.println("Test of total amount transaction by type not use function : ");
       String id = "ACC003";
       LocalDateTime start = LocalDateTime.of(2023,12,1,12,30,0);
       LocalDateTime end = LocalDateTime.of(2024,1,1,12,30,0);
       getTotalAmountType(id,start,end);
   }
}
