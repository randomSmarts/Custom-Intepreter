// Enum to define the various types of tokens that the lexer can generate
public enum TokenType {
    NUMBER,       // Represents numeric literals (e.g., 10, 5)
    PLUS,         // Represents the '+' operator
    MINUS,        // Represents the '-' operator
    MULTIPLY,     // Represents the '*' operator
    DIVIDE,       // Represents the '/' operator
    ASSIGN,       // Represents the '=' operator used for assignments
    IDENTIFIER,   // Represents variable names (e.g., x, y)
    SEMICOLON,    // Represents the ';' symbol which marks the end of a statement
    EOF           // Represents the end of the input
}

/*
Explanation:
	•	The TokenType enum defines the different types of tokens the lexer can generate, such as numbers, operators, and variable names.
	•	The Token class represents a single token, storing its type and the string value. The toString() method allows easy printing for debugging purposes.
 */