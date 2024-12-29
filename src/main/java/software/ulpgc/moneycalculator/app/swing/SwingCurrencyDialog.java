package software.ulpgc.moneycalculator.app.swing;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private JComboBox<Currency> currencySelector;
    private final String label;

    public SwingCurrencyDialog(String label) {
        this.setLayout(new FlowLayout());
        this.label = label;
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        JLabel text = new JLabel(label);
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        add(text);
        add(createCurrencySelector(currencies));
        return this;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        selector.setFont(new Font("Tahoma", Font.PLAIN, 16));
        for (Currency currency : currencies) selector.addItem(currency);
        if (Objects.equals(label, "From:")) {
            selector.setSelectedItem(new Currency("USD", "United States Dollar"));
        } else if (Objects.equals(label, "To:")) {
            selector.setSelectedItem(new Currency("EUR", "Euro"));
        }
        this.currencySelector = selector;
        return selector;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
