package software.ulpgc.moneycalculator.app.swing;

import software.ulpgc.moneycalculator.architecture.model.Money;
import software.ulpgc.moneycalculator.architecture.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(Money money) {
        this.setFont(new Font("Segoe UI", Font.BOLD, 20));
        this.setText(money.toString());
    }
}
