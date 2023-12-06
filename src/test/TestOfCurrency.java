package test;

import model.Currency;
import dao.CurrencyCrudOperation;

import java.util.ArrayList;
import java.util.List;

public class TestOfCurrency {
    public static void TestCurrency(){
        CurrencyCrudOperation currencyCrudOperation = new CurrencyCrudOperation();


        // save Currency
        System.out.println("Save currency :");
        Currency currency = new Currency("AR","Ariary","AR");
        currencyCrudOperation.save(currency);

        // find by id :
        System.out.println("Find by id of currency : ");
        currencyCrudOperation.findById(currency);

        // save list of currency ;
        System.out.println("Save list of currency :");
        List<Currency> currencies = new ArrayList<>();
        Currency currency1 = new Currency("EUR3", "Euro", "EUR");
        Currency currency2 = new Currency("GBP2", "British Pound", "GBP");
        currencies.add(currency1);
        currencies.add(currency2);
        currencyCrudOperation.saveAll(currencies);

        // update currency
        System.out.println("Update currency : ");
        Currency SetCurrency = new Currency("AR","ARIARY","AR");
        currencyCrudOperation.update(SetCurrency);

        // Find all currency
        System.out.println("List of currency :");
        currencyCrudOperation.findAll();


    }
}
