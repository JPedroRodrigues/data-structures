import java.util.ArrayList;
import java.util.List;

import parser.Parser;

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
	
	public static void testParser(final String[] testData) {
		List<String> contents = new ArrayList<String>();
		for (var s : testData) {
			contents.add(s);
		}

		Parser parser = new Parser();
		try {
			parser.run(contents);
		} catch(RuntimeException e) {
			System.out.println();
			System.out.println("*** ERRO! O conteúdo inserido não está bem formado (não está de acordo com as regras gramaticais):");
			System.out.println("  > " + e.getMessage());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		String[] correctTest = {
			"# hello neeightbor",
			"char = at",
			"escopo",
			"(",
			"              ",
			"",
			"chave = valor",
			"Palavras complexas e = espaços mais complexos ainda",
			")",
		};

		String[] wrongTest = {
			"        # Exemplo de programa totalmente errado",
			" = ",
			"escopo (",
			"	chave = valor",
			")"
		};

		testParser(correctTest);
		testParser(wrongTest);
	}
	
}
