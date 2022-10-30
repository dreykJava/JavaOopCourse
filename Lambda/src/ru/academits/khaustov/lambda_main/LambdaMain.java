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
                new Person("Ivan", 22));

        List<String> distinctNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String distinctNamesString = distinctNames.stream()
                .collect(Collectors.joining(", ", "", "."));

        System.out.println("Уникальные имена: " + distinctNamesString);
        System.out.println();

        List<Person> peopleUnder18List = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        OptionalDouble peopleUnder18AverageAges = peopleUnder18List.stream()
                .mapToInt(Person::getAge)
                .average();

        if (peopleUnder18AverageAges.isPresent()) {
            System.out.print("Средний возраст для людей младше 18 лет: ");
            peopleUnder18AverageAges.ifPresent(System.out::print);
            System.out.println(" лет.");
        } else {
            System.out.println("Невозможно посчитать средний возраст.");
        }

        System.out.println();
        
        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        averageAgesByNames.forEach((name, averageAges) ->
                System.out.printf("Средний возраст для имени %s равен: %f%n", name, averageAges));
        System.out.println();

        String personsBetween20and45 = persons.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Имена людей в возрасте от 20 до 45 включительно: ", "."));

        System.out.println(personsBetween20and45);
    }
}