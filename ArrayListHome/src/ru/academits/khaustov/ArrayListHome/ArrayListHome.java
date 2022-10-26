package ru.academits.khaustov.ArrayListHome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static ArrayList<String> getFileLinesList(String fileName) {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while(line != null) {
                linesList.add(line);

                line = reader.readLine();
            }

            return linesList;
        } catch (IOException e) {
            return null;
        }
    }

    public static void setOddNumbersList(ArrayList<Integer> list) {
        int listLength = list.size();

        for (int i = 0; i < listLength; ) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                listLength -= 1;
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
        String fileName = "ArrayListHomeTextFile.txt";

        ArrayList<String> linesList = getFileLinesList(fileName);

        if (linesList  == null) {
            System.out.println("Файл '" + fileName + "' не найден.");
        } else {
            System.out.println("Список строк из файла: " + linesList);
        }

        System.out.println();

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(12, 21, 233, 64, 12, 63));
        System.out.println("Список до изменений: " + list);
        setOddNumbersList(list);
        System.out.println("Список нечётных значений: " + list);

        System.out.println();

        list = new ArrayList<>(Arrays.asList(12, 12, 24, 13, 24, 26));
        System.out.println("Список с дубликатами: " + list);
        System.out.println("Список без дубликатов: " + getNonDuplicateList(list));
    }
}
