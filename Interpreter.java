import java.util.List;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner to take user input

        System.out.println("Enter code to interpret (e.g., y = 10 + 5;):");
        String code = scanner.nextLine();  // Get user input

        // Tokenization: Create a lexer and tokenize the input code
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
        tokens.forEach(System.out::println);  // Print the tokens for debugging

        // Parsing: Create a parser and generate an abstract syntax tree (AST)
        Parser parser = new Parser(tokens);
        Expression expression = parser.parse();

        // Evaluation: Evaluate the expression
        int result = expression.evaluate();
        System.out.println("Result: " + result);  // Print the result

        // Print the variables stored in memory
        System.out.println("Variables:");
        AssignExpression.memory.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }
}

/*
Explanation:
	•	The Interpreter class is the entry point for the program. It reads user input, tokenizes it using the Lexer, parses it using the Parser, and evaluates the resulting AST.
	•	After evaluation, the interpreter prints the result and any variables stored in memory.
	•	This is the class that ties all components (lexer, parser, and evaluator) together.
*/

/*
How Each Segment Contributes to the Program:
	•	Token.java: Defines the tokens that represent different components of the language (numbers, variables, operators, etc.).
	•	Lexer.java: Converts the input string into a sequence of tokens that the parser can process.
	•	Expression.java: Defines the different types of expressions (numbers, assignments, binary operations) that can appear in the language.
	•	Parser.java: Converts the tokens into an abstract syntax tree (AST) composed of Expression objects, which represents the structure of the code.
	•	Interpreter.java: Orchestrates the entire process by reading input, tokenizing, parsing, evaluating the AST, and printing the results.

 */