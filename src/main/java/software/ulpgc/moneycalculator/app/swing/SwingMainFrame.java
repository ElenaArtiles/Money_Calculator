package software.ulpgc.moneycalculator.app.swing;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.architecture.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private final JTextField textField;
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public SwingMainFrame() throws MalformedURLException {
        this.textField = new JTextField();
        this.setSize(800,350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Money Calculator");
        URL url = new URL("https://cdn-icons-png.flaticon.com/512/2666/2666395.png");
        ImageIcon icon = new ImageIcon(url);
        this.setIconImage(icon.getImage());
        this.add(createMainPanel(), BorderLayout.CENTER);
    }

    private Component createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.add(createNorthPanel(), BorderLayout.NORTH);
        panel.add(createCenterPanel(), BorderLayout.CENTER);
        panel.add(createSouthPanel(), BorderLayout.SOUTH);
        return panel;

    }

    private Component createNorthPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Amount:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(label);
        textField.setColumns(10);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(textField);
        return panel;
    }

    private Component createCenterPanel() {
        JPanel panel = new JPanel();
        panel.add(createDialogs(), BorderLayout.CENTER);
        return panel;
    }

    private Component createDialogs() {
        JPanel panel = new JPanel();
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(textField);
        this.moneyDialog = moneyDialog;
        panel.add(moneyDialog);
        SwingCurrencyDialog currencyDialog = new SwingCurrencyDialog("To:");
        this.currencyDialog = currencyDialog;
        panel.add(currencyDialog);
        return panel;
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.add(createButton());
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        panel.add(display);
        return panel;
    }

    private Component createButton() {
        JButton button = new JButton("Convert");
        button.addActionListener(e -> {
            try {
                commands.get("exchange-money").execute();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 18));
        return button;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
