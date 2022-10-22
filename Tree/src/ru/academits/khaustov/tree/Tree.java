package ru.academits.khaustov.tree;

import ru.academits.khaustov.tree_node.TreeNode;

import java.util.*;

public class Tree<T> {
    private TreeNode<T> treeRoot;
    private final Comparator<T> comparator;

    public Tree(Comparator<T> comparator) {
        this.treeRoot = null;
        this.comparator = comparator;
    }

    public Tree() {
        this.treeRoot = null;
        this.comparator = null;
    }

    public TreeNode<T> getTreeRoot() {
        return treeRoot;
    }

    public void setTreeNode(TreeNode<T> treeNode) {
        if (treeRoot == null) {
            treeRoot = treeNode;
        }

        if (treeNode == null) {
            throw new NullPointerException("В бинарном дереве не должно быть null элементов.");
        }

        TreeNode<T> currentTreeNode = treeRoot;
        int compareResult;

        if (comparator != null) {
            while(true) {
                compareResult = comparator.compare(treeNode.getData(), currentTreeNode.getData());

                if (compareResult < 0) {
                    if (currentTreeNode.getLeft() == null) {
                        currentTreeNode.setLeft(treeNode);

                        break;
                    } else {
                        currentTreeNode = currentTreeNode.getLeft();

                        continue;
                    }
                }

                if (currentTreeNode.getRight() == null) {
                    currentTreeNode.setRight(treeNode);

                    break;
                } else {
                    currentTreeNode = currentTreeNode.getRight();
                }
            }
        } else {
            while(true) {
                //noinspection unchecked
                compareResult = ((Comparable<T>) treeNode.getData()).compareTo(currentTreeNode.getData());

                if (compareResult < 0) {
                    if (currentTreeNode.getLeft() == null) {
                        currentTreeNode.setLeft(treeNode);

                        break;
                    } else {
                        currentTreeNode = currentTreeNode.getLeft();

                        continue;
                    }
                }

                if (currentTreeNode.getRight() == null) {
                    currentTreeNode.setRight(treeNode);

                    break;
                } else {
                    currentTreeNode = currentTreeNode.getRight();
                }
            }
        }
    }

    public boolean isTreeNodeInTree(TreeNode<T> treeNode) {
        if (treeRoot == null) {
            return false;
        }

        TreeNode<T> currentTreeNode = treeRoot;
        int compareResult;

        if (comparator != null) {
            while (true) {
                compareResult = comparator.compare(treeNode.getData(), currentTreeNode.getData());

                if (compareResult == 0) {
                    return true;
                } else if (compareResult < 0) {
                    if (currentTreeNode.getLeft() == null) {
                        return false;
                    } else {
                        currentTreeNode = currentTreeNode.getLeft();
                    }
                } else {
                    if (currentTreeNode.getRight() == null) {
                        return false;
                    } else {
                        currentTreeNode = currentTreeNode.getRight();
                    }
                }
            }
        } else {
            while (true) {
                //noinspection unchecked
                compareResult = ((Comparable<T>) treeNode.getData()).compareTo(currentTreeNode.getData());

                if (compareResult == 0) {
                    return true;
                } else if (compareResult < 0) {
                    if (currentTreeNode.getLeft() == null) {
                        return false;
                    } else {
                        currentTreeNode = currentTreeNode.getLeft();
                    }
                } else {
                    if (currentTreeNode.getRight() == null) {
                        return false;
                    } else {
                        currentTreeNode = currentTreeNode.getRight();
                    }
                }
            }
        }
    }

