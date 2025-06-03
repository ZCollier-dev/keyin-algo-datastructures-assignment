package org.example;

public class Main {
    public static void main(String[] args) {
        SingleLinkedListTest sll = new SingleLinkedListTest();
        sll.insertLinkedList(1,0); //d
        sll.insertLinkedList(2,1);
        sll.insertLinkedList(3,2);
        sll.insertLinkedList(4,3); //d
        sll.insertLinkedList(5,4);
        sll.insertLinkedList(6,5); //d
        // 1, 2, 3, 4, 5, 6
        sll.traverseLinkedList();
        sll.searchNode(5);
        sll.delete(5);
        sll.delete(0);
        sll.delete(2);
        sll.traverseLinkedList(); // Expected values: 2, 3, 5
        // actual: 2, 3, 5

    }
}
