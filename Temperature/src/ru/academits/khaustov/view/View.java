package ru.academits.khaustov.view;

import javax.swing.*;
import java.awt.*;

public class View {
    private final JPanel panel;
    private final JTextField enteredTemperature;
    private final JComboBox<String> initialTemperatureUnit;
    private final JComboBox<String> resultTemperatureUnit;
    private final JLabel result;
    private final JButton convert;

    public View(String title) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int appWidth = 400;
        int appHeight = 260;

        JFrame frame = new JFrame(title);
        frame.setSize(appWidth, appHeight);
        frame.setLocation((screenWidth - appWidth) / 2, (screenHeight - appHeight) / 2);
        frame.setMinimumSize(new Dimension(appWidth, appHeight));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] temperatureUnits = {"C", "F", "K"};
        initialTemperatureUnit = new JComboBox<>(temperatureUnits);
        resultTemperatureUnit = new JComboBox<>(temperatureUnits);

        panel = new JPanel();
        enteredTemperature = new JTextField(10);
        result = new JLabel(" = 0");

        convert = new JButton("Перевести");

        panel.add(initialTemperatureUnit);
        panel.add(enteredTemperature);
        panel.add(result);
        panel.add(resultTemperatureUnit);
        panel.add(convert);
        frame.add(panel);

        frame.setVisible(true);
    }

    public JComboBox<String> getInitialTemperatureUnit() {
        return initialTemperatureUnit;
    }

    public JComboBox<String> getResultTemperatureUnit() {
        return resultTemperatureUnit;
    }

    public JTextField getEnteredTemperature() {
        return enteredTemperature;
    }

    public JLabel getResult() {
        return result;
    }

    public JButton getConvert() {
        return convert;
    }

    public void showErrorMessage() {
        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
    }
}
