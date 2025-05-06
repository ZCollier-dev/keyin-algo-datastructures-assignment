package org.example;

import java.util.Objects;
import java.util.Scanner;

// take user inputs (scanner)
// judging by wording, ask for number of temperatures
// calculate average temperature (sum of temperates / length of array)
// determine which temps are greater than the average temperature

public class Main {
    public static void main(String[] args) {
        int arrayLength;
        double[] tempsArray;
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

            // temperature object - calculates everything on initialize
            Temperature temps = new Temperature(tempsArray);

            // output
            System.out.println(temps);

                while (true) {
                    System.out.print("Continue using this program? ('n' to exit, 'y' to continue) ");
                    exitChoice = scanner.nextLine();
                    System.out.println(exitChoice);
                    if (Objects.equals(exitChoice, "y") || Objects.equals(exitChoice, "n")) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter either 'n' to exit or 'y' to continue.");
                    }
                }
            } while (!exitChoice.equals("n"));
        } catch (Exception err) {
            System.out.println("Error: " + err);
        }
    }
}