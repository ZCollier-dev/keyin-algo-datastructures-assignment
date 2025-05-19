package org.example;

public class TheatreSeats {
    //attributes
    private Seat[][] seatingArray;

    //methods
    public TheatreSeats(){
        this.seatingArray = new Seat[5][6];
        populateSeats(5, 6);
    }
    public TheatreSeats(int rows, int columns){
        this.seatingArray = new Seat[rows][columns];
        populateSeats(rows, columns);
    }

    private void populateSeats(int rows, int columns){
        int charASCIICodeSecond = 65; //second character
        int charASCIICodeFirst = 65; //first character, adds +1 to second after 90 then resets
        char[] rowCharArray = new char[2];
        for (int row = 0; row < rows; ++row){
            rowCharArray[0] = (char) charASCIICodeSecond;
            rowCharArray[1] = (char) charASCIICodeFirst;
            ++charASCIICodeFirst;
            if (charASCIICodeFirst == 91){
                charASCIICodeFirst = 65;
                ++charASCIICodeSecond;
            }
            for (int col = 0; col < columns; ++col){
                seatingArray[row][col] = new Seat(new String(rowCharArray), col);
            }
        }
    }

    public String reserveSeat(String seatID){
        String returnString = "";
        if (!parseSeat(seatID)){
            return returnString;
        }

        String rowString = seatID.substring(0, 2).toUpperCase();
        String colString = seatID.substring(2);
        int colNum = Integer.parseInt(colString);
        int rowIndex = 0;

        try {
            for (int row = 0; row <= seatingArray.length; row++) {
                if (row == seatingArray.length){
                    throw new ArrayIndexOutOfBoundsException("ERR: Row not found.");
                }
                if (seatingArray[row][0].getRowString().equals(rowString)){
                    rowIndex = row;
                    break;
                }
            }

            if (seatingArray[rowIndex][colNum].getAvailability()){
                seatingArray[rowIndex][colNum].setAvailability(false);
                returnString = "Seat " + seatID + " has been reserved.";
            } else {
                int biggerCol = colNum;
                int smallerCol = colNum;
                int biggerRow = rowIndex;
                int smallerRow = rowIndex;
                String suggestedSeatID = null;

                do {
                    if (biggerCol == seatingArray[0].length - 1 && smallerCol == 0){
                        ++biggerRow;
                        if (biggerRow >= seatingArray.length){
                            biggerRow = seatingArray.length - 1;
                        }
                        --smallerRow;
                        if (smallerRow < 0){
                            smallerRow = 0;
                        }
                        biggerCol = colNum;
                        smallerCol = colNum;
                    } else {
                        ++biggerCol;
                        if (biggerCol >= seatingArray[0].length){
                            biggerCol = seatingArray[0].length - 1;
                        }
                        --smallerCol;
                        if (smallerCol < 0){
                            smallerCol = 0;
                        }
                    }

                    if (seatingArray[biggerRow][biggerCol].getAvailability()){
                        suggestedSeatID = seatingArray[biggerRow][biggerCol].toString();
                    } else if (seatingArray[biggerRow][smallerCol].getAvailability()){
                        suggestedSeatID = seatingArray[biggerRow][smallerCol].toString();
                    } else if (seatingArray[smallerRow][biggerCol].getAvailability()){
                        suggestedSeatID = seatingArray[smallerRow][biggerCol].toString();
                    } else if (seatingArray[smallerRow][smallerCol].getAvailability()){
                        suggestedSeatID = seatingArray[smallerRow][smallerCol].toString();
                    }

                } while ((biggerRow < seatingArray[0].length && smallerRow > 0 && biggerCol < seatingArray.length && smallerCol > 0) || suggestedSeatID == null);
                if (suggestedSeatID != null){
                    returnString = "Seat " + seatID + " is already taken. Maybe " + suggestedSeatID + " would work instead?";
                } else {
                    returnString = "The theatre is full.";
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            returnString = "ERR: Row/Column not found.";
            return returnString;
        }

        return returnString;
    }

    public String cancelSeat(String seatID){
        String returnString = "";
        if (!parseSeat(seatID)){
            return returnString;
        }
        String rowString = seatID.substring(0, 2).toUpperCase();
        String colString = seatID.substring(2);
        int colNum = Integer.parseInt(colString);
        int rowIndex = 0;

        try {
            for (int row = 0; row <= seatingArray.length; row++) {
                if (row == seatingArray.length){
                    throw new ArrayIndexOutOfBoundsException("ERR: Row not found.");
                }
                if (seatingArray[row][0].getRowString().equals(rowString)){
                    rowIndex = row;
                    break;
                }
            }

            if (!seatingArray[rowIndex][colNum].getAvailability()){
                seatingArray[rowIndex][colNum].setAvailability(true);
                returnString = "Seat " + seatID + " has been cancelled.";
            } else {
                returnString = "Seat " + seatID + " is already freed.";
            }
        } catch (ArrayIndexOutOfBoundsException e){
            returnString = "ERR: Row/Column not found.";
            return returnString;
        }

        return returnString;
    }

    private boolean parseSeat(String seatID){
        String rowString;
        String colString;
        try {
            rowString = seatID.substring(0, 2).toUpperCase();
            colString = seatID.substring(2);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("ERR: Input string is under 3 characters long. Please enter your seat in the format XX##...");
            return false;
        }

        if (!rowString.matches("^[A-Z]+$")){
            System.out.println("ERR: Invalid row. Please enter your seat in the format XX##...");
            return false;
        }

        try {
            int colNumber = Integer.parseInt(colString);
        } catch (NumberFormatException e){
            System.out.println("ERR: Invalid column integer. Please enter your seat in the format XX##...");
            return false;
        }
        return true;
    }

    public String toString(){
        char seatAvailable;
        String seatingChart = "Seating Chart - O = Available, X = Unavailable\n-----------------------------\n";
        for (int row = 0; row < seatingArray.length; ++row){
            seatingChart += "| ";
            for (int col = 0; col < seatingArray[0].length; ++col) {
                if (seatingArray[row][col].getAvailability()){
                    seatAvailable = 'O';
                } else {
                    seatAvailable = 'X';
                }
                seatingChart += seatingArray[row][col].toString() + " " + seatAvailable + " | ";
            }
            seatingChart += "\n";
        }
        return seatingChart;
    }
}
