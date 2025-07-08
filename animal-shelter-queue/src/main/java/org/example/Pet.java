package org.example;

public class Pet {
    // attributes
    private String petType;
    private String petName;

    // methods
    public Pet(String petName, String petType){
        this.petName = petName;
        this.petType = petType;
    }

    public String getPetType(){
        return this.petType;
    }
    public String getPetName(){
        return this.petName;
    }

    public void setPetName(String newName){
        this.petName = newName;
    }

    public String toString(){
        return petName + " - " + petType;
    }
}
