package org.example;

import java.util.LinkedList;

public class UndoStates<E> {
    private LinkedList<E> undoStates;
    private int maxLength;
    private int currentPosition = 0;

    public UndoStates(){
        this.undoStates = new LinkedList<E>();
        this.maxLength = 10;
    }
    public UndoStates(int maxLength){
        this.undoStates = new LinkedList<E>();
        this.maxLength = maxLength;
    }

    private LinkedList<E> getUndoStates(){
        return this.undoStates;
    }
    private int getMaxLength(){
        return this.maxLength;
    }
    private int getCurrentPosition(){
        return this.currentPosition;
    }

    private void setMaxLength(int maxLength){
        this.maxLength = maxLength;
    }

    public E retrieveItemAtCurrentPosition(){
        if (this.undoStates.size() == 0){
            return null;
        }
        return this.undoStates.get(this.currentPosition - 1);
    }

    // Adds an item to the Undo List, and deletes the oldest item if the Undo List's size is above the maximum.
    // If the current position is not at the front of the list, overwrites everything after it prior to adding.
    public void addToUndoList(E element){
        int size = this.undoStates.size();

        if (this.currentPosition < size){
            for (int i = size; i > this.currentPosition; i--) {
                this.undoStates.removeLast();
            }
            this.undoStates.addLast(element);
        } else {
            this.undoStates.addLast(element);

            if (size > this.maxLength){
                this.undoStates.removeFirst();
            } else {
                ++this.currentPosition;
            }
        }
    }

    // Moves the pointer backwards through the undo list - Undo Functionality
    public E moveBackThroughList(){
        --this.currentPosition;
        if (this.currentPosition < 0){
            System.out.println("Can't undo any further!");
            this.currentPosition = 0;
        }
        return retrieveItemAtCurrentPosition();
    }
    // Moves the pointer forwards through the undo list - Redo functionality
    public E moveForwardThroughList(){
        int size = this.undoStates.size();
        ++this.currentPosition;
        if (this.currentPosition > size){
            System.out.println("Can't redo any further!");
            this.currentPosition = size;
        }
        return retrieveItemAtCurrentPosition();
    }
}
