package software.ulpgc.moneycalculator.persistence;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to) throws IOException;
}
