/* Estrutura de Dados II - Atividade: Revisão POO com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: https://github.com/JPedroRodrigues/vi-editor.git
 * Referência: https://github.com/JPedroRodrigues/learning-java/tree/main/data-structures-I/lab/josephus
 */

import java.io.*;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);
        String str = "";
        String [] lines;

        while (true) {
            showMenu();

            String opt;
            int option;

            // Read user input and try to convert the string into a number
            while (true) {
                opt = s.nextLine();
                try {
                    option = Integer.parseInt(opt);
                    if (option < 1 || option > 3) { System.out.println("\nInvalid option. Value must be between 1-3. Try again."); }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.print("\nInvalid option. Value must be between 1-3. Try again.\nOption: ");
                    continue;
                }
                break;
            }

            switch(option) {
                // Load data
                case 1 -> {
                    str = loadTextFileToString("./imdb.txt");

                    if (str.isEmpty()) System.out.println("\nData could not be loaded.");
                    else System.out.println("\nData successfully loaded.");
                }

                // Show data
                case 2 -> {
                    if (str.isEmpty()) {
                        System.out.println("\nData not loaded. Select option 1 to load data.");
                        continue;
                    }
                    lines = str.split("#|\\r?\\n|//r");

                    LinkedList list = new LinkedList();
                    int count = 1;

                    for (int i = 0; i < lines.length; i += 3) {
                        Movie movie = new Movie();

                        movie.setTitle(lines[i]);
                        movie.setYear(Integer.parseInt(lines[i + 1]));
                        movie.setRating(Float.parseFloat(lines[i + 2]));

                        list.append(movie);
                        System.out.println("#" + (count++) + " " + movie);
                    }
                }

                // End program
                case 3 -> {
                    System.out.println("\nEnding program...");
                    s.close();
                    return;
                }
            }
        }
    }

    public static void showMenu() {
        System.out.print("""
        =====================================================\s
        1. Load data
        2. Show data
        3. Exit
        
        Option:\s""");
    }

    public static String loadTextFileToString(String filename) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            sb.append(line).append('\n');
        }

        is.close();
        return sb.toString();
    }
}