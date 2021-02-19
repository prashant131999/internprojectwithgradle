package calculator;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class takes equation as input from user and prints out the result after solving it
 * calculation method takes one operator as an input and apply it between two num
 */
class Bodmascalci {
    /**This method makes calculation between two number on given operator
     *
     * @param oper parameter for operator on which operation is performed
     * @param num2 second number from the stack for calculation
     * @param num1 first number  from stack for calculation
     * @return the output after the operation between two operator.
     */
    public static int calculation(char oper, int num2, int num1) {
        switch (oper) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    /**
     * this method check the precedence and return true if there is precedence
     * @param oper_1 comes from question char array
     * @param oper_2 comes from stack of operations
     *
     * @return the true or false
     */
    public static boolean hasPrecedence(char oper_1, char oper_2) {
        if (oper_2 == '(' || oper_2 == ')')
            return false;
        if ((oper_1 == '*' || oper_1 == '/') && (oper_2 == '+' || oper_2 == '-'))
            return false;
        else
            return true;
    }

    /** this method solves the string given by user using two stacks and return the output
     *
     * @param question takes the input from the user and does the calculation
     *
     * @return the output after calculation on the given question
     */
    public static int solve(String question) {
        int flag = 0;
        char[] evaluation_array = question.toCharArray();
        // stack num for numbers
        Stack<Integer> number = new Stack<Integer>();
        // stack operators for operators
        Stack<Character> operators = new Stack<Character>();
        for (int i = 0; i < evaluation_array.length; i++) {
            if (evaluation_array[i] >= 'A' && evaluation_array[i] <= 'Z') {
                flag = 1;
                if (flag == 1) {
                    System.out.println("Input is not correct");
                }
                break;
            }
            if (evaluation_array[i] >= 'a' && evaluation_array[i] <= 'z') {
                flag = 1;
                if (flag == 1) {
                    System.out.println("Input is not correct");
                }
                break;
            }

            if (evaluation_array[i] == ' ') {
                continue;
            }
            if (evaluation_array[i] >= '0' && evaluation_array[i] <= '9') {
                StringBuffer strcalculation = new StringBuffer();
                while (i < evaluation_array.length && evaluation_array[i] >= '0' && evaluation_array[i] <= '9') {
                    strcalculation.append(evaluation_array[i++]);
                }
                number.push(Integer.parseInt(strcalculation.toString()));
            } else if (evaluation_array[i] == '(') {
                operators.push(evaluation_array[i]);
            } else if (evaluation_array[i] == ')') {
                while (operators.peek() != '(') {
                    number.push(calculation(operators.pop(), number.pop(), number.pop()));

                }
                operators.pop();
            } else if (evaluation_array[i] == '+' || evaluation_array[i] == '-' || evaluation_array[i] == '*'
                    || evaluation_array[i] == '/') {
                while (!operators.empty() && hasPrecedence(evaluation_array[i], operators.peek())) {
                    number.push(calculation(operators.pop(), number.pop(), number.pop()));
                }
                operators.push(evaluation_array[i]);
            }

        }
        while (!operators.empty()) {
            number.push(calculation(operators.pop(), number.pop(), number.pop()));
        }
        return number.pop();

    }

    public static void main(String[] args) {
        int result;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the input");
        String question = sc.nextLine();

        result = solve(question);


        System.out.println(result);

    }
}