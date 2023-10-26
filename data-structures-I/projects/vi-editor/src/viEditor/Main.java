package viEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List list = new List();
        String cmd;

        do {
            cmd = scanner.nextLine();

            switch(cmd.charAt(1)) {
                // Open file, read its content and store each line in a node of the list
                case 'e' -> {
                    if (readFile(list, "src-code-c"))
                        System.out.println("File was successfully read and data stored in the list.");
                    else
                        System.out.println("Error while reading file or storing data in the list.");
                }
                // Store the content of the list in a file
                case 'w' -> {
                    if (writeFile(list, "src-code-c"))
                        System.out.println("Data from the list was successfully stored in the file.");
                    else
                        System.out.println("Error while storing data from the list in the file.");
                }
                // Quit program
                case 'q' -> {
                    System.out.print("Exit without saving? [Y/N]");
                    String ans = scanner.next();

                    if (ans.charAt(0) == 'Y') quit();
                }
                // Select text from line x to line y and print it
                case 'v' -> {

                    String[] substrings = cmd.split(" ");

                    if (substrings.length >= 3 && ":v".equals(substrings[0])) {
                        try {
                            int x = Integer.parseInt(substrings[1]);
                            int y = Integer.parseInt(substrings[2]);

                            printSelectedText(list, x, y);

                        } catch (NumberFormatException e) { System.out.println("Invalid input."); }
                    } else System.out.println("Invalid input.");

                }

                case 'y' -> {

                }

                case 'c' -> {

                }

                case 'p' -> {

                }

                case 's' -> {

                }

                case 'x' -> {

                }

                case 'X' -> {

                }

                case '/' -> {

                }

                case 'a' -> {

                }

                case 'i' -> {

                }

                case 'h' -> {

                }
            }
        }
        while (!Objects.equals(cmd, ":q!"));

        // Close scanner
        scanner.close();

    }

    public static boolean readFile(List list, String fileName) {
        try (BufferedReader fileScan = new BufferedReader(new FileReader(fileName))) {
            String line;

            // While file is not over, store each line in a node at the end of the list
            while ((line = fileScan.readLine()) != null) list.append(line);
            return true;

            // If there's an error while reading the file or storing data
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeFile(List list, String fileName) {
        try (BufferedWriter fileScan = new BufferedWriter(new FileWriter(fileName))) {
            Node temp = list.getHead();

            do {
                fileScan.write(temp.getData());
                fileScan.newLine();
                temp = temp.getNext();
            } while (temp != list.getHead());

            return true;

            // If there's an error while writing in the file
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void quit() {
        System.out.println("Exiting...");
    }

    public static void printSelectedText(List list, int x, int y) {

        // If list is empty
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            // Traverse the list and print its content from line x to line y
        } else {
            // Create a pointer that starts at head
            Node currentPointer = new Node();
            currentPointer = list.getHead();

            // Counter to check the line number
            int counter = 1;

            // While the list isn't over
            do {

            }
            while ();

        }



    }

}