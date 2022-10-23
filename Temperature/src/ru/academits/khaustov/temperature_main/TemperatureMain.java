package ru.academits.khaustov.temperature_main;

import javax.swing.*;

public class TemperatureMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature conversion");
            frame.setSize(400, 260);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextField textField = new JTextField(10);
            JLabel initialTemperatureUnit = new JLabel("C");
            JLabel equalsNotation = new JLabel("=");
            JLabel result = new JLabel("0");
            JLabel resultTemperatureUnit = new JLabel("C");

            JButton changeTemperatureUnit = new JButton("Изменить единицу измерения");
            JButton celsiusConversion = new JButton("Перевести в градусы цельсия");
            JButton fahrenheitConversion = new JButton("Перевести в градусы фаренгейта");
            JButton kelvinConversion = new JButton("Перевести в кельвины");

            changeTemperatureUnit.addActionListener(e -> {
                Object[] temperatureUnits = {"C", "K", "F"};

                String temperatureUnit = (String) JOptionPane.showInputDialog(frame, "Выберите единицу измерения температуры: ",
                        "Change temperature unit", JOptionPane.PLAIN_MESSAGE, null,  temperatureUnits, "C");

                initialTemperatureUnit.setText(temperatureUnit);
            });

            celsiusConversion.addActionListener(e -> {
                if (initialTemperatureUnit.getText().equals("C")) {
                    int resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf(resultTemperature));
                    resultTemperatureUnit.setText("C");
                } else if (initialTemperatureUnit.getText().equals("F")) {
                    double resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf((resultTemperature - 32) * 5 / 9));
                    resultTemperatureUnit.setText("F");
                } else {
                    double resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf(resultTemperature - 273.15));
                    resultTemperatureUnit.setText("K");
                }
            });

            fahrenheitConversion.addActionListener(e -> {
                if (initialTemperatureUnit.getText().equals("F")) {
                    int resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf(resultTemperature));
                    resultTemperatureUnit.setText("F");
                } else if (initialTemperatureUnit.getText().equals("C")) {
                    double resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf((resultTemperature * 9 / 5) + 32));
                    resultTemperatureUnit.setText("C");
                } else {
                    double resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf((resultTemperature - 273.15) * 9 / 5 + 32));
                    resultTemperatureUnit.setText("K");
                }
            });

            kelvinConversion.addActionListener(e -> {
                if (initialTemperatureUnit.getText().equals("K")) {
                    int resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf(resultTemperature));
                    resultTemperatureUnit.setText("K");
                } else if (initialTemperatureUnit.getText().equals("C")) {
                    double resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf(resultTemperature + 273.15));
                    resultTemperatureUnit.setText("C");
                } else {
                    double resultTemperature = 0;

                    try {
                        resultTemperature = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
                    }

                    result.setText(String.valueOf((resultTemperature - 32) * 5 / 9 + 273.15));
                    resultTemperatureUnit.setText("F");
                }
            });

            panel.add(changeTemperatureUnit);
            panel.add(textField);
            panel.add(initialTemperatureUnit);
            panel.add(equalsNotation);
            panel.add(result);
            panel.add(resultTemperatureUnit);
            panel.add(celsiusConversion);
            panel.add(fahrenheitConversion);
            panel.add(kelvinConversion);
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
