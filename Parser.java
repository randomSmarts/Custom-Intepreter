import java.util.List;

class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token currentToken() {
        return tokens.get(pos);
    }

    private void advance() {
        pos++;
    }

    private Token eat(TokenType type) {
        Token token = currentToken();
        if (token.type == type) {
            advance();
        } else {
            throw new RuntimeException("Expected token: " + type + " but got " + token.type);
        }
        return token;
    }

    private Expression parseFactor() {
        Token token = currentToken();
        if (token.type == TokenType.NUMBER) {
            eat(TokenType.NUMBER);
            return new NumberExpression(Integer.parseInt(token.value));
        }
        if (token.type == TokenType.IDENTIFIER) {
            String varName = token.value;
            eat(TokenType.IDENTIFIER);
            return new VariableExpression(varName);
        }
        throw new RuntimeException("Unexpected token: " + token);
    }

    private Expression parseTerm() {
        Expression result = parseFactor();
        while (currentToken().type == TokenType.MULTIPLY || currentToken().type == TokenType.DIVIDE) {
            Token token = currentToken();
            String operator = token.value;
            eat(token.type);
            result = new BinaryOperationExpression(result, operator, parseFactor());
        }
        return result;
    }

    private Expression parseExpression() {
        Expression result = parseTerm();
        while (currentToken().type == TokenType.PLUS || currentToken().type == TokenType.MINUS) {
            Token token = currentToken();
            String operator = token.value;
            eat(token.type);
            result = new BinaryOperationExpression(result, operator, parseTerm());
        }
        return result;
    }

    public Expression parse() {
        Token token = currentToken();
        if (token.type == TokenType.IDENTIFIER) {
            String varName = token.value;
            eat(TokenType.IDENTIFIER);
            eat(TokenType.ASSIGN);
            Expression value = parseExpression();
            return new AssignExpression(varName, value);
        }
        return parseExpression();
    }
}