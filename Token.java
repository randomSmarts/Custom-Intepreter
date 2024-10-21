// Token class to store a token's type and value
public class Token {
    TokenType type;  // The type of the token (e.g., NUMBER, IDENTIFIER)
    String value;    // The value of the token (e.g., "5", "x")

    // Constructor to create a Token object with a type and a value
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    // Override the toString method to return a string representation of the token
    @Override
    public String toString() {
        return "Token(" + type + ", '" + value + "')";
    }
}