package test;

import model.Currency;
import repository.CurrencyCrudOperation;

public class TestOfCurrency {
    public static void TestCurrency(){
        CurrencyCrudOperation currencyCrudOperation = new CurrencyCrudOperation();

        // save Currency
        Currency currency = new Currency("AR","Ariary","AR");
        currencyCrudOperation.save(currency);

        // update currency
        Currency SetCurrency = new Currency("AR","ARIARY","AR");
        currencyCrudOperation.update(SetCurrency);

        // Find all currency
        currencyCrudOperation.findAll();


    }
}
