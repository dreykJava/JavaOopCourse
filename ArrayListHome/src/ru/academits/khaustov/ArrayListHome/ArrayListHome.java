package ru.academits.khaustov.ArrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> getFileLinesArrayList(String fileName) throws FileNotFoundException {
        ArrayList<String> linesArrayList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while(scanner.hasNextLine()) {
                String fileString = scanner.nextLine();
                linesArrayList.add(fileString);
            }
        }

        return linesArrayList;
    }

    public static ArrayList<Integer> getOddNumbersArrayList(ArrayList<Integer> intArrayList) {
        for (int i = 0; i <= intArrayList.size() - 1; i++) {
            while (intArrayList.get(i) % 2 == 0) {
                int j;

                for (j = i + 1; j <= intArrayList.size() - 1 && intArrayList.get(j) != null; j++) {
                    intArrayList.set(j - 1, intArrayList.get(j));
                }

                intArrayList.remove(j - 1);
            }
        }

        return intArrayList;
    }

    public static ArrayList<Integer> getNonDuplicateArrayList(ArrayList<Integer> intArrayList) {
        for (int i = 0; i <= intArrayList.size() - 2; i++) {
            for (int j = i + 1; j <= intArrayList.size() - 1; j++) {
                if (intArrayList.get(i).equals(intArrayList.get(j))) {
                    int k;

                    for (k = j + 1; k <= intArrayList.size() - 1 && intArrayList.get(k) != null; k++) {
                        intArrayList.set(k - 1, intArrayList.get(k));
                    }

                    intArrayList.remove(k - 1);
                }
            }
        }

        return intArrayList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String filename = scanner.nextLine();
        System.out.println("Список строк из файла: " + getFileLinesArrayList(filename));

        ArrayList<Integer> intArrayList = new ArrayList<>(10);
        intArrayList.add(12);
        intArrayList.add(21);
        intArrayList.add(233);
        intArrayList.add(64);
        intArrayList.add(12);
        intArrayList.add(63);

        System.out.println("Список нечётных значений: " + getOddNumbersArrayList(intArrayList));

        intArrayList = new ArrayList<>(10);
        intArrayList.add(12);
        intArrayList.add(12);
        intArrayList.add(24);
        intArrayList.add(13);
        intArrayList.add(24);
        intArrayList.add(26);

        System.out.println("Неповторяющийся массив: " + getNonDuplicateArrayList(intArrayList));
    }
}
