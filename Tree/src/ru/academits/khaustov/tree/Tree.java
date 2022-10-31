package ru.academits.khaustov.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<E> {
    private TreeNode<E> root;
    private final Comparator<E> comparator;
    private int size;

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
        size = 0;
    }

    public Tree() {
        comparator = null;
        size = 0;
    }

    private int compare(E data1, E data2) {
        int compareResult;

        if (comparator != null) {
            compareResult = comparator.compare(data1, data2);
        } else {
            //noinspection unchecked
            compareResult = ((Comparable<E>) data1).compareTo(data2);
        }

        return compareResult;
    }

    public E getRoot() {
        if (root == null) {
            throw new NullPointerException("У дерева нет корневого элемента.");
        }

        return root.getData();
    }

    public void add(E data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<E> current = root;

        while (true) {
            int compareResult = compare(data, current.getData());

            if (compareResult < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode<>(data));
                    size++;

                    return;
                }

                current = current.getLeft();

                continue;
            }

            if (current.getRight() == null) {
                current.setRight(new TreeNode<>(data));
                size++;

                return;
            }

            current = current.getRight();
        }
    }

    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> current = root;
        int compareResult;

        while (true) {
            compareResult = compare(data, current.getData());

            if (compareResult == 0) {
                return true;
            }

            if (compareResult < 0) {
                if (current.getLeft() == null) {
                    return false;
                }

                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    return false;
                }

                current = current.getRight();
            }
        }
    }

    public boolean remove(E data) {
        if (root == null) {
            return false;
        }

        int compareResult = compare(data, root.getData());

        if (compareResult == 0) {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                size--;

                return true;
            }

            if (root.getRight() == null) {
                root = root.getLeft();
                size--;

                return true;
            }

            if (root.getLeft() == null) {
                root = root.getRight();
                size--;

                return true;
            }

            if (root.getRight().getLeft() != null) {
                TreeNode<E> current = root.getRight();
                TreeNode<E> next = root.getRight().getLeft();

                while (next.getLeft() != null) {
                    current = current.getLeft();
                    next = next.getLeft();
                }

                current.setLeft(next.getRight());
                next.setLeft(root.getLeft());
                next.setRight(root.getRight());
                root = next;
                size--;

                return true;
            }

            root.getRight().setLeft(root.getLeft());
            root = root.getRight();
            size--;

            return true;
        }

        TreeNode<E> current = root;
        TreeNode<E> next;

        if (compareResult < 0) {
            next = root.getLeft();
        } else {
            next = root.getRight();
        }

        while (true) {
            if (next == null) {
                return false;
            }

            compareResult = compare(data, next.getData());

            if (compareResult < 0) {
                current = next;
                next = next.getLeft();

                continue;
            } else if (compareResult > 0) {
                current = next;
                next = next.getRight();

                continue;
            }

            if (next.getLeft() == null || next.getRight() == null) {
                compareResult = compare(data, current.getData());

                if (compareResult < 0) {
                    if (next.getLeft() == null && next.getRight() == null) {
                        current.setLeft(null);
                        size--;

                        return true;
                    }

                    if (next.getLeft() == null) {
                        current.setLeft(next.getRight());
                        size--;

                        return true;
                    }

                    current.setLeft(next.getLeft());
                    size--;

                    return true;
                }

                if (next.getLeft() == null && next.getRight() == null) {
                    current.setRight(null);
                    size--;

                    return true;
                }

                if (next.getLeft() == null) {
                    current.setRight(next.getRight());
                    size--;

                    return true;
                }

                if (next.getRight() == null) {
                    current.setRight(next.getLeft());
                    size--;

                    return true;
                }
            }

            TreeNode<E> previousRemoved = current;
            TreeNode<E> removed = next;
            current = next;
            next = next.getRight();

            while (true) {
                if (next.getLeft() != null) {
                    current = next;
                    next = next.getLeft();

                    continue;
                }

                current.setLeft(next.getRight());
                next.setLeft(removed.getLeft());
                next.setRight(removed.getRight());

                if (compare(data, previousRemoved.getData()) < 0) {
                    previousRemoved.setLeft(next);
                    size--;

                    return true;
                }

                previousRemoved.setRight(next);
                size--;

                return true;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void passWidth(Consumer<E> consumer) {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.remove();

            consumer.accept(current.getData());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    public void passDeepRecursive(Consumer<E> consumer) {
        passDeepRecursive(root, consumer);
    }

    private void passDeepRecursive(TreeNode<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            passDeepRecursive(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            passDeepRecursive(node.getRight(), consumer);
        }
    }

    public void passDeep(Consumer<E> consumer) {
        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.removeLast();

            consumer.accept(current.getData());

            if (current.getRight() != null) {
                stack.add(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.add(current.getLeft());
            }
        }
    }
}
