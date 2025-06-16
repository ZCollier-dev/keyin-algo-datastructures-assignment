package org.example;

public class Main {
    public static void main(String[] args) {
        TestObject testObject = new TestObject(1, "Zack", true);
        UndoStates<TestObject> undoStates = new UndoStates<>(5, TestObject::cloneObject);

        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        testObject.setIsAvailable(false);
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        testObject.setName("Zachary");
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        testObject.setIsAvailable(true);
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        testObject.setIsAvailable(false);
        testObject.setName("Za");
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        undoStates.moveBackThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveBackThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        testObject.setName("Z");
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        undoStates.moveBackThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveBackThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveBackThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveBackThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveForwardThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveForwardThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveForwardThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveForwardThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        undoStates.moveForwardThroughList();
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        testObject.setName("Zac");
        testObject.setIsAvailable(true);
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());
        testObject.setIsAvailable(false);
        undoStates.addToUndoList(testObject);
        System.out.println(undoStates.retrieveItemAtCurrentPosition().toString());

        System.out.println();

        System.out.println(undoStates);
    }
}
