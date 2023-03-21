package dsa.impl;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        bst.insert(23);
        bst.insert(12);
        bst.insert(44);
        bst.insert(13);
        bst.insert(1);
        bst.insert(7);
        bst.insert(22);
        bst.insert(55);
        bst.insert(43);
        bst.insert(18);

        System.out.println("FIRST TREE------------------------------------------------------");
        TreePrinter.printTree(bst);

        bst.remove(1);

        System.out.println("SECOND TREE------------------------------------------------------");
        TreePrinter.printTree(bst);

        bst.remove(23);

        System.out.println("THIRD TREE-------------------------------------------------------");
        TreePrinter.printTree(bst);

    }
}
