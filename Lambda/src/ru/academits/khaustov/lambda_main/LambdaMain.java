package ru.academits.khaustov.lambda_main;

import ru.academits.khaustov.lambda.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaMain {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 18),
                new Person("Sergey", 16),
                new Person("Maks", 12),
                new Person("Andrey", 24),
                new Person("Igor", 45),
                new Person("Elisey", 45),
                new Person("Maks", 40),
                new Person("Ivan", 22)
        );

        List<String> distinctNames = persons.stream()
                .map(Person::name)
                .distinct()
                .collect(Collectors.toList());

        String distinctNamesString = distinctNames.stream()
                .collect(Collectors.joining(", ", "", "."));

        System.out.println("Уникальные имена: " + distinctNamesString);
        System.out.println();

        List<Person> peopleUnder18List = persons.stream()
                .filter(x -> x.age() < 18)
                .collect(Collectors.toList());

        OptionalDouble peopleUnder18AverageAge = peopleUnder18List.stream()
                .mapToInt(Person::age)
                .average();

        if (peopleUnder18AverageAge.isPresent()) {
            System.out.println("Средний возраст для людей младше 18 лет: " + peopleUnder18AverageAge.getAsDouble() + " лет.");
        } else {
            System.out.println("Невозможно посчитать средний возраст.");
        }

        System.out.println();

        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        averageAgesByNames.forEach((name, averageAge) ->
                System.out.printf("Средний возраст для имени %s равен: %f%n", name, averageAge));
        System.out.println();

        String personsBetween20And45 = persons.stream()
                .filter(x -> x.age() >= 20 && x.age() <= 45)
                .sorted((p1, p2) -> p2.age() - p1.age())
                .map(Person::name)
                .collect(Collectors.joining(", ", "Имена людей в возрасте от 20 до 45 включительно: ", "."));

        System.out.println(personsBetween20And45);
    }
}