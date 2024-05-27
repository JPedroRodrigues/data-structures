package parser;

import java.util.ArrayList;
import java.util.List;
import Tree.*;

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

public class Parser {

	private List<Token> tokens;
	private Token currToken;
	private int index;
    private List<String> path;
    AVL avl;
    BST bst;

	public Parser() {
        // Parser related variables
		tokens = null;
		currToken = null;
		index = -1;

        // Tree related variables
        path = null;
        avl = null;
        bst = null;
	}
	
	public BinaryTree[] run(List<String> contents) {
        // Creating the Tree's root
        avl = new AVL();
        bst = new BST();
        path = new ArrayList<>();
        path.add("~");
        
        avl.insert(new Node(String.join("/", path), TokenType.SCOPE.toString()));
        bst.insert(new Node(String.join("/", path), TokenType.SCOPE.toString()));

        // Receiving tokens to start the validation
		Tokenizer tokenizer = new Tokenizer();
		tokens = tokenizer.tokenize(contents);
		currToken = null;
		index = -1;

		// Uncomment this code below to show the currently stored tokens
		// System.out.println("==================== TOKENS ====================");
		// for (var token : tokens) {
		// 	System.out.println(token);
		// }
		// System.out.println("==================== TOKENS ====================");
		
		parse();

        // Returning both AVL and BST trees
        BinaryTree[] treeArray = {avl, bst};
        return treeArray;
	}
	
	// Parse advances to the first token and then validates the data rule
	private void parse() {
		advance();
		data();		
		if (currToken.getType() != TokenType.EOF) {
			throw new RuntimeException("Parser.parse(): Waiting End of File (EOF), but found " + currToken);
		}
	}
	
	// <data> ::= ((<scope> | <key> | <comment>)* <blank_line>)*
	private void data() {
		TokenType type = currToken.getType();

		// Consume 0+ rules of SCOPE, KEY or COMMENT followed by blank lines
		while (type == TokenType.SCOPE || type == TokenType.KEY || type == TokenType.COMMENT || type == TokenType.NEWLINE) {
			if (type == TokenType.SCOPE) {
				scope();
			} else if (type == TokenType.KEY) {
				key();
			} else if (type == TokenType.COMMENT) 
                comment();
			
			// After processing SCOPE, KEY or COMMENT, consume an end of line
			consume(TokenType.NEWLINE);
			
			// Processing NEWLINE token type as a new line print
			System.out.println();			
			
			type = currToken.getType();
		}
	}
	

	// <scope> ::= <identifier> (<blank> | <blank_line>)* "(" <blank_line>+ <data>* <blank> ")"
	private void scope() {
        consume(TokenType.SCOPE);

        // StringBuilder used to consume both STRING and WHITESPACE
        StringBuilder scopeSB = new StringBuilder();
        StringBuilder identifier = new StringBuilder();

        // Consume STRING and WHITESPACE
        while (currToken.getType() == TokenType.STRING || currToken.getType() == TokenType.WHITESPACE) {
            identifier.append(currToken.getValue());

            if (currToken.getType() == TokenType.STRING) consume(TokenType.STRING);
            else if (currToken.getType() == TokenType.WHITESPACE) consume(TokenType.WHITESPACE);
        }

        // Is the Identifier blank?
        if (identifier.toString().isBlank()) {
            throw new RuntimeException("Identifier is Empty.");
        }

        // Compute the identifier as the name of node and build its path
        // if (avl.isEmpty() && bst.isEmpty()) {
        //     path.add("~");
        // }

        // Common order
        // path.addLast(identifier.toString().trim());

        // avl.insert(
        //     new Node(
        //         String.join("/", path),
        //         TokenType.SCOPE.toString()
        //     )
        // );

        // bst.insert(
        //     new Node(
        //         String.join("/", path),
        //         TokenType.SCOPE.toString()
        //     )
        // );

        // Reverse order
        path.addFirst(identifier.toString().trim());

        avl.insert(
            new Node(
                String.join("/", path),
                TokenType.SCOPE.toString()
            )
        );

        bst.insert(
            new Node(
                String.join("/", path),
                TokenType.SCOPE.toString()
            )
        );

        // If code reaches this line, then it has a valid identifier
        scopeSB.append(identifier.toString());

        // Consume 0+ NEWLINE tokens
        while (currToken.getType() == TokenType.NEWLINE) {
            scopeSB.append("\n");
            consume(TokenType.NEWLINE);
        }

        scopeSB.append(currToken.getValue());
        consume(TokenType.OPEN_PARENTESIS);

        scopeSB.append(currToken.getValue());
        consume(TokenType.NEWLINE);

        System.out.print(scopeSB.toString());
        scopeSB.setLength(0);

        data();

        // Removing the already used SCOPE in the path
        // Common order
        // path.removeLast();

        // Reverser order
        path.removeFirst();

        // Consume 0+ WHITESPACE tokens
        while (currToken.getType() == TokenType.WHITESPACE) {
            scopeSB.append(currToken.getValue());
            consume(TokenType.WHITESPACE);
        }

        scopeSB.append(currToken.getValue());
        consume(TokenType.CLOSE_PARENTESIS);

        System.out.print(scopeSB.toString());
	}
	

