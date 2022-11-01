package ru.academits.khaustov.temperature_main;

import ru.academits.khaustov.model.Model;
import ru.academits.khaustov.view.View;

public class TemperatureMain {
    public static void main(String[] args) {
        View view = new View("Перевод температур");
        Model model = new Model(view);
        model.initModel();
    }
}
