package software.ulpgc.moneycalculator.fixerws;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.moneycalculator.persistence.ExchangeRateLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FixerExchangeRateLoader implements ExchangeRateLoader {
    private final LocalDate date;

    public FixerExchangeRateLoader(LocalDate date) {
        this.date = date;
    }

    @Override
    public ExchangeRate load(Currency from, Currency to) throws IOException {
        Map<String, Double> exchangeRates = getExchangeRates();
        return new ExchangeRate(from, to, LocalDate.now(), getConversionRate(from.code(), to.code(), exchangeRates));
    }

    private Map<String, Double> getExchangeRates() throws IOException {
        String json = loadJson();
        return toMap(json);
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/" + date + "?access_key=" + FixerAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private Map<String, Double> toMap(String json) {
        Map<String, Double> rates = new HashMap<>();
        JsonObject ratesObject = new Gson().fromJson(json, JsonObject.class).getAsJsonObject("rates");
        for (Map.Entry<String, JsonElement> entry : ratesObject.entrySet()) {
            rates.put(entry.getKey(), entry.getValue().getAsDouble());
        }
        return rates;
    }

    private double getConversionRate(String fromCurrency, String toCurrency, Map<String, Double> exchangeRates) {
        double rateFrom = exchangeRates.get(fromCurrency);
        double rateTo = exchangeRates.get(toCurrency);
        return rateTo / rateFrom;
    }
}

