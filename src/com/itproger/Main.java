package com.itproger;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        class Calculator {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите выражение (например, 2 + 3):");
                String input = scanner.nextLine();

                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Неверный формат выражения");
                }

                int a, b;
                try {
                    if (isRoman(parts[0]) && isRoman(parts[2])) {
                        a = romanToArabic(parts[0]);
                        b = romanToArabic(parts[2]);
                    } else if (!isRoman(parts[0]) && !isRoman(parts[2])) {
                        a = Integer.parseInt(parts[0]);
                        b = Integer.parseInt(parts[2]);
                    } else {
                        throw new IllegalArgumentException("Калькулятор не умеет работать с арабскими и римскими числами одновременно");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Неправильный формат чисел");
                }

                int result;
                switch (parts[1]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        result = a / b;
                        break;
                    default:
                        throw new IllegalArgumentException("Неподдерживаемая операция");
                }

                if (isRoman(parts[0]) && isRoman(parts[2])) {
                    if (result <= 0) {
                        throw new IllegalArgumentException("Результат должен быть положительным для римских чисел");
                    }
                    System.out.println(arabicToRoman(result));
                } else {
                    System.out.println(result);
                }
            }

            private static boolean isRoman(String str) {
                return str.matches("[IVXLCDM]+");
            }

            private static int romanToArabic(String roman) {
                Map<Character, Integer> romanMap = new HashMap<>();
                romanMap.put('I', 1);
                romanMap.put('V', 5);
                romanMap.put('X', 10);
                romanMap.put('L', 50);
                romanMap.put('C', 100);
                romanMap.put('D', 500);
                romanMap.put('M', 1000);

                int result = 0;
                int prevValue = 0;
                for (int i = roman.length() - 1; i >= 0; i--) {
                    int value = romanMap.get(roman.charAt(i));
                    if (value < prevValue) {
                        result -= value;
                    } else {
                        result += value;
                    }
                    prevValue = value;
                }
                return result;
            }

            private static String arabicToRoman(int number) {
                if (number < 1 || number > 3999) {
                    throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 3999");
                }

                String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

                StringBuilder roman = new StringBuilder();
                int i = 0;
                while (number > 0) {
                    while (number >= arabicValues[i]) {
                        roman.append(romanSymbols[i]);
                        number -= arabicValues[i];
                    }
                    i++;
                }
                return roman.toString();
            }
        }







    }
}