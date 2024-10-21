abstract class Expression {
    abstract int evaluate();
}

class NumberExpression extends Expression {
    int value;

    NumberExpression(int value) {
        this.value = value;
    }

    @Override
    int evaluate() {
        return value;
    }
}

class AssignExpression extends Expression {
    String varName;
    Expression value;
    static final java.util.Map<String, Integer> memory = new java.util.HashMap<>();

    AssignExpression(String varName, Expression value) {
        this.varName = varName;
        this.value = value;
    }

    @Override
    int evaluate() {
        int val = value.evaluate();
        memory.put(varName, val);
        return val;
    }
}

class BinaryOperationExpression extends Expression {
    Expression left;
    Expression right;
    String operator;

    BinaryOperationExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    int evaluate() {
        int leftVal = left.evaluate();
        int rightVal = right.evaluate();
        switch (operator) {
            case "+":
                return leftVal + rightVal;
            case "-":
                return leftVal - rightVal;
            case "*":
                return leftVal * rightVal;
            case "/":
                return leftVal / rightVal;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}

class VariableExpression extends Expression {
    String varName;

    VariableExpression(String varName) {
        this.varName = varName;
    }

    @Override
    int evaluate() {
        if (AssignExpression.memory.containsKey(varName)) {
            return AssignExpression.memory.get(varName);
        } else {
            throw new RuntimeException("Variable " + varName + " is not defined.");
        }
    }
}