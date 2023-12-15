package test.test_function_sql;

import java.time.LocalDateTime;

import static service.function_sql.getTotalAmountOfTransactionByType.TotalAmountOfTransactionByType;

public class testTotalAmountTransactionByTypeNotUseFunctionSQL {
    public static  void TotalAmountTransactionByTypeNotUseFunctionSQL(){
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2023,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,1,1,12,30,0);
        TotalAmountOfTransactionByType(id,start,end);
    }
}
