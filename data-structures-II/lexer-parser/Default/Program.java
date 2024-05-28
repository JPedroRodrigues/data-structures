/* APL2 - Lexer & Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Pedro Pessuto Rodrigues Ferreira    10409729
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Default;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
					System.out.print("Enter the path of the .ed2 file: ");
					String filePath = s.nextLine();

					if (!filePath.contains(".ed2")) {
						System.out.println("Invalid input file. It must be an \".ed2\" file.");
						continue;
					}

					List<String> ed2File = readFile(filePath);
					
					// List<String> ed2File = readFile("validExample.ed2");

					System.out.println("> File read with Success!\n");

					
					try {
						BinaryTree[] binaryTrees = testParser(ed2File);

						avl = (AVL) binaryTrees[0];
						bst = (BST) binaryTrees[1];

						loadedFile = true;
					} catch (RuntimeException e) {
						continue;
					}
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

						// Checks if user wants to insert a key or scope or just exit the option
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

						// Instructions to enter a correct path
						System.out.println("\n> Enter the scope path where you want your key/scope to be placed. Consider the example below");
						System.out.println("\n~/scopeX/scopeY\n");
						System.out.println("> The \"~\" symbol indicates the global scope.");
						System.out.println("> To make insertion in the global scope, just type \"~\"");
						System.out.println("> To specify a more complex path, you need to, starting from the global scope, write a slash \"/\" followed by the name of the scope");
						System.out.println("> Every complex path must start with a \"~/\", indicating its origin from de global scope");
						System.out.println("> A path sould not end with a slash \"/\"");
						System.out.print("> Current file's paths: ");
						avl.inOrderTraversal();
						System.out.print("\nScope path: ");

						// If user typed a slash at the end of the path, we just eliminate it by
						// converting the string to array and joining it again
						String scopePath = s.nextLine().trim();
						String[] treadtedScopePath = scopePath.split("/");
						scopePath = String.join("/", treadtedScopePath);
						
						// Searching the scope where the key/scope will be inserted
						try {
							avl.searchScope(scopePath);
							bst.searchScope(scopePath);
						} catch (RuntimeException e) {
							System.out.println("\n>>> Invalid scope path: " + e.getMessage());
							System.out.println();
							continue;
						}
						System.out.println("\n>>> Scope found successfuly!");

						// Reading node's data
						System.out.print("Enter the identifier name: ");
						String identifier = s.nextLine().trim();
						
						if (keyOrScope == 1) {
							System.out.print("Enter the value of the key: ");
							String value = s.nextLine();

							try {
								avl.insert(new Node(scopePath.concat("/" + identifier), TokenType.KEY.toString(), value.trim()));
								bst.insert(new Node(scopePath.concat("/" + identifier), TokenType.KEY.toString(), value.trim()));
							} catch (RuntimeException e) {
								System.out.println(e.getMessage());
								continue;
							}
						} else {
							try {
								avl.insert(new Node(scopePath.concat("/" + identifier), TokenType.SCOPE.toString()));
								bst.insert(new Node(scopePath.concat("/" + identifier), TokenType.SCOPE.toString()));
							} catch (RuntimeException e) {
								System.out.println(e.getMessage());
								continue;
							}
						}

						// Showing results
						System.out.println("\n>>> Insertion made successfully");
						System.out.println("\n>>> Data in BST: ");
						bst.inOrderNode();
						System.out.println("\n>>> Data in AVL: ");
						avl.inOrderNode();
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

						// Instructions to enter a correct path
						System.out.println("\n> Enter the path of the key you want to be updated. Consider the example below");
						System.out.println("\n~/scopeX/scopeY/key\n");
						System.out.println("> The \"~\" symbol indicates the global scope.");
						System.out.println("> To specify a more complex path, you need to, starting from the global scope (\"~\"), type a slash \"/\" followed by the name of the scope or key you want to access");
						System.out.println("> Every complex path must start with a \"~/\", indicating its origin from de global scope");
						System.out.println("> A path sould not end with a slash \"/\"");
						System.out.print("> Current file's paths: ");
						avl.inOrderTraversal();

						// If user typed a slash at the end of the path, we just eliminate it by
						// converting the string to array and joining it again
						System.out.print("\nKey path: ");
						String keyPath = s.nextLine().trim();
						String[] treatedKeyPath = keyPath.split("/");
						keyPath = String.join("/", treatedKeyPath);
						
						try {
							// Searching for the key and showing off results
							Node avlKey = avl.searchKey(keyPath);
							Node bstKey = bst.searchKey(keyPath);

							System.out.println("\n>>> Key found successfuly!");
							System.out.println("\n>>> Node's data in BST: ");
							System.out.println(bstKey);
							System.out.println("\n>>> Node's data in AVL: ");
							System.out.println(avlKey);
							
							System.out.print("Enter the new value of the key: ");
							String value = s.nextLine();

							avlKey.setValue(value);
							bstKey.setValue(value);

							System.out.println("\n>>> Update made successfully");
							System.out.println("\n>>> Node's data in BST: ");
							System.out.println(bstKey);
							System.out.println("\n>>> Node's data in AVL: ");
							System.out.println(avlKey);

						} catch (RuntimeException e) {
							System.out.println("\n>>> Invalid key path: " + e.getMessage());
							System.out.println();
							continue;
						}
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

						// Instructions to enter a correct path
						System.out.println("\n> Enter the path of the key you want to be removed. Consider the example below");
						System.out.println("\n~/scopeX/scopeY/key\n");
						System.out.println("> The \"~\" symbol indicates the global scope.");
						System.out.println("> To specify a more complex path, you need to, starting from the global scope (\"~\"), type a slash \"/\" followed by the name of the scope or key you want to access");
						System.out.println("> Every complex path must start with a \"~/\", indicating its origin from de global scope");
						System.out.println("> A path sould not end with a slash \"/\"");
						System.out.print("> Current file's paths: ");
						avl.inOrderTraversal();

						// If user typed a slash at the end of the path, we just eliminate it by
						// converting the string to array and joining it again
						System.out.print("\nKey path: ");
						String keyPath = s.nextLine().trim();
						String[] treatedKeyPath = keyPath.split("/");
						keyPath = String.join("/", treatedKeyPath);
						
						try {
							// Searching for the key, removing it and showing off the resulting tree
							avl.removeKey(keyPath);
							bst.removeKey(keyPath);

							System.out.println("\n>>> Key removed successfuly!");

							System.out.println("\n>>> Content in both trees");
							System.out.println("\n>>> BST: ");
							System.out.print("BST in order: ");
							bst.inOrderTraversal();
							bst.inOrderNode();

							System.out.println("\n>>> AVL: ");
							System.out.print("AVL in order: ");
							avl.inOrderTraversal();
							avl.inOrderNode();

						} catch (RuntimeException e) {
							System.out.println("\n>>> Invalid key path: " + e.getMessage());
							System.out.println();
							continue;
						}
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

						// Reading user's file
						System.out.print("Enter the name of the \".ed2\" file in which you will save the content: ");
						String filePath = s.nextLine();

						if (!filePath.contains(".ed2")) {
							System.out.println("Invalid input file. It must be an \".ed2\" file.");
							continue;
						}

						writeFile(avl, filePath);
						// writeFile(avl, "output.ed2");

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
						System.out.print("Path sequence: ");
						bst.preOrderTraversal();
						bst.preOrderNode();

						System.out.println("\n> In-order traversal:\n");
						System.out.print("Path sequence: ");
						bst.inOrderTraversal();
						bst.inOrderNode();

						System.out.println("\n> Post-order traversal:\n");
						System.out.print("Path sequence: ");
						bst.postOrderTraversal();
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
						System.out.print("Path sequence: ");
						avl.preOrderTraversal();
						avl.preOrderNode();

						System.out.println("\n> In-order traversal:\n");
						System.out.print("Path sequence: ");
						avl.inOrderTraversal();
						avl.inOrderNode();

						System.out.println("\n> Post-order traversal:\n");
						System.out.print("Path sequence: ");
						avl.postOrderTraversal();
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
		// Validating the input tobe be a number between 1-9
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

	public static boolean writeFile(AVL avl, String filePath) {

        // Try-catch to open file and write tree's content on it
        try (FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			
			// Making an in-order printing
			if (avl.isEmpty()) throw new RuntimeException(">>> Unable to write tree's contents in the file: tree is empty");
			
			// Placing every node in order inside a list
			List<Node> nodeList = new ArrayList<>();
			inOrderStorage(avl.getRoot(), nodeList);
			
			// Keeping track of the number of tabs in order to build a readable text
			int prevTabsNumber = 0;
			int tabsNumber = 0;
			
			for (Node node : nodeList) {
				// Updating the number of tabs used in the previous line
				prevTabsNumber = tabsNumber;

				// The number of tabs is the number of elements, desconsidering the global scope and the current element
				if (!node.getName().equals("~"))
					tabsNumber = (node.getPath().split("/").length) - 2;

				// If there are more tabs in the previous line than the actual
				if (prevTabsNumber > tabsNumber) {
					int currentTab = prevTabsNumber - 1;

					// While the number of tabs is greater or equal than the actual one
					while (currentTab >= tabsNumber) {
						// Add a ")" at the end of every function with the right tab size 
						for (int i = 0; i < currentTab; i++) {
							System.out.print("\t");
							bufferedWriter.write("\t");
						}
						System.out.println(")");

						bufferedWriter.write(")");
						bufferedWriter.newLine();
						currentTab--;
					}
				}

				// Format the right number of tabs
				for (int i = 0; i < tabsNumber; i++) {
					System.out.print("\t");
					bufferedWriter.write("\t");
				}

				// If the node is a scope, then print its name followed by a "("
				if (node.getType().equals("SCOPE") && !node.getName().equals("~")) {
					System.out.print(node.getName() + " (");
					System.out.println();

					bufferedWriter.write(node.getName() + " (");
					bufferedWriter.newLine();
				// If the node is a key, then print its name followed by a "=" and its value
				} else if (node.getType().equals("KEY")) {
					System.out.print(node.getName() + " = " + node.getValue());
					System.out.println();

					bufferedWriter.write(node.getName() + " = " + node.getValue());
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(">>> Program.writeFile(): " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(">>> Program.writeFile(): " + e.getMessage());
        }
        return true;
    }

	public static void inOrderStorage(Node root, List<Node> nodeList) {
		if (root == null) return;

		// Acces the left child of root
		inOrderStorage(root.getLeft(), nodeList);

		if (!root.notDuplicated() && root.getDuplicated().getType().equals("KEY"))
			nodeList.add(root.getDuplicated());
			
		nodeList.add(root);

		if (!root.notDuplicated() && root.getDuplicated().getType().equals("SCOPE")) 
			nodeList.add(root.getDuplicated());

		// Acces the right child of root
		inOrderStorage(root.getRight(), nodeList);
	}

	// Validate the list of contents
	public static BinaryTree[] testParser(List<String> contents) {
		Parser parser = new Parser();
		try {
			return parser.run(contents);
		} catch(RuntimeException e) {
			System.out.println();
			System.out.println(">>> The content is not grammatically correct: " + e.getMessage());
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
