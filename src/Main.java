import dao.generic.GenericDaoAccount;

import java.sql.SQLException;

public class  Main {
    public static void main(String[] args) throws SQLException {
/*
       //Test of transaction :
        TestOfTransaction.TestTransaction();



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


*/
        GenericDaoAccount accounts = new GenericDaoAccount();
        accounts.findAll();

    }
}