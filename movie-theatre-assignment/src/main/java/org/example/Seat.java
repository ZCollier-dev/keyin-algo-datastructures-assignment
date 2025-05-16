package org.example;

public class Seat {
    //attributes
    private String rowString;
    private int columnNum;
    private boolean isAvailable = true;

    //methods
    public Seat(String rowString, int columnNum) {
        this.rowString = rowString;
        this.columnNum = columnNum;
    }

    public String getRowCharacter(){
        return this.rowString;
    }
    public int getColumnNumber(){
        return this.columnNum;
    }
    public boolean getAvailability(){
        return this.isAvailable;
    }

    public void setAvailability(boolean newAvailability){
        this.isAvailable = newAvailability;
    }

    public String toString(){
        return this.rowString + ":" + this.columnNum;
    }
}
