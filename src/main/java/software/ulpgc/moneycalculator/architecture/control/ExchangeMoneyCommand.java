package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.architecture.persistence.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.architecture.view.MoneyDisplay;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.model.Money;

import java.io.IOException;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() throws IOException {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
        Money result = new Money(money.amount() * exchangeRate.rate(), currency);
        moneyDisplay.show(result);
    }
}
