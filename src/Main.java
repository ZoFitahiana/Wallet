import Test.TestOfAccount;
import Test.TestOfCurrency;
import Test.TestOfTransaction;

public class  Main {
    public static void main(String[] args) {

        //Test of account :
        TestOfAccount.TestAccount();

        //Test of transaction :
        TestOfTransaction.TestTransaction();

        //Test of currency :
        TestOfCurrency.TestCurrency();

    }
}