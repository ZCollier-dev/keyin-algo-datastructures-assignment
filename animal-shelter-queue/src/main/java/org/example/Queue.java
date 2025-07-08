package org.example;

public class Queue {
    // attributes
    private Pet[] petQueue; // the array which holds the queue data
    private int backOfQueue = -1; // the far end of the queue - the newest item
    private int numberOfItems = 0; // the number of items in the queue
    private int frontOfQueue = -1; // the close end of the queue - the oldest item

    // methods
    public Queue(int size){
        this.petQueue = new Pet[size];
        System.out.println("Queue created. Size: " + size);
    }

    public boolean isFull(){
        if (backOfQueue == petQueue.length - 1){
            return true;
        } else {
            return false;
        }
    }
    public boolean isEmpty(){
        return numberOfItems == 0;
    }


}
