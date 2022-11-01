package ru.academits.khaustov.model;

import ru.academits.khaustov.view.View;

import java.util.Objects;

public record Model(View view) {
    public Model(View view) {
        this.view = view;
        initView();
    }

    public void initView() {
        view.getEnteredTemperature().setText("0");
    }

    public void initModel() {
        view.getConvert().addActionListener(e -> view.getResult().setText(convertTemperature()));
    }

    private String convertTemperature() {
        Object initialTemperatureUnit = view.getInitialTemperatureUnit().getSelectedItem();
        Object resultTemperatureUnit = view.getResultTemperatureUnit().getSelectedItem();

        double enteredTemperature;
        try {
            enteredTemperature = Double.parseDouble(view.getEnteredTemperature().getText());
        } catch (NumberFormatException e1) {
            enteredTemperature = 0;
            view.showErrorMessage();
        }

        if (Objects.equals(initialTemperatureUnit, resultTemperatureUnit)) {
            return " = " + enteredTemperature;
        }

        if (Objects.equals(initialTemperatureUnit, "C")) {
            return " = " + convertCelsius(resultTemperatureUnit, enteredTemperature);
        }

        if (Objects.equals(initialTemperatureUnit, "F")) {
            return " = " + convertFahrenheits(resultTemperatureUnit, enteredTemperature);
        }

        return " = " + convertKelvins(resultTemperatureUnit, enteredTemperature);
    }

    private double convertCelsius(Object resultTemperatureUnit, double enteredTemperature) {
        if (Objects.equals(resultTemperatureUnit, "F")) {
            return enteredTemperature * (9 / 5.0) + 32;
        }

        return enteredTemperature + 273.15;
    }

    private double convertFahrenheits(Object resultTemperatureUnit, double enteredTemperature) {
        if (Objects.equals(resultTemperatureUnit, "C")) {
            return (enteredTemperature - 32) * (5 / 9.0);
        }

        return (enteredTemperature - 32) * (5 / 9.0) + 273.15;
    }

    private double convertKelvins(Object resultTemperatureUnit, double enteredTemperature) {
        if (Objects.equals(resultTemperatureUnit, "C")) {
            return enteredTemperature - 273.15;
        }

        return (enteredTemperature - 273.15) * (9 / 5.0) + 32;
    }
}
