import test.TestOfAccount;
import test.TestOfCurrency;
import test.TestOfTransaction;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static test.test_function_sql.TestCategoryAmountNotUseFunction.testCategoryAmountNotUseFunction;
import static test.test_function_sql.TestGetCategoryAmounts.testGetCategoryAmounts;
import static test.test_function_sql.TestOfTotalAmountTransactionType.testOfTotalAmountTransactionType;
import static test.test_function_sql.TestTotalAmountTypeNotUseFunctionSQL.testOfTotalAmountTransactionByTypeNotUseFunction;

public class  Main {
    public static void main(String[] args) throws SQLException {

        //Test of transaction :
        TestOfTransaction.TestTransaction();

        //Test of account :
        TestOfAccount.TestAccount();



        //Test of currency :
         TestOfCurrency.TestCurrency();

        //Test od status of transaction :
        LocalDateTime start = LocalDateTime.of(2023, 12, 7, 10, 30);
        LocalDateTime end = LocalDateTime.now();
        //StatusOfTransaction.statusOfTransaction(start,end);


        // Test of total amount transaction by type not use function :
        testOfTotalAmountTransactionByTypeNotUseFunction();

        //Test category amount not use function :
        testCategoryAmountNotUseFunction();



        // Get total amount transaction type
        testOfTotalAmountTransactionType();

        // get category total amounts
        testGetCategoryAmounts();
}
}