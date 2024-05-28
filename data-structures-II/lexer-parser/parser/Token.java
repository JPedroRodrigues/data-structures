/* APL2 - Lexer & Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Pedro Pessuto Rodrigues Ferreira    10409729
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package parser;

public class Token {
	
	private TokenType type;
	private String value;
	
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return type + ": " + value;
	}

}
