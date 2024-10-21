import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;  // The input string containing the code to be tokenized
    private int pos = 0;         // The current position in the input string
    private char currentChar;    // The current character being processed

    // Constructor to initialize the input and set the first character to currentChar
    public Lexer(String input) {
        this.input = input;
        this.currentChar = input.charAt(pos);  // Set current character to the first character
    }

    // Method to advance to the next character in the input string
    private void advance() {
        pos++;
        if (pos < input.length()) {
            currentChar = input.charAt(pos);  // Move to the next character
        } else {
            currentChar = '\0';  // End of input (represented by null character)
        }
    }

    // Method to skip whitespace characters
    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();  // Skip over any spaces or tabs
        }
    }

    // Method to extract a number token (sequence of digits)
    private String number() {
        StringBuilder result = new StringBuilder();
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result.append(currentChar);  // Append digits to result
            advance();  // Move to next character
        }
        return result.toString();  // Return the number as a string
    }

    // Method to extract an identifier (variable name)
    private String identifier() {
        StringBuilder result = new StringBuilder();
        while (currentChar != '\0' && Character.isLetterOrDigit(currentChar)) {
            result.append(currentChar);  // Append letters or digits to result
            advance();  // Move to next character
        }
        return result.toString();  // Return the identifier
    }

    // Method to tokenize the input and return a list of tokens
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();  // List to store tokens
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();  // Skip over whitespace
                continue;
            }
            if (Character.isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, number()));  // Add a number token
                continue;
            }
            if (Character.isLetter(currentChar)) {
                tokens.add(new Token(TokenType.IDENTIFIER, identifier()));  // Add an identifier token
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
        tokens.add(new Token(TokenType.EOF, ""));  // Add end-of-file token
        return tokens;
    }
}

/*
Explanation:

	•	The Lexer class reads the input string and converts it into a sequence of tokens. It identifies numbers, identifiers (variables), operators (+, -, *, /), and assignment (=).
	•	Methods like advance() move through the input character by character, and number() and identifier() are used to extract tokens.
	•	The tokenize() method iterates through the input string and returns a list of tokens for further parsing.
 */