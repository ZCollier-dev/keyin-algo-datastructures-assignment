package org.example;

public class TestObject {
    private int id;
    private String name;
    private boolean isAvailable;

    public TestObject(int id, String name, boolean isAvailable){
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public boolean getIsAvailable(){
        return this.isAvailable;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public TestObject cloneObject(){
        return new TestObject(this.id, this.name, this.isAvailable);
    }

    public String toString(){
        return "Person: " + this.id + " Name: " + this.name + " Available: " + this.isAvailable;
    }
}
