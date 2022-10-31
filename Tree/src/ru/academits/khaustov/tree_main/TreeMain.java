package ru.academits.khaustov.tree_main;

import ru.academits.khaustov.tree.Tree;

import java.util.function.Consumer;

public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>();
        tree1.add(12);

        System.out.println("Корень первого дерева: " + tree1.getRoot());

        tree1.add(13);
        tree1.add(14);
        tree1.add(0);

        Consumer<Integer> consumer = x -> System.out.print(x + "; ");

        System.out.print("Элементы первого дерева: ");
        tree1.passWidth(consumer);
        System.out.println();

        tree1.add(-10);
        tree1.add(19);

        System.out.print("Элементы первого дерева: ");
        tree1.passDeep(consumer);
        System.out.println();

        tree1.add(-11);
        tree1.add(-11);

        System.out.print("Элементы первого дерева: ");
        tree1.passDeepRecursive(consumer);
        System.out.println();

        if (tree1.contains(-11)) {
            System.out.println("Первое дерево содержит элемент " + -11);
        } else {
            System.out.println("Первое дерево не содержит элемент " + -11);
        }

        if (tree1.contains(1)) {
            System.out.println("Первое дерево содержит элемент " + 1);
        } else {
            System.out.println("Первое дерево не содержит элемент " + 1);
        }

        if (tree1.remove(11)) {
            System.out.println("Из первого дерева был удалён элемент " + 11);
        } else {
            System.out.println("Из первого дерева не был удалён элемент " + 11);
        }

        if (tree1.remove(-11)) {
            System.out.println("Из первого дерева был удалён элемент " + -11);
        } else {
            System.out.println("Из первого дерева не был удалён элемент " + -11);
        }

        System.out.print("Элементы первого дерева: ");
        tree1.passDeep(consumer);
        System.out.println();

        System.out.println("Размер первого дерева: " + tree1.getSize());
    }
}
