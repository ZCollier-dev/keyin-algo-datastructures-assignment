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

    public String toString(){
        String returnString = "";
        for (int i = 0; i < petQueue.length; i++) {
            returnString += petQueue[i] + " | ";
        }
        return returnString;
    }

    public boolean isFull(){
        return numberOfItems == petQueue.length;
    }
    public boolean isEmpty(){
        return numberOfItems == 0;
    }

    // enqueuePet - Adds a pet to the queue
    public void enqueuePet(String petName, String petType){
        if (isFull()){
            System.out.println("Queue is full.");
        } else if (isEmpty()){
            frontOfQueue = 0;
            backOfQueue = 0;
            petQueue[backOfQueue] = new Pet(petName, petType);
            numberOfItems++;
            System.out.println(petName + " added to queue.");
        } else {
            backOfQueue++;
            if (backOfQueue == petQueue.length){
                backOfQueue = 0;
            }
            petQueue[backOfQueue] = new Pet(petName, petType);
            numberOfItems++;
            System.out.println(petName + " added to queue.");
        }
    }

    // dequeueAny - Dequeues the pet at the front of the queue.
    public void dequeueAny(){
        if (isEmpty()){
            System.out.println("Queue is empty.");
        } else {
            while (petQueue[frontOfQueue] == null){
                frontOfQueue++;
                if (frontOfQueue == petQueue.length){
                    frontOfQueue = 0;
                }
            }
            Pet selectedPet = petQueue[frontOfQueue];
            petQueue[frontOfQueue] = null;
            frontOfQueue++;
            if (frontOfQueue == petQueue.length){
                frontOfQueue = 0;
            }
            numberOfItems--;
            System.out.println("Pet Dequeued: " + selectedPet);
        }
    }

    // dequeueSpecies - Dequeues the frontmost selected species from the queue
    public void dequeueSpecies(String species){
        if (isEmpty()){
            System.out.println("Queue is empty.");
        } else {
            int pointer = frontOfQueue;
            Pet selectedPet = null;
            for (int i = 0; i < numberOfItems; i++) {
                while (petQueue[pointer] == null){
                    pointer++;
                    if (pointer == petQueue.length){
                        pointer = 0;
                    }
                }
                if (petQueue[pointer].getPetType().equalsIgnoreCase(species)){
                    selectedPet = petQueue[pointer];
                    petQueue[pointer] = null;
                    if (pointer == backOfQueue){
                        backOfQueue--;
                        if (backOfQueue == -1){
                            backOfQueue = petQueue.length - 1;
                        }
                    } else if (pointer == frontOfQueue){
                        frontOfQueue++;
                        if (frontOfQueue == petQueue.length){
                            frontOfQueue = 0;
                        }
                    } else {
                        sortQueue();
                    }
                    break;
                } else {
                    pointer++;
                    if (pointer == petQueue.length){
                        pointer = 0;
                    }
                }
            }
            if (selectedPet == null){
                System.out.println("Species not found.");
            } else {
                numberOfItems--;
                System.out.println(species + " found and dequeued: " + selectedPet);
            }
        }
    }

    // sortQueue - remove nulls from the middle of queue
    public void sortQueue(){
        if (isEmpty()){
            System.out.println("Queue is empty.");
        } else {
            Pet[] newQueue = new Pet[petQueue.length];
            int queuePos = frontOfQueue;
            for (int i = 0; i < numberOfItems - 1; i++) {
                while (petQueue[queuePos] == null){
                    ++queuePos;
                    if (queuePos == petQueue.length){
                        queuePos = 0;
                    }
                }
                newQueue[i] = petQueue[queuePos];
                ++queuePos;
                if (queuePos == petQueue.length){
                    queuePos = 0;
                }
            }
            petQueue = newQueue;
            frontOfQueue = 0;
            backOfQueue = numberOfItems - 1;
        }
    }
}
