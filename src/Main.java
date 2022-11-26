import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String inputLine = scan.nextLine(); //Считываем строку, передаем в метод calc
        scan.close();
        System.out.println(calc(inputLine));
    }

    public static String calc(String input) throws Exception {
        String[] var = input.split("\\b");
        if (var.length > 3) throw new Exception("Больше 3-х операндов");
        String result = null;
        if (isNum(var[0].trim()) && isNum(var[2].trim())) {
            int firstNumber = Integer.parseInt(var[0].trim());
            int secondNumber = Integer.parseInt(var[2].trim());
            result = operation(firstNumber, secondNumber, var[1].trim());
        } else if (isArab(var[0].trim()) && isArab(var[2].trim())) {
            String firstNumber = var[0].trim();
            String secondNumber = var[2].trim();
            result = operation(firstNumber, secondNumber, var[1].trim());
        } else throw new Exception("Ошибка ввода данных");
        return result;
    }

    static String operation(int first, int second, String operator) throws Exception {
        int firstNumber = first;
        int secondNumber = second;
        int result = 0;
        if (firstNumber >= 1 && firstNumber <= 10 && secondNumber >= 1 && secondNumber <= 10) {
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
                default:
                    throw new Exception("Не верный оператор");
            }
        } else throw new Exception("Не от 1 до 10");
        return Integer.toString(result);
    }

    static String operation(String first, String second, String operator) throws Exception {
        int firstNumber = translarionInArab(first);
        int secondNumber = translarionInArab(second);
        String answerRome = null;
        String result = operation(firstNumber, secondNumber, operator);
        if (Integer.parseInt(result) > 0) {
            answerRome = inRome(Integer.parseInt(result));
        } else throw new Exception("Результат меньше или равен нулю");
        return answerRome;
    }

    static int translarionInArab(String znak) throws Exception {
        RomeArab pos = RomeArab.valueOf(znak);
        return pos.getTranslation();
    }

    public static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isArab(String str) {
        try {
            RomeArab pos = RomeArab.valueOf(str);
            pos.getTranslation();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String inRome(int number) {
        int[] roman_value_list = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i++) {
            while (number >= roman_value_list[i]) {
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }
}
enum RomeArab {
    I(1), II(2), III(3), IV(4), V(5),
    VI(6), VII(7), VIII(8), IX(9), X(10);
    int translation;

    RomeArab(int translation) {
        this.translation = translation;
    }

    public int getTranslation() {
        return translation;
    }
}