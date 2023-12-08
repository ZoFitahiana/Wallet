import service.StatusOfTransaction;
import test.TestOfAccount;
import test.TestOfCurrency;
import test.TestOfTransaction;

import java.time.LocalDateTime;

public class  Main {
    public static void main(String[] args) {

        //Test of transaction :
         TestOfTransaction.TestTransaction();

        //Test of account :
        TestOfAccount.TestAccount();



        //Test of currency :
          TestOfCurrency.TestCurrency();

    }
}