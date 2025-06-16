package org.example;

import java.util.LinkedList;
import java.util.ListIterator;

public class UndoStates<E> {
    private LinkedList<E> undoStates = new LinkedList<E>();
    private ListIterator<E> iterator = this.undoStates.listIterator();
    private int maxLength;

    public UndoStates(){
        this.maxLength = 10;
    }
    public UndoStates(int maxLength){
        this.maxLength = maxLength;
    }

    private LinkedList<E> getUndoStates(){
        return this.undoStates;
    }
    private int getMaxLength(){
        return this.maxLength;
    }

    private void setMaxLength(int maxLength){
        this.maxLength = maxLength;
    }

    public E retrieveItemAtCurrentPosition(){
        if (this.undoStates.isEmpty()){
            return null;
        }
        return this.undoStates.get(this.iterator.previousIndex());
    }

    // Adds an item to the Undo List, and deletes the oldest item if the Undo List's size is above the maximum.
    // If the current position is not at the front of the list, overwrites everything after it prior to adding.
    public void addToUndoList(E element){
        if (this.iterator.hasNext()){
            while (this.iterator.hasNext()){
                this.iterator.next();
                this.iterator.remove();
            }

            this.iterator.add(element);
        } else {
            this.iterator.add(element);

            if (this.undoStates.size() > this.maxLength){
                while (this.iterator.hasPrevious()){
                    this.iterator.previous();
                }
                this.iterator.remove();
                while (this.iterator.hasNext()){
                    this.iterator.next();
                }
            }
        }
    }

    // Moves the pointer backwards through the undo list - Undo Functionality
    public E moveBackThroughList(){
        try {
            if (this.iterator.hasPrevious()){
                return this.iterator.previous();
            }

            System.out.println("Can't undo any further!");
            return this.undoStates.get(this.iterator.nextIndex());
        } catch (IndexOutOfBoundsException e){
            System.out.println("ERR: Nothing in undo list.");
            return null;
        }
    }
    // Moves the pointer forwards through the undo list - Redo functionality
    public E moveForwardThroughList(){
        try {
            if (this.iterator.hasNext()){
                return this.iterator.next();
            }

            System.out.println("Can't redo any further!");
            return this.undoStates.get(this.iterator.previousIndex());
        } catch (IndexOutOfBoundsException e){
            System.out.println("ERR: Nothing in undo list.");
            return null;
        }
    }
}
