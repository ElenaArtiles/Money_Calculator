package software.ulpgc.moneycalculator.architecture.persistence;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.IOException;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to) throws IOException;
}
