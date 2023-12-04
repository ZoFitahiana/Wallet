package Repository;

import java.util.List;

import Model.Currency;


public interface CurrencyRepository {
        List<Currency> findAll();
    
        Currency findById(String currencyId);
    
        void save(Currency currency);
}
