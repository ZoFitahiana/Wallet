package test.test_function_sql;

import java.time.LocalDateTime;

import static utils.function_sql.getTotalAmountOfTransactionByType.TotalAmountOfTransactionByType;

public class testTotalAmountTransactionByTypeNotUseFunctionSQL {
    public static  void TotalAmountTransactionByTypeNotUseFunctionSQL(){
        System.out.println("Total amount transaction by type of account :");
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2022,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,4,1,12,30,0);
        TotalAmountOfTransactionByType(id,start,end);
    }
}
