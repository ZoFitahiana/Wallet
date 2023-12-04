package Service;

import java.util.List;

import Model.Currency;
import Repository.CurrencyRepository;

public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(String currencyId) {
        return currencyRepository.findById(currencyId);
    }

    public void createOrUpdateCurrency(Currency currency) {
        currencyRepository.save(currency);
    }
}
