/* APL2 - Lexer & Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Pedro Pessuto Rodrigues Ferreira    10409729
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Deque;

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

public class Tokenizer {
	private int pos;
	private String line = null;

	public Tokenizer() {
		pos = 0;
	}
	
	// A partir da lista de strings de entrada (contents), constrói e retorna uma lista de Tokens
	// que será processada pelo parser.
	public List<Token> tokenize(List<String> contents) {
		List<Token> tokens = new ArrayList<>();
		Deque<Token> tokenDeque = new ArrayDeque<>();

		StringBuilder sb = new StringBuilder();

		int lineIndex = 0;
		Character currChar = '\0';
		boolean isData = false;
		
		while (true) {
			// Have we just started reading the file or the current line is finished?
			if (line == null || pos >= line.length()) {
				// If the token Deque is not empty, then there was being created a data line. In this case, let's add its contents build until now
				if (tokenDeque.size() > 0) {
					// For each token stored in the Deque, indicanting all of the line's tokens, add them to the tokens List
					for (Token token : tokenDeque) tokens.add(token);

					tokenDeque.clear();
					sb.setLength(0);
				}
				
				// Add a New line token
				if (line != null) {
					tokens.add(new Token(TokenType.NEWLINE, "\n"));
				}
				
				// Have we reached the end of file?
				if (lineIndex >= contents.size()) {
					// Adiciona um token EOF, indicando fim do conteúdo, e encerra o loop.
					tokens.add(new Token(TokenType.EOF, "\0"));
					break;
				}
				
				// Reads next line
				line = contents.get(lineIndex++);
				
				// Resets all control variables at each line
				pos = 0;
				currChar = '\0';
				isData = false;
				
				// Does the line has just empty spaces?
				if (line.isBlank()) {
					pos = line.length();
					continue;
				}				
			}
			
			if (!isData) {
				currChar = getNextChar();
				
				// Recongnizes a WHITESPACE token
				if (Character.isWhitespace(currChar)) { 
					// Reads a sequence of whitespaces as a single whitespace
					while (Character.isWhitespace(currChar)) {
						currChar = getNextChar();
					}

					tokenDeque.addLast(new Token(TokenType.WHITESPACE, " "));

					// If we pass by a sequence of whitespaces and didn't reach the end of line
					// then we return one position in the line to continue to analyse
					if (pos <= line.length() && !Character.isWhitespace(line.charAt(pos - 1))) {
						--pos;
					}
				
				} else if (currChar == '=') {  // Recognize a KEY token
					// Add a key symbol to maintain the order of writing
					tokenDeque.addLast(new Token(TokenType.KEY_SYMBOL, currChar.toString()));
					
					// Place a KEY at the start of the token Deque to identify the line as a KEY
					tokenDeque.addFirst(new Token(TokenType.KEY, currChar.toString()));
				} else if (currChar == '(') {
					if (tokenDeque.size() > 0) {
						tokenDeque.addFirst(new Token(TokenType.SCOPE, "("));

						tokenDeque.addLast(new Token(TokenType.OPEN_PARENTESIS, "("));
					} else {
						// Create a stack to store the last line's identifier
						Stack<Token> tokenStack = new Stack<>();

						// Taking the last NEWLINE, from the line above the actual line
						if (tokens.size() > 0) tokenStack.push(tokens.removeLast());
						
						// While the tokens from the last line aren't completely stored, keep adding them to the stack
						// Removes from the upper line all of its tokens
						while (tokens.size() > 0 && tokens.getLast().getType() != TokenType.NEWLINE) {
							tokenStack.push(tokens.removeLast());
						}

						// Insert the SCOPE token
						tokens.add(new Token(TokenType.SCOPE, "("));

						// Return every token removed to the list
						while (tokenStack.size() != 0) {
							tokens.addLast(tokenStack.pop());
						}

						// Add a parentesis identifier to keep track of the correct writing
						tokenDeque.addLast(new Token(TokenType.OPEN_PARENTESIS, "("));
					}
				} else if (currChar == ')') {  // Recognize the end of a scope
					tokenDeque.addLast(new Token(TokenType.CLOSE_PARENTESIS, ")"));
				} else if (currChar == '#') { // Recognize a COMMENT token
					// Add the comment symbol
					tokenDeque.addLast(new Token(TokenType.COMMENT, "#"));

					// Setting a new StringBuilder to add comment's string
					sb.setLength(0);
					while (pos < line.length()) {
						currChar = getNextChar();
						sb.append(currChar);
					}
					
					tokenDeque.addLast(new Token(TokenType.STRING, sb.toString()));
					sb.setLength(0);
					isData = false;

				} else if (currChar != '\0') { // Probably an alphabetical STRING was found
					// Resets the string builder to keep reading by the start
					isData = true;
					sb.setLength(0);
					sb.append(currChar);
				}

			} else { // Recongnizes a STRING token
				// At this momment, we just gotta read everything until we find a special symbol

				while (pos < line.length()) {
					currChar = getNextChar();

					if (Character.isWhitespace(currChar) || currChar == '=' || currChar == '(' || currChar == ')') {
						// Reads the current STRING
						tokenDeque.addLast(new Token(TokenType.STRING, sb.toString()));
						sb.setLength(0);

						--pos;
						isData = false;
						break;
					}
					sb.append(currChar);        
				}

				if (Character.isWhitespace(currChar) || currChar == '=' || currChar == '(' || currChar == ')') continue;

				tokenDeque.addLast(new Token(TokenType.STRING, sb.toString()));

				sb.setLength(0);
				isData = false;
			}
		}
		return tokens;
	}
	
	// Advances to the next line character and then returns its value
	// Returns a null terminator '\0' if it reaches the end of line
	private char getNextChar() {
		if (pos >= line.length()) {
			return '\0';
		}
		return line.charAt(pos++);
	}
}
