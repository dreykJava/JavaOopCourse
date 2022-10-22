package ru.academits.khaustov.tree_main;

import ru.academits.khaustov.tree.Tree;
import ru.academits.khaustov.tree_node.TreeNode;

public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> newTree = new Tree<>();

        TreeNode<Integer> firstNode = new TreeNode<>(6);
        TreeNode<Integer> secondNode = new TreeNode<>(4);
        TreeNode<Integer> thirdNode = new TreeNode<>(5);
        TreeNode<Integer> fourthNode = new TreeNode<>(11);

        newTree.setTreeNode(firstNode);

        System.out.println(newTree.getTreeRoot().getData());

        newTree.setTreeNode(secondNode);

        System.out.println(newTree.getTreeRoot().getData());

        newTree.setTreeNode(thirdNode);

        System.out.println(newTree.getTreeRoot().getData());

        newTree.setTreeNode(fourthNode);

        System.out.println(newTree.getTreeRoot().getData());
    }
}
