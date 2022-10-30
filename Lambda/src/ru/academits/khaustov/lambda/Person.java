package ru.academits.khaustov.lambda;

public record Person(String name, int age) {

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
