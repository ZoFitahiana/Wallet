import service.transaction.StatusOfTransaction;
import test.TestOfAccount;
import test.TestOfCurrency;
import test.TestOfTransaction;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static test.test_function_sql.TestOfTotalAmountTransactionUseFunction.testOfTotalAmountTransactionByTypeNotUseFunction;
import static test.test_function_sql.TestOfTotalAmountsCategoryAndName.testCategoryNameAndAmountsFunction;
import static test.test_function_sql.testTotalAmountOfCategoryNotUseFunctionSQL.TestTotalAmountOfCategoryNotUseFunction;
import static test.test_function_sql.testTotalAmountTransactionByTypeNotUseFunctionSQL.TotalAmountTransactionByTypeNotUseFunctionSQL;

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
        StatusOfTransaction.statusOfTransaction(start,end);

        // Test of total amount transaction by type map the function sql :
        testOfTotalAmountTransactionByTypeNotUseFunction();

        //Test category total  amount and category name to  map the function :
        testCategoryNameAndAmountsFunction();


        //Test total transaction by type of account ;
        TotalAmountTransactionByTypeNotUseFunctionSQL();


        //Test total amount category not use function
        TestTotalAmountOfCategoryNotUseFunction();



}
}