package software.ulpgc.moneycalculator.persistence;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> load();
}
