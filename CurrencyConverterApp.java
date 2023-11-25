import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class ExchangeRateApi {

    public static Map<String, Double> getExchangeRates(String baseCurrency) {
        Map<String, Double> rates = new HashMap<>();
        
        rates.put("INR", 1.0); 
        rates.put("EUR", 0.0111);
        rates.put("USD", 0.0120);
        return rates;
    }
}

public class CurrencyConverterApp extends JFrame {
    private JComboBox<String> baseCurrencyComboBox;
    private JComboBox<String> targetCurrencyComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        
        add(new JLabel("Base Currency:"));
        String[] currencies = {"INR", "EUR", "USD"}; 
        baseCurrencyComboBox = new JComboBox<>(currencies);
        add(baseCurrencyComboBox);

        
        add(new JLabel("Target Currency:"));
        targetCurrencyComboBox = new JComboBox<>(currencies);
        add(targetCurrencyComboBox);

        
        add(new JLabel("Amount:"));
        amountTextField = new JTextField();
        add(amountTextField);

       
        JButton convertButton = new JButton("Convert");
        add(convertButton);

       
        resultLabel = new JLabel("Result: ");
        add(resultLabel);

        
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        setSize(400, 200);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void convertCurrency() {
        try {
            String baseCurrency = (String) baseCurrencyComboBox.getSelectedItem();
            String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();
            double amount = Double.parseDouble(amountTextField.getText());

           
            Map<String, Double> exchangeRates = ExchangeRateApi.getExchangeRates(baseCurrency);

           
            if (exchangeRates.containsKey(targetCurrency)) {
                double exchangeRate = exchangeRates.get(targetCurrency);
                double result = amount * exchangeRate;

                resultLabel.setText(String.format("Result: %.2f %s", result, targetCurrency));
            } else {
                resultLabel.setText("Invalid currency selection.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount format.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterApp());
    }
}
