import java.util.List;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter code to interpret (e.g., y = 10 + 5;):");
        String code = scanner.nextLine();

        // Tokenization
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
        tokens.forEach(System.out::println);

        // Parsing
        Parser parser = new Parser(tokens);
        Expression expression = parser.parse();

        // Evaluation
        int result = expression.evaluate();
        System.out.println("Result: " + result);

        // Display all variables in memory
        System.out.println("Variables:");
        AssignExpression.memory.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }
}