package software.ulpgc.moneycalculator.app;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.app.fixerws.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.app.fixerws.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.app.swing.SwingMainFrame;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        LocalDate date = LocalDate.now();
        SwingMainFrame mainFrame = new SwingMainFrame();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                mainFrame.moneyDialog().define(currencies),
                mainFrame.currencyDialog().define(currencies),
                new FixerExchangeRateLoader(date),
                mainFrame.moneyDisplay());
        mainFrame.add("exchange-money", command);
        mainFrame.setVisible(true);
    }
}
