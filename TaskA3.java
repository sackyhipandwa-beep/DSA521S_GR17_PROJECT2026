// TaskA3.java
class CustomStack {
    private int[] data;
    private int top;
    private int capacity;

    public CustomStack(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow!");
            return;
        }
        data[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return data[top--];
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return data[top];
    }

    public void printStack() {
        System.out.print("Stack Contents (Bottom -> Top): [ ");
        for (int i = 0; i <= top; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println("]");
    }
}

public class TaskA3 {
    public static int evaluatePostfix(String expression) {
        CustomStack stack = new CustomStack(20);
        String[] tokens = expression.split(" ");

        System.out.println("\nEvaluating Postfix Expression: \"" + expression + "\"");
        System.out.println("--------------------------------------------------");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                int num = Integer.parseInt(token);
                stack.push(num);
                System.out.println("Token: " + token + " -> Pushed operand " + num);
                stack.printStack();
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = 0;

                switch (token) {
                    case "+": result = operand1 + operand2; break;
                    case "-": result = operand1 - operand2; break;
                    case "*": 
                    case "x": result = operand1 * operand2; break;
                    case "/": result = operand1 / operand2; break;
                    default: System.out.println("Invalid Operator: " + token);
                }

                stack.push(result);
                System.out.println("Token: " + token + " -> Evaluated (" + operand1 + " " + token + " " + operand2 + ") = " + result);
                stack.printStack();
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String expr = "5 3 + 2 *";
        int finalResult = evaluatePostfix(expr);
        System.out.println("--------------------------------------------------");
        System.out.println("Final Result: " + finalResult);
    }
}