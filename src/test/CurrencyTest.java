package test;

import dao.CurrencyCrudOperation;
import model.Currency;
import org.junit.jupiter.api.Test;
import utils.GetAccountOfData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CurrencyTest implements TestOperation{
    CurrencyCrudOperation currencyCrudOperation = new CurrencyCrudOperation();
    GetAccountOfData data = new GetAccountOfData();

    @Test
    @Override
    public void TestFindById() {
        Currency currency = new Currency("AR","ARIARY","AR");
        Currency result = currencyCrudOperation.findById(currency);
        assertEquals(result,currency,"Should return currency have id 'AR' ");
    }

    @Test
    @Override
    public void TestSave() {
        Currency currency = new Currency("EUR_","EURO","EUR");
        Currency result = currencyCrudOperation.save(currency);
        assertNotNull(currencyCrudOperation.findById(result),"should not null data ");
        assertEquals(result,currency,"Should equals result and currency id EUR_");
    }

    @Test
    @Override
    public void TestSaveList() {
        List<Currency> currencieList = new ArrayList<>();
        Currency currency1 = new Currency("EUR3", "Euro", "EUR");
        Currency currency2 = new Currency("GBP2", "British Pound", "GBP");
        currencieList.add(currency1);
        currencieList.add(currency2);
        List<Currency> result = currencyCrudOperation.saveAll(currencieList);
        assertNotNull(result,"Should currency list   saved not null");
    }
@Test
    @Override
    public void TestUpdate() {
        Currency SetCurrency = new Currency("AR","ARIARY","AR");
        Currency result = currencyCrudOperation.update(SetCurrency);
        assertNotNull(currencyCrudOperation.findById(result));
        assertEquals(SetCurrency,currencyCrudOperation.findById(result));
    }

    @Test
    @Override
    public void TestFindAll() {
        List<Currency> currencieList = currencyCrudOperation.findAll();
        Long DataSize = data.getAccountOfData("currency");
        assertEquals(currencieList.size(),DataSize,"Shoul equals curency list size and data in database size of model");
    }
}
