// João Pedro Rodrigues Vieira          RA 32281730
// Sabrina Midori F. T. de Carvalho     RA 42249511

package Josephus;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        showHeader();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        LinkedList list = null;

        // Do-while option isn't 5
        do {
            showMenu();

            // Do-while option is invalid (not integer or value out of range 1-5)
            do {
                System.out.print("Option: ");

                // Check if input is an integer
                boolean integerValue = false;
                try {
                    // Read input from user
                    String input = scanner.nextLine().trim();
                    option = Integer.parseInt(input);
                    integerValue = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option - it must be an integer value. Please try again.");
                }

                // Check if option is a valid number
                if (integerValue && (option < 1 || option > 5))
                    System.out.println("Invalid option - value must be between 1-5. Please try again.");

            } while (option < 1 || option > 5);

            // Perform the correspondent operation
            switch(option) {

                // Create an empty list
                case 1 -> {
                    list = new LinkedList();

                    if (list != null) { System.out.println("\nThe list was successfully created."); }
                }

                // Add soldier
                case 2 -> {
                    boolean add = true;

                    if (list == null) {
                        System.out.println("Create a list (option 1) in order to run this option.");
                        continue;
                    }

                    // Do-while add == true
                    do {
                        boolean validName = false;
                        boolean validId = false;
                        String name = null;
                        int id = 0;

                        // Do-while validName == false
                        do {
                            // Read soldier name
                            System.out.print("Name: ");

                            try {
                                name = scanner.nextLine().trim();

                                // If name contains only letters and spaces
                                if (name.matches("^[a-zA-Z\\s]*[a-zA-Z]+[a-zA-Z\\s]*$")) validName = true;
                                // If name contains symbols or digits
                                else System.out.println("Name must contain only letters (at least one) and spaces. Please try again.");

                            } catch (Exception e) {
                                System.out.println("Name must contain only letters (at least one) and spaces. Please try again.");
                            }
                        } while (!validName);

                        // Do-while validId == false
                        do {
                            // Read soldier ID
                            System.out.print("ID: ");

                            try {
                                // If ID contains only digits
                                String input = scanner.nextLine().trim();
                                id = Integer.parseInt(input);
                                validId = true;

                            // If ID contains symbols or letters
                            } catch (Exception e) {
                                System.out.println("ID must contain only digits. Please try again.");
                            }
                        } while (!validId);

                        // Create new soldier
                        Soldier soldier = new Soldier(name, id);

                        // Try to add new soldier to list
                        if (list.insertHead(soldier)) {
                            System.out.println("Soldier successfully added. Do you wish to continue? [Y/N]");

                            String ans = scanner.next().toUpperCase();
                            // Consume newline character
                            scanner.nextLine();

                            add = Objects.equals(ans, "Y");
                        } else {
                            System.out.println("The list is full, could not add new soldier.");
                            add = false;
                        }
                    } while (add);
                }

                // Print soldiers
                case 3 -> {
                    if (list == null || list.isEmpty()) {
                        System.out.println("The list is empty.\nCreate one (option 1) and add soldiers (option 2) in order to run this option.");
                        continue;
                    }
                    list.print();
                }

                // Remove soldier
                case 4 -> {
                    // This option does not run if the list is empty
                    if (list == null || list.isEmpty()) {
                        System.out.println("The list is empty.\nCreate one (option 1) and add soldiers (option 2) in order to run this option.");
                        continue;
                    }

                    // Get soldier's name
                    System.out.print("Name the soldier who is going to be the first of the counting: ");
                    String name = scanner.nextLine();

                    Node selectedSoldier = list.search(name);
                    if (selectedSoldier == null) {
                        System.out.println("There is no soldier with given name. Please re-enter a name.");
                        continue;
                    } else {
                        System.out.println("Soldier found successfully.");
                        System.out.printf("Name: %s\n", selectedSoldier.getSoldier().getName());
                        System.out.printf("ID: %d\n", selectedSoldier.getSoldier().getId());
                    }

                    // Repeat while list has more than one soldier
                    while (list.getCount() > 1) {
                        // Create a random object
                        Random random = new Random();

                        // Generate a random number between 1 and 100
                        int randomNumber1 = random.nextInt(100) + 1;
                        System.out.println("\n1st Random Number: " + randomNumber1);

                        int count = 0;

                        // Keep iterating through the list until the soldier in the random position is selected
                        while (count != randomNumber1) {
                            selectedSoldier = selectedSoldier.getNext();
                            count++;
                        }

                        // Showing off the selected soldier's information
                        System.out.printf("\nName of the soldier who is going to be removed: %s\n", selectedSoldier.getSoldier().getName());
                        System.out.printf("Soldier's ID: %d\n", selectedSoldier.getSoldier().getId());

                        Node removedSoldier = selectedSoldier;
                        selectedSoldier = selectedSoldier.getNext();

                        if (list.remove(removedSoldier.getSoldier())) { System.out.println("\nRemoval done successfully."); }
                        else { System.out.println("\nThere is no soldier with such a name."); }
                    }

                    // Showing off the survivor's information
                    if (list.getHead() != null) {
                        Node survivor = list.getHead();
                        System.out.printf("\nThe soldier %s, with ID %d, was saved and will take the horse.\n", survivor.getSoldier().getName(), survivor.getSoldier().getId());
                    }
                }

                // End program
                case 5 -> {
                    System.out.println("\nEnding Program. . .\nE N D");
                }
            }

        } while (option != 5);
    }

    public static void showHeader() {
        System.out.println("""
                *************************************************************************************
                                             WELCOME TO JOSEPHUS PROBLEM
                *************************************************************************************
                """);
    }

    public static void showMenu() {
        System.out.println("""
                =====================================================================================
                Choose an option:
                
                1. Start
                2. Insert Soldier
                3. Show Soldiers
                4. Remove Soldier
                5. Exit
                =====================================================================================
                """);
    }
}
