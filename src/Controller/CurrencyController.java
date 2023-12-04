package Controller;

import java.util.List;

import Model.Currency;
import Service.CurrencyService;

public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    public Currency getCurrencyById(String currencyId) {
        return currencyService.getCurrencyById(currencyId);
    }

    public void createOrUpdateCurrency(Currency currency) {
        currencyService.createOrUpdateCurrency(currency);
    }
}