    public boolean removeTreeNode(T treeNodeData) {
        if (treeRoot == null) {
            return false;
        }

        int compareResult;
        TreeNode<T> currentTreeNode;
        TreeNode<T> nextTreeNode;

        if (comparator != null) {
            compareResult = comparator.compare(treeNodeData, treeRoot.getData());

            if (compareResult == 0) {
                if (treeRoot.getLeft() == null && treeRoot.getRight() == null) {
                    treeRoot = null;

                    return true;
                }

                if (treeRoot.getRight() == null) {
                    treeRoot = treeRoot.getLeft();

                    return true;
                }

                if (treeRoot.getLeft() == null) {
                    treeRoot = treeRoot.getRight();

                    return true;
                }

                if (treeRoot.getRight().getLeft() != null) {
                    currentTreeNode = treeRoot.getRight();
                    nextTreeNode = treeRoot.getRight().getLeft();

                    while(true) {
                        if (nextTreeNode.getLeft() != null) {
                            currentTreeNode = currentTreeNode.getLeft();
                            nextTreeNode = nextTreeNode.getLeft();
                        } else {
                            if (nextTreeNode.getRight() == null) {
                                treeRoot.setData(nextTreeNode.getData());
                                currentTreeNode.setLeft(null);
                            } else {
                                treeRoot.setData(nextTreeNode.getData());
                                currentTreeNode.setLeft(nextTreeNode.getRight());
                            }

                            break;
                        }
                    }

                    return true;
                }

                treeRoot.setData(treeRoot.getRight().getData());
                treeRoot.setRight(treeRoot.getRight().getRight());

                return true;

            }

            currentTreeNode = treeRoot;

            if (compareResult < 0) {
                nextTreeNode = treeRoot.getLeft();
            } else  {
                nextTreeNode = treeRoot.getRight();
            }

            while (true) {
                if (nextTreeNode == null) {
                    return false;
                }

                compareResult = comparator.compare(treeNodeData, nextTreeNode.getData());

                if (compareResult < 0) {
                    currentTreeNode = nextTreeNode;
                    nextTreeNode = nextTreeNode.getLeft();

                    continue;
                } else if (compareResult > 0) {
                    currentTreeNode = nextTreeNode;
                    nextTreeNode = nextTreeNode.getRight();

                    continue;
                }

                compareResult = comparator.compare(treeNodeData, currentTreeNode.getData());

                if (nextTreeNode.getLeft() == null || nextTreeNode.getRight() == null) {
                    if (compareResult < 0) {
                        if (nextTreeNode.getLeft() == null && nextTreeNode.getRight() == null) {
                            currentTreeNode.setLeft(null);

                            return true;
                        }

                        if (nextTreeNode.getLeft() == null) {
                            currentTreeNode.setLeft(nextTreeNode.getRight());

                            return true;
                        }

                        if (nextTreeNode.getRight() == null) {
                            currentTreeNode.setLeft(nextTreeNode.getLeft());

                            return true;
                        }
                    }

                    if (nextTreeNode.getLeft() == null && nextTreeNode.getRight() == null) {
                        currentTreeNode.setRight(null);

                        return true;
                    }

                    if (nextTreeNode.getLeft() == null) {
                        currentTreeNode.setRight(nextTreeNode.getRight());

                        return true;
                    }

                    if (nextTreeNode.getRight() == null) {
                        currentTreeNode.setRight(nextTreeNode.getLeft());

                        return true;
                    }
                }

                currentTreeNode = nextTreeNode;
                TreeNode<T> removedTreeNode = nextTreeNode;
                nextTreeNode = nextTreeNode.getRight();

                while(true) {
                    if (nextTreeNode.getLeft() != null) {
                        currentTreeNode = nextTreeNode;
                        nextTreeNode = nextTreeNode.getLeft();

                        continue;
                    }

                    if (nextTreeNode.getRight() == null) {
                        removedTreeNode.setData(nextTreeNode.getData());
                        currentTreeNode.setLeft(null);

                        return true;
                    }

                    removedTreeNode.setData(nextTreeNode.getData());
                    currentTreeNode.setLeft(nextTreeNode.getRight());

                    return true;
                }
            }
        }

        //noinspection unchecked
        compareResult = ((Comparable<T>) treeNodeData).compareTo(treeRoot.getData());

        if (compareResult == 0) {
            if (treeRoot.getLeft() == null && treeRoot.getRight() == null) {
                treeRoot = null;

                return true;
            }

            if (treeRoot.getRight() == null) {
                treeRoot = treeRoot.getLeft();

                return true;
            }

            if (treeRoot.getLeft() == null) {
                treeRoot = treeRoot.getRight();

                return true;
            }

            if (treeRoot.getRight().getLeft() != null) {
                currentTreeNode = treeRoot.getRight();
                nextTreeNode = treeRoot.getRight().getLeft();

                while(true) {
                    if (nextTreeNode.getLeft() != null) {
                        currentTreeNode = currentTreeNode.getLeft();
                        nextTreeNode = nextTreeNode.getLeft();
                    } else {
                        if (nextTreeNode.getRight() == null) {
                            treeRoot.setData(nextTreeNode.getData());
                            currentTreeNode.setLeft(null);
                        } else {
                            treeRoot.setData(nextTreeNode.getData());
                            currentTreeNode.setLeft(nextTreeNode.getRight());
                        }

                        break;
                    }
                }

                return true;
            }

            treeRoot.setData(treeRoot.getRight().getData());
            treeRoot.setRight(treeRoot.getRight().getRight());

            return true;

        }

        currentTreeNode = treeRoot;

        if (compareResult < 0) {
            nextTreeNode = treeRoot.getLeft();
        } else  {
            nextTreeNode = treeRoot.getRight();
        }

        while (true) {
            if (nextTreeNode == null) {
                return false;
            }

            //noinspection unchecked
            compareResult = ((Comparable<T>) treeNodeData).compareTo(nextTreeNode.getData());

            if (compareResult < 0) {
                currentTreeNode = nextTreeNode;
                nextTreeNode = nextTreeNode.getLeft();

                continue;
            } else if (compareResult > 0) {
                currentTreeNode = nextTreeNode;
                nextTreeNode = nextTreeNode.getRight();

                continue;
            }

            //noinspection unchecked
            compareResult = ((Comparable<T>) treeNodeData).compareTo(currentTreeNode.getData());

            if (nextTreeNode.getLeft() == null || nextTreeNode.getRight() == null) {
                if (compareResult < 0) {
                    if (nextTreeNode.getLeft() == null && nextTreeNode.getRight() == null) {
                        currentTreeNode.setLeft(null);

                        return true;
                    }

                    if (nextTreeNode.getLeft() == null) {
                        currentTreeNode.setLeft(nextTreeNode.getRight());

                        return true;
                    }

                    if (nextTreeNode.getRight() == null) {
                        currentTreeNode.setLeft(nextTreeNode.getLeft());

                        return true;
                    }
                }

                if (nextTreeNode.getLeft() == null && nextTreeNode.getRight() == null) {
                    currentTreeNode.setRight(null);

                    return true;
                }

                if (nextTreeNode.getLeft() == null) {
                    currentTreeNode.setRight(nextTreeNode.getRight());

                    return true;
                }

                if (nextTreeNode.getRight() == null) {
                    currentTreeNode.setRight(nextTreeNode.getLeft());

                    return true;
                }
            }

            currentTreeNode = nextTreeNode;
            TreeNode<T> removedTreeNode = nextTreeNode;
            nextTreeNode = nextTreeNode.getRight();

            while (true) {
                if (nextTreeNode.getLeft() != null) {
                    currentTreeNode = nextTreeNode;
                    nextTreeNode = nextTreeNode.getLeft();

                    continue;
                }

                if (nextTreeNode.getRight() == null) {
                    removedTreeNode.setData(nextTreeNode.getData());
                    currentTreeNode.setLeft(null);

                    return true;
                }

                removedTreeNode.setData(nextTreeNode.getData());
                currentTreeNode.setLeft(nextTreeNode.getRight());

                return true;
            }
        }
    }

