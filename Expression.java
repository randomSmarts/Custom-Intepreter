// Base class for all expression types
abstract class Expression {
    abstract int evaluate();  // Each expression must implement evaluate method
}

// Class to represent a numeric literal (e.g., 5, 10)
class NumberExpression extends Expression {
    int value;  // The value of the number

    // Constructor to initialize the value
    NumberExpression(int value) {
        this.value = value;
    }

    // Evaluate method that returns the numeric value
    @Override
    int evaluate() {
        return value;
    }
}

// Class to represent a variable assignment (e.g., x = 5)
class AssignExpression extends Expression {
    String varName;  // The variable being assigned to
    Expression value;  // The expression being assigned
    static final java.util.Map<String, Integer> memory = new java.util.HashMap<>();  // Memory to store variables

    // Constructor to initialize the variable name and value
    AssignExpression(String varName, Expression value) {
        this.varName = varName;
        this.value = value;
    }

    // Evaluate method that assigns the value to the variable and stores it in memory
    @Override
    int evaluate() {
        int val = value.evaluate();  // Evaluate the right-hand side of the assignment
        memory.put(varName, val);  // Store the variable in memory
        return val;  // Return the evaluated value
    }
}

// Class to represent a binary operation (e.g., 5 + 10)
class BinaryOperationExpression extends Expression {
    Expression left;  // Left operand
    Expression right;  // Right operand
    String operator;  // Operator (e.g., +, -, *, /)

    // Constructor to initialize left operand, operator, and right operand
    BinaryOperationExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    // Evaluate method that performs the binary operation
    @Override
    int evaluate() {
        int leftVal = left.evaluate();  // Evaluate the left operand
        int rightVal = right.evaluate();  // Evaluate the right operand
        switch (operator) {
            case "+":
                return leftVal + rightVal;  // Perform addition
            case "-":
                return leftVal - rightVal;  // Perform subtraction
            case "*":
                return leftVal * rightVal;  // Perform multiplication
            case "/":
                return leftVal / rightVal;  // Perform division
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}

// Class to represent a variable expression (e.g., y)
class VariableExpression extends Expression {
    String varName;  // The variable name

    // Constructor to initialize the variable name
    VariableExpression(String varName) {
        this.varName = varName;
    }

    // Evaluate method to retrieve the variable's value from memory
    @Override
    int evaluate() {
        if (AssignExpression.memory.containsKey(varName)) {
            return AssignExpression.memory.get(varName);  // Return the variable's value
        } else {
            throw new RuntimeException("Variable " + varName + " is not defined.");
        }
    }
}

/*
Explanation:
	•	The Expression class is the base class for different kinds of expressions. It defines the evaluate() method, which must be implemented by subclasses.
	•	The NumberExpression class represents numeric values, while the AssignExpression handles variable assignments.
	•	The BinaryOperationExpression class handles binary operations (e.g., 5 + 10), and the VariableExpression class retrieves variable values from memory.
	•	The memory map stores variable values, allowing the program to evaluate expressions that use variables.
 */