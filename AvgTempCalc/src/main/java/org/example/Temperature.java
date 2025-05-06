package org.example;

public class Temperature {
    private double[] tempsArray;
    private double tempsAvg = 0;
    private double[] tempsHigher;

    public Temperature(double[] tempArray){
        this.tempsArray = tempArray;
        CalcTempAvg();
        FindHighTemps();
    }

    // calculates temperature average
    public void CalcTempAvg(){
        for (int i = 0; i < this.tempsArray.length; ++i){
            this.tempsAvg += this.tempsArray[i];
        }
        this.tempsAvg /= this.tempsArray.length;
    }

    // searches for temperatures higher than the average temperature
    public void FindHighTemps(){
        int quantity = 0;
        int pointer = 0;

        for (int i = 0; i < this.tempsArray.length; ++i){
            if (this.tempsArray[i] > this.tempsAvg){
                ++quantity;
            }
        }
        this.tempsHigher = new double[quantity];
        for (int i = 0; i < this.tempsArray.length; ++i){
            if (this.tempsArray[i] > this.tempsAvg){
                this.tempsHigher[pointer] = this.tempsArray[i];
                ++pointer;
            }
        }
    }

    // returns a formatted string containing all info
    public String toString(){
        return "Temperatures: " + this.tempsArray.toString() + "\nAverage temperature: " + this.tempsAvg + "\nTemperatures over average: " + this.tempsHigher.toString();
    }
}
