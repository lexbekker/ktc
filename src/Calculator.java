import java.util.List;

public class Calculator {
    static List<String> romanCharacterList = List.of("Hello guys!", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
    static List<String> arabicCharacterList = List.of("How are ya?", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    static List<String> romanDecimalCharacterList = List.of("Hey bro, nice code!", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C");
    static List<String> procedureCharacterList = List.of("+", "-", "/", "*");

    static int[] arabicNumeralArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    static int firstOperand, secondOperand;
    static String operation;
    static int result;
    static boolean isArabic;

    static String input = "I + IX";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calc(input));

    }


    /**
     * Метод, принимающий в качестве параметра строку с арифметическим выражением
     * в римской или арабской системе счисления. Работает для чисел от 1 до 10, результат
     * операции над римскими цифрами не может быть отрицательным.
     * Powered by LexBekker
     * @param inputString String with arithmetic expression
     * @return String, contains result of arithmetic expression
     */
    public String calc(String inputString) {
        String[] expr = inputString.split(" ");

        validateNumberOfArgs(expr);
        validateProcedure(expr);
        whichNumeralSystem(expr);

        result = calculationResult(firstOperand, operation, secondOperand);

        if(!isArabic) {
            romeLessThenOne(result);
            return convertArabicResultToRome(result);
        }

        return Integer.toString(result);
    }

    /**
     *  Метод принимает в качестве параметра массив элементов строки input.
     *  Если выражение не соответствует шаблону "число знак_операции  число", создаётся исключение
     *  @param expression Array of elements of an arithmetic expression
     */
    private static void validateNumberOfArgs(String[] expression){
        if (expression.length != 3) {
            throw new RuntimeException("Неверный ввод");
        }
    }

    /**
     *  Метод принимает в качестве параметра массив элементов строки input,
     *  в случае недопустимой арифметической операции создаётся исключение
     *  @param expression Array of elements of an arithmetic expression
     */
    private static void validateProcedure(String[] expression){
        if(procedureCharacterList.contains(expression[1])) {
            operation = expression[1];
        } else {
            throw new RuntimeException("Невозможная арифметическая операция");
        }
    }

    /**
     *  Метод принимает в качестве параметра массив элементов строки input
     *  и определяет систему счисления, которая использовалась в строке ввода
     * @param expression Array of elements of an arithmetic expression
     */
    private static void whichNumeralSystem(String[] expression){
        if (romanCharacterList.contains(expression[0]) && romanCharacterList.contains(expression[2])) {
            firstOperand = arabicNumeralArray[romanCharacterList.indexOf(expression[0])];
            secondOperand = arabicNumeralArray[romanCharacterList.indexOf(expression[2])];
            isArabic = false;
        } else if (arabicCharacterList.contains(expression[0]) && arabicCharacterList.contains(expression[2])) {
            firstOperand = arabicNumeralArray[arabicCharacterList.indexOf(expression[0])];
            secondOperand = arabicNumeralArray[arabicCharacterList.indexOf(expression[2])];
            isArabic = true;
        } else throw new RuntimeException("Неверный ввод, введите выражение, используя либо римские," +
                "либо арабские цифры от 1 до 10");
    }

    /**
     * Метод принимает в качестве параметра результат операции над римскими числами.
     * Если результат меньше единицы, создаётся исключение
     * @param result Calculation result
     */
    private static void romeLessThenOne(int result) {
        if(result < 1) throw new RuntimeException("Результат операции над римскими числами меньше единицы");
    }

    /**
     * Метод принимает в качестве параметра int результат вычисления,
     * конвертирует его в римскую систему счисления и возвращает строку с результатом:
     * @param result Calculation result
     * @return String result of conversion from Arabic to Roman
     */
    private static String convertArabicResultToRome(int result){
        StringBuilder stringBuilder = new StringBuilder();
        int dec = result / 10;
        if(dec > 0){
            stringBuilder.append(romanDecimalCharacterList.get(dec));
        }
        int rem = result % 10;
        if(rem != 0) {
            stringBuilder.append(romanCharacterList.get(rem));
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param firstOperand first number in expression
     * @param operation arithmetic expression operation
     * @param secondOperand second number in expression
     * @return int result of calculation
     */
    private static int calculationResult(int firstOperand, String operation, int secondOperand){
        int tempResult = 0;
        //Вычитание
        if(operation.equals("-")) {
            tempResult = firstOperand - secondOperand;
        }
        //Деление
        if(operation.equals("+")){
            tempResult = firstOperand + secondOperand;
        }
        //Умножение
        if(operation.equals("*")){
            tempResult = firstOperand * secondOperand;
        }
        //Деление
        if(operation.equals("/")){
            tempResult = firstOperand / secondOperand;
        }
        return tempResult;

    }


}
