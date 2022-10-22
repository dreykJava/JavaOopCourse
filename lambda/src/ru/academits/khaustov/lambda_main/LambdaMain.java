package ru.academits.khaustov.lambda_main;

import ru.academits.khaustov.lambda.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaMain {
    public static void main(String[] args) {
        Person person1 = new Person("Ivan", 18);
        Person person2 = new Person("Sergey", 16);
        Person person3 = new Person("Maks", 12);
        Person person4 = new Person("Andrey", 24);
        Person person5 = new Person("Igor", 45);
        Person person6 = new Person("Elisey", 45);
        Person person7 = new Person("Maks", 40);
        Person person8 = new Person("Ivan", 22);

        List<Person> persons = Arrays.asList(person1, person2, person3, person4,
                person5, person6, person7, person8);

        List<String> distinctNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String stringDistinctNames = distinctNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(stringDistinctNames);

        List<Person> peopleUnder18List = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        peopleUnder18List.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresent(System.out::println);
/**
        Map<String, OptionalDouble> averageAgeByNames = persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .mapToInt(Person::getAge)
                .average()
                .collect(Collectors.groupingBy(Person::getName));  **/

        String personsBetween20and45 = persons.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Имена людей в возрасте от 20 до 45 включительно: ", "."));

        System.out.println(personsBetween20and45);
    }
}