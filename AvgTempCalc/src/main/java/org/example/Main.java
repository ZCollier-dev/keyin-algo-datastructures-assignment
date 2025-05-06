package org.example;

import java.util.Scanner;

// take user inputs (scanner)
// judging by wording, ask for number of temperatures
// calculate average temperature (sum of temperates / length of array)
// determine which temps are greater than the average temperature

public class Main {
    public static void main(String[] args) {
        int arrayLength;
        double averageTemp;
        double[] tempsArray;
        int higherTempCount;
        int higherTempPointer;
        double[] isHigherTemp;
        String exitChoice;

        try (Scanner scanner = new Scanner(System.in)) {
            do {
            System.out.println("===Average Temperature Calculator===");
            System.out.print("Enter number of daily temperature values to calculate: ");

            // arrayLength input validation
            while (true) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    scanner.next();
                } else {
                    arrayLength = scanner.nextInt();
                    if (arrayLength <= 0){
                        System.out.println("Invalid input. Please enter a positive integer.");
                        scanner.next();
                    } else {
                        break;
                    }
                }
            }

            // create array with length arrayLength
            tempsArray = new double[arrayLength];
            scanner.nextLine();

            // Input each temperature
            for (int i = 0; i < arrayLength; ++i) {
                System.out.print(String.format("Enter temperate %d: ", i));

                //input validation
                while (true) {
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                    } else {
                        tempsArray[i] = scanner.nextDouble();
                        scanner.nextLine();
                        break;
                    }
                }
            }

            averageTemp = 0;
            // calculate average temperature with sum of array values / array length
            for (int i = 0; i < arrayLength; ++i){
                averageTemp += tempsArray[i];
            }
            averageTemp /= arrayLength;

            // determine which temps are above average
            higherTempCount = 0;
            for (int i = 0; i < arrayLength; ++i){
                if (tempsArray[i] > averageTemp){
                    ++higherTempCount;
                }
            }
            isHigherTemp = new double[higherTempCount];
            higherTempPointer = 0;
            for (int i = 0; i < arrayLength; ++i){
                if (tempsArray[i] > averageTemp){
                    isHigherTemp[higherTempPointer] = tempsArray[i];
                    ++higherTempPointer;
                }
            }

            System.out.println("Temperatures: " + tempsArray.toString());
            System.out.println("Average temperature: " + averageTemp);
            System.out.println("Temperatures over average: " + isHigherTemp.toString());

            System.out.println("Continue using this program? ('n' to exit, 'y' to continue)");
                while (true) {
                    exitChoice = scanner.nextLine();
                    scanner.nextLine();
                    if (exitChoice != "y" || exitChoice != "n") {
                        System.out.println("Invalid input. Please enter either 'n' to exit or 'y' to continue.");
                    } else {
                        break;
                    }
                }
            } while (exitChoice != "n");
        } catch (Exception err) {
            System.out.println("Error: " + err);
        }
    }
}