    public int getSize() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(treeRoot);

        int size = 0;

        TreeNode<T> currentTreeNode;

        while (!queue.isEmpty()) {
            currentTreeNode = queue.remove();

            size++;

            if (currentTreeNode.getLeft() != null) {
                queue.add(currentTreeNode.getLeft());
            }

            if (currentTreeNode.getRight() != null) {
                queue.add(currentTreeNode.getRight());
            }
        }

        return size;
    }

    public Collection<TreeNode<T>> passWidth() {
        LinkedList<TreeNode<T>> treeNodes = new LinkedList<>();

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(treeRoot);

        TreeNode<T> currentTreeNode;

        while (!queue.isEmpty()) {
            currentTreeNode = queue.remove();

            treeNodes.add(currentTreeNode);

            if (currentTreeNode.getLeft() != null) {
                queue.add(currentTreeNode.getLeft());
            }

            if (currentTreeNode.getRight() != null) {
                queue.add(currentTreeNode.getRight());
            }
        }

        return treeNodes;
    }

    public Collection<TreeNode<T>> passDeepRecursive() {
        LinkedList<TreeNode<T>> treeNodes = new LinkedList<>();

        return passDeepRecursive(treeRoot, treeNodes);
    }

    public Collection<TreeNode<T>> passDeepRecursive(TreeNode<T> treeNode, LinkedList<TreeNode<T>> treeNodes) {
        treeNodes.add(treeNode);

        if (treeNode.getLeft() != null) {
            passDeepRecursive(treeNode.getLeft(), treeNodes);
        }

        if (treeNode.getRight() != null) {
            passDeepRecursive(treeNode.getRight(), treeNodes);
        }

        return treeNodes;
    }

    public Collection<TreeNode<T>> passDeep() {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.add(treeRoot);

        LinkedList<TreeNode<T>> treeNodes = new LinkedList<>();

        TreeNode<T> currentTreeNode;

        while (!stack.isEmpty()) {
            currentTreeNode = stack.remove();

            treeNodes.add(currentTreeNode);

            if (currentTreeNode.getRight() != null) {
                stack.add(currentTreeNode.getRight());
            }

            if (currentTreeNode.getLeft() != null) {
                stack.add(currentTreeNode.getLeft());
            }
        }

        return treeNodes;
    }
}
