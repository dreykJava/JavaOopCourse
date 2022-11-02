package ru.academits.khaustov.ArrayListHome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayListHome {
    public static ArrayList<String> getFileLinesList(String fileName) {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                linesList.add(line);

                line = reader.readLine();
            }

            return linesList;
        } catch (IOException e) {
            throw new NoSuchElementException("Файла с названием " + fileName + " не существует.");
        }
    }

    public static void retainOddNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); ) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> getNonDuplicateList(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!newList.contains(number)) {
                newList.add(number);
            }
        }

        return newList;
    }

    public static void main(String[] args) {
        String fileName = "ArrayListHomeTextFil.txt";

        ArrayList<String> linesList = getFileLinesList(fileName);
        System.out.println("Список строк из файла: " + linesList);
        System.out.println();

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(12, 21, 233, 64, 12, 63));
        System.out.println("Список до изменений: " + list);
        retainOddNumbers(list);
        System.out.println("Список нечётных значений: " + list);
        System.out.println();

        list = new ArrayList<>(Arrays.asList(12, 12, 24, 13, 24, 26));
        System.out.println("Список с дубликатами: " + list);
        System.out.println("Список без дубликатов: " + getNonDuplicateList(list));
    }
}
