package org.example;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.function.Function;

public class UndoStates<E> {
    private LinkedList<E> undoStates = new LinkedList<E>();
    private ListIterator<E> iterator = this.undoStates.listIterator();
    private int maxLength;
    private Function<E, E> copier;

    public UndoStates(Function<E, E> copier){
        this.maxLength = 10;
        this.copier = copier;
    }
    public UndoStates(int maxLength, Function<E, E> copier){
        this.maxLength = maxLength;
        this.copier = copier;
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
        E object;

        if (this.undoStates.isEmpty()){
            object = null;
        } else if (!this.iterator.hasNext()){
            object = this.iterator.previous();
            this.iterator.next();
        } else {
            object = this.iterator.next();
            this.iterator.previous();
        }

        return object;
    }

    // Adds an item to the Undo List, and deletes the oldest item if the Undo List's size is above the maximum.
    // If the current position is not at the front of the list, overwrites everything after it prior to adding.
    public void addToUndoList(E element){
        if (this.iterator.hasNext()){
            System.out.println("Clearing List");

            while (this.iterator.hasNext()){
                this.iterator.next();
                this.iterator.remove();
            }
        }

        this.iterator.add(copier.apply(element));

        if (this.undoStates.size() > this.maxLength){
            System.out.println("Oversized");
            while (this.iterator.hasPrevious()){
                this.iterator.previous();
            }
            this.iterator.remove();
            while (this.iterator.hasNext()){
                this.iterator.next();
            }
        }
    }

    // Moves the pointer backwards through the undo list - Undo Functionality
    public E moveBackThroughList(){
        try {
            if (this.iterator.hasPrevious()){
                System.out.println("prev");
                this.iterator.previous();

                if (this.iterator.previousIndex() != -1){
                    return this.undoStates.get(this.iterator.previousIndex());
                } else {
                    System.out.println("End of List");
                    return this.undoStates.get(this.iterator.nextIndex());
                }
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
                System.out.println("next");
                this.iterator.next();
                return this.undoStates.get(this.iterator.previousIndex());
            }

            System.out.println("Can't redo any further!");
            return this.undoStates.get(this.iterator.previousIndex());
        } catch (IndexOutOfBoundsException e){
            System.out.println("ERR: Nothing in undo list.");
            return null;
        }
    }

    public String toString(){
        String returnString = "";

        while (this.iterator.hasPrevious()){
            this.iterator.previous();
        }
        while (this.iterator.hasNext()){
            returnString += this.iterator.next().toString() + "\n";
        }

        return returnString;
    }
}
