import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Tree.BST;
import Tree.AVL;
import Tree.BinaryTree;
import Tree.Node;
import parser.Parser;
import parser.TokenType;

//================================================================================
//  GRAMMAR
// ================================================================================
//  <data>         ::= ((<scope> | <key> | <comment>)* <blank_line>)*
//  <scope>        ::= <identifier> (<blank> | <blank_line>)* "(" <blank_line>+ <data>* <blank> ")"
//  <key>          ::= <identifier> <blank> "=" <blank> <value>
//  <identifier>   ::= <string>
//  <value>        ::= <string>
//  <comment>      ::= "#" <string>

//  <string>       ::= <char>+
//  <char>         ::= <basic_latin> | <latin_1_supp> | <whitespace>
//  <basic_latin>  ::= [\u0020-\u007F]  ; Unicode Basic Latin
//  <latin_1_supp> ::= [\u00A0-\u00FF]  ; Unicode Latin-1 Supplement

//  <blank_line>   ::= <blank> <newline>
//  <blank>        ::= <whitespace>*
//  <whitespace>   ::= " " | "\t"
//  <newline>      ::= "\n" | "\r" | "\r\n"

public class Program {
	public static void main(String[] args) {
		
		showHeader();

        Scanner s = new Scanner(System.in);

		boolean loadedFile = false;
		AVL avl = new AVL();
		BST bst = new BST();
		int opt = 0;

		do {
			opt = readOption(s);

			switch(opt) {
				// Load data from a file
				case 1 -> {
					System.out.println("""

						==============================================================
							      1. Load data from an \".ed2\" file
						==============================================================
						""");
					// System.out.print("Enter the path of the .ed2 file: ");
					// String filePath = s.nextLine();

					// List<String> ed2File = readFile(filePath);
					List<String> ed2File = readFile("validExample.ed2");

					System.out.println("> File read with Success!\n");
					BinaryTree[] binaryTrees = testParser(ed2File);

					avl = (AVL) binaryTrees[0];
					bst = (BST) binaryTrees[1];

					avl.inOrderTraversal();
					bst.inOrderTraversal();

					loadedFile = true;
				}

				// Search a key or a scope
				case 2 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								2. Search for a key/scope in tree
						==============================================================
						""");
						System.out.print("Name of the key/scope: ");
						String identifier = s.nextLine();

						System.out.println("> Using an AVL to search for key/scope \"" + identifier + "\"\n");
						avl.searchNodes(identifier);
						System.out.println("> Using a BST to search for key/scope \"" + identifier + "\"\n");
						bst.searchNodes(identifier);
					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// Insert a key or scope
				case 3 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								3. Insert a key/scope in a tree
						==============================================================
						""");

						int keyOrScope = 0;

						System.out.println("> Do you want to insert a key or a scope?");
						do {
							try {
								System.out.println("1. Key\n2. Scope\n0. Exit option");
								System.out.print("Type the number of the corresponding option: ");

								keyOrScope = Integer.parseInt(s.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("Select a number between 1-2 or 0 to exit this option.");
							}
						} while ((keyOrScope < 1 || keyOrScope > 2) && keyOrScope != 0);

						if (keyOrScope == 0) continue;

						System.out.print("Enter the name of your key/scope:");
						String identifier = s.nextLine().trim();

						System.out.println("\n> Enter the scope path where you want your key/scope to be placed. Consider the example below");
						System.out.println("> E.g.: scopeX/scopeY/~");
						System.out.println("> Every path must end with a \"/~\", indicating the global scope");

						System.out.print("Scope path: ");
						String scope = s.nextLine().trim();

						try {
							avl.search(scope);
							bst.search(scope);
						} catch (RuntimeException e) {
							System.out.println("Invalid scope path: " + e.getMessage());
							continue;
						}

						if (keyOrScope == 1) {
							System.out.println("Enter the value of the key: ");
							String value = s.nextLine();

							try {
								avl.insert(new Node(identifier.concat("/" + scope), TokenType.KEY.toString(), value.trim()));
								bst.insert(new Node(identifier.concat("/" + scope), TokenType.KEY.toString(), value.trim()));
							} catch (RuntimeException e) {
								System.out.println(e.getMessage());
								continue;
							}
						} else {
							try {
								avl.insert(new Node(identifier.concat("/" + scope), TokenType.SCOPE.toString()));
								bst.insert(new Node(identifier.concat("/" + scope), TokenType.KEY.toString()));
							} catch (RuntimeException e) {
								System.out.println(e.getMessage());
								continue;
							}
						}
						System.out.println("> Insertion made successfully");
					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// Change a key from the tree
				case 4 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								4. Update a tree's key
						==============================================================
						""");


					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// Remove a key from the tree
				case 5 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								5. Remove a tree's key
						==============================================================
						""");
					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// Save data in a file
				case 6 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								6. Save data in a file
						==============================================================
						""");
					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// Show BST's content and properties
				case 7 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								7. Show contents and properties of the BST
						==============================================================
						""");
						System.out.println("> Tree stats:");
						System.out.println("Tree's height: " + bst.getHeight());
						System.out.println("Tree's degree: " + bst.getDegree());
						System.out.println("Number of nodes: " + bst.getSize());

						System.out.println("\n> Pre-order traversal:\n");
						bst.preOrderNode();

						System.out.println("\n> In-order traversal:\n");
						bst.inOrderNode();

						System.out.println("\n> Post-order traversal:\n");
						bst.postOrderNode();
						
					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// Show AVL
				case 8 -> {
					// Verify if file content was loaded into memory
					if (loadedFile) {
						System.out.println("""

						==============================================================
								8. Show contents and properties of the AVL
						==============================================================
						""");
						System.out.println("> Tree stats:");
						System.out.println("Tree's height: " + avl.getHeight());
						System.out.println("Tree's degree: " + avl.getDegree());
						System.out.println("Number of nodes: " + avl.getSize());

						System.out.println("\n> Pre-order traversal:\n");
						avl.preOrderNode();

						System.out.println("\n> In-order traversal:\n");
						avl.inOrderNode();

						System.out.println("\n> Post-order traversal:\n");
						avl.postOrderNode();
					} else System.out.println("File not loaded. Please, provide a valid file and select option 1 to load the file's contents into memory.");
				}

				// End program
				case 9 -> {
					s.close();
					System.out.println("Ending Program. . .\nEND");
				}
			}
		} while (opt != 9);        
    }

	public static int readOption(Scanner s) {
        int opt = 0;
        do {
			try {
				showMenu();
                opt = Integer.parseInt(s.nextLine());
                if (opt < 1 || opt > 9) System.out.println("Option must be a an integer number between 1-9.");
            } catch (NumberFormatException e) {
                System.out.println("Option must be an integer number between 1-9.");
                continue;
            }
        } while (opt < 1 || opt > 9);

        return opt;
    }

	public static List<String> readFile(String filePath) {
		List<String> ed2File = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(filePath);
			Scanner scanner = new Scanner(fileReader);

			// Read the file line by line and store its contents in the List<String>

			while (scanner.hasNextLine()) {
				ed2File.add(scanner.nextLine());
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("No such file \""+ filePath + "\"");
		}

		return ed2File;
	}

	public static BinaryTree[] testParser(List<String> contents) {
		Parser parser = new Parser();
		try {
			return parser.run(contents);
		} catch(RuntimeException e) {
			System.out.println();
			System.out.println("The content is not grammatically correct:");
			System.out.println("  > " + e.getMessage());
			System.out.println();
			return null;
		}
	}

	public static void showHeader() {
        System.out.println("""
                =================================================================================
                                                LEXER PARSER
                =================================================================================   
                """);
    }

	public static void showMenu() {
        System.out.print("""
			==========================================================
					Lexer and Parser
			==========================================================
			1. Load data from an \".ed2\" file
			2. Search for a key/scope in tree
			3. Insert a key/scope in a tree
			4. Update a tree's key
			5. Remove a tree's key
			6. Save data in a file
			7. Show contents and properties of the BST
			8. Show contents and properties of the AVL
			9. End program
			Option:\s""");
	}
}
