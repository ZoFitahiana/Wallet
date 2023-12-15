package test;

import java.time.LocalDateTime;

import static service.GetTotalAmountType.getTotalAmountType;

public class TestOfTotalAmountTransactionType {
    public static  void testOfTotalAmountTransactionType(){
        String id = "ACC003";
        LocalDateTime start = LocalDateTime.of(2023,12,1,12,30,0);
        LocalDateTime end = LocalDateTime.of(2024,1,1,12,30,0);

        getTotalAmountType(id,start,end);

    }

}
