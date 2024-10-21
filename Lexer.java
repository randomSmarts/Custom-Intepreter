import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos = 0;
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.currentChar = input.charAt(pos);
    }

    private void advance() {
        pos++;
        if (pos < input.length()) {
            currentChar = input.charAt(pos);
        } else {
            currentChar = '\0';  // End of input
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private String number() {
        StringBuilder result = new StringBuilder();
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    private String identifier() {
        StringBuilder result = new StringBuilder();
        while (currentChar != '\0' && Character.isLetterOrDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }
            if (Character.isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, number()));
                continue;
            }
            if (Character.isLetter(currentChar)) {
                tokens.add(new Token(TokenType.IDENTIFIER, identifier()));
                continue;
            }
            switch (currentChar) {
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    advance();
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-"));
                    advance();
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*"));
                    advance();
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIVIDE, "/"));
                    advance();
                    break;
                case '=':
                    tokens.add(new Token(TokenType.ASSIGN, "="));
                    advance();
                    break;
                case ';':
                    tokens.add(new Token(TokenType.SEMICOLON, ";"));
                    advance();
                    break;
                default:
                    throw new RuntimeException("Unknown character: " + currentChar);
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}