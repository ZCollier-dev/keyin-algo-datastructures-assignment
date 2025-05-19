package org.example;

import java.util.Scanner;

// Simulate a movie theater that helps users to book and reserve seats.
// reserve seats (if seat is taken, let them know it is taken and suggest an available seat)
// cancel seats
// retrieve initial seating charting

// 2d array for seat grid
// each seat has a number-letter combo (string) (letter row, number column)
// 65-90 ASCII are uppercase letters, 97-122 are lowercase
// use regex to extract user input (extract A-Za-z for row, 0-9 for column)
// search array for letter, then number
// seats are either reserved or not reserved (boolean)

public class Main {
    public static void main(String[] args) {
        TheatreSeats balconySeats = new TheatreSeats();
        TheatreSeats groundSeats = new TheatreSeats(8, 12);
        int choice = -1;

        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Balcony Seats:");
            System.out.println(balconySeats);
            System.out.println("");
            System.out.println("Ground Seats:");
            System.out.println(groundSeats);
            System.out.println("");
            do {
                System.out.println("0. Exit");
                System.out.println("1. Balcony Seats");
                System.out.println("2. Ground Seats");
                System.out.print("Enter choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter one of the specified choices.");
                    scanner.next();
                } else {
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 0: {
                            // Exit
                            System.out.println("Exiting program...");
                            break;
                        }
                        case 1: {
                            balconySeats = SeatingMenu(scanner, balconySeats);
                            break;
                        }
                        case 2: {
                            groundSeats = SeatingMenu(scanner, groundSeats);
                            break;
                        }
                        default: {
                            System.out.println("ERR: Invalid Choice.");
                        }
                    }
                }
            } while (choice != 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // SeatingMenu - Sub-menu for the specified seating arrangement.
    public static TheatreSeats SeatingMenu(Scanner scanner, TheatreSeats seating){
        String seatID;
        int choice = -1;
        do {
            System.out.println("0. Back");
            System.out.println("1. Reserve Seat");
            System.out.println("2. Cancel Seat");
            System.out.println("3. View Seating Chart");
            System.out.print("Enter choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter one of the specified choices.");
                scanner.next();
            } else {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0: {
                        // Back
                        System.out.println("Returning to main menu...");
                        break;
                    }
                    case 1: {
                        System.out.println("Enter a seat ID to reserve (Two letters followed by a number; XX##): ");
                        seatID = scanner.nextLine();
                        System.out.println(seating.reserveSeat(seatID));
                        break;
                    }
                    case 2: {
                        System.out.println("Enter a seat ID to cancel (Two letters followed by a number; XX##): ");
                        seatID = scanner.nextLine();
                        System.out.println(seating.cancelSeat(seatID));
                        break;
                    }
                    case 3: {
                        System.out.println(seating);
                        break;
                    }
                    default: {
                        System.out.println("ERR: Invalid Choice.");
                    }
                }
            }
        } while (choice != 0);
        return seating;
    }
}