	// <key> ::= <identifier> <blank> "=" <blank> <value>
	private void key() {
        consume(TokenType.KEY);

		// StringBuilder used to consume both STRING and WHITESPACE
        StringBuilder identifier = new StringBuilder();

        // Consume STRING and WHITESPACE
        while (currToken.getType() == TokenType.STRING || currToken.getType() == TokenType.WHITESPACE) {
            identifier.append(currToken.getValue());

            if (currToken.getType() == TokenType.STRING) consume(TokenType.STRING);
            else if (currToken.getType() == TokenType.WHITESPACE) consume(TokenType.WHITESPACE);
        }

        // Is the Identifier blank?
        if (identifier.toString().isBlank()) {
            throw new RuntimeException("Identifier is Empty.");
        }
        
        // identifier.append(currToken.getValue());
        consume(TokenType.KEY_SYMBOL);

        // Read the both value STRING and WHITESPACE
        StringBuilder value = new StringBuilder();

        while (currToken.getType() == TokenType.STRING || currToken.getType() == TokenType.WHITESPACE) {
            value.append(currToken.getValue());

            if (currToken.getType() == TokenType.STRING) consume(TokenType.STRING);
            else if (currToken.getType() == TokenType.WHITESPACE) consume(TokenType.WHITESPACE);
        }

        if (value.toString().isBlank()) {
            throw new RuntimeException("Value is Empty.");
        }

        // If our program reaches this part, then the key has both valid identifier and key
        // Inserting the new key in the AVL and BST trees
        // if (avl.isEmpty() && bst.isEmpty()) {
        //     path.add("~");
        // }

        // Common order
        // path.addLast(identifier.toString().trim());

        // avl.insert(
        //     new Node(
        //         String.join("/", path),
        //         TokenType.KEY.toString(),
        //         value.toString().trim()
        //     )
        // );

        // bst.insert(
        //     new Node(
        //         String.join("/", path),
        //         TokenType.KEY.toString(),
        //         value.toString().trim()
        //     )
        // );

        // Inserting the new key in the AVL and BST trees
        // Reverser order
        path.addFirst(identifier.toString().trim());

        avl.insert(
            new Node(
                String.join("/", path),
                TokenType.KEY.toString(),
                value.toString().trim()
            )
        );

        bst.insert(
            new Node(
                String.join("/", path),
                TokenType.KEY.toString(),
                value.toString().trim()
            )
        );

        // Removing the already used Key in the path
        // Common order
        // path.removeLast();

        // Reverse order
        path.removeFirst();

        System.out.print(identifier.toString() + "=" + value.toString());
	}


    private void comment() {
        consume(TokenType.COMMENT);

        // Store the current STRING value
        String str = currToken.getValue();
        consume(TokenType.STRING);

        // If the program reaches this part, then the key has both valid identifier and key
        System.out.print("#" + str);
    }


	// Advances to the next token of the list (update token)
	private void advance() {
		++index;
		if (index >= tokens.size()) {
			throw new RuntimeException("Unexpected end of file.");
		}
		currToken = tokens.get(index);
	}
	
    // Consume the expected token. If it was not the expected, return an error
	private void consume(TokenType expected) {
		if (currToken.getType() == expected) {
			advance();
		} else {
			throw new RuntimeException("Parser.consume(): Token incorreto. Esperado: " + expected + ". Obtido: " + currToken);
		}
	}
}
