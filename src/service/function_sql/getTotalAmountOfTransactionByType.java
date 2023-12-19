package service.function_sql;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static service.function_sql.getCountCredit.CountCredit;
import static service.function_sql.getCountDebit.CountDebit;

public class getTotalAmountOfTransactionByType {
    public static  void TotalAmountOfTransactionByType(String id , LocalDateTime startDate , LocalDateTime endDate){
        BigDecimal credit = CountCredit(id,startDate,endDate);
        BigDecimal debit = CountDebit(id,startDate,endDate);
        System.out.println("Total amount  credit : " + credit + ",  Total amount debit : " + debit);
    }
}
