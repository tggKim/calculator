package com.example.calculator_lv3_1.app;

import com.example.calculator_lv3_1.calculator.Calculator03_02;
import com.example.calculator_lv3_1.symbol.Symbol02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class App03 {

    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^-?[0-9]+$";
    private static final String DECIMAL_REG = "^-?[0-9]+(\\.[0-9]+)?$";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] arg){
        Calculator03_02<Double> calculator = new Calculator03_02<>();
        String continueFlag = "false";
        while(!continueFlag.equals("exit")){

            System.out.println("원하시는 기능을 번호로 선택해 주세요");
            System.out.println("===================================================");
            System.out.println("1. 숫자 계산\n" +
                    "2. 원하는 연산 기록 출력\n" +
                    "3. 원하는 연산 기록 삭제\n" +
                    "4. 원하는 값보다 큰 기록들 불러오기");
            System.out.println("===================================================");

            // 올바른 번호를 입력할 때까지 반복해서 입력받음
            String functionFlag = readFunctionFlag();

            if(functionFlag.equals("1")){
                System.out.print("첫 번째 수를 입력하세요: ");
                double firstNumber = Double.parseDouble(readDecimal());

                System.out.print("두 번째 수를 입력하세요: ");
                double secondNumber = Double.parseDouble(readDecimal());

                System.out.print("부호를 입력하세요: ");
                Symbol02 symbol = readSymbol();

                double result = 0;
                try{
                    result = calculator.calculate(firstNumber, secondNumber, symbol);
                    System.out.println("===================================================");
                    System.out.println("계산 결과: " + result);
                    System.out.println("===================================================");
                }catch (ArithmeticException e){
                    System.out.println("===================================================");
                    System.out.println(e.getMessage());
                    System.out.println("===================================================");
                }
            }
            else if(functionFlag.equals("2")){
                System.out.print("몇 번째 연산 기록을 불러올까요? 총 " + calculator.getRecordSize() + "번의 기록이 있습니다: ");
                String number = readNumber();
                calculator.getAnswerRecord(Integer.parseInt(number));
            }
            else if(functionFlag.equals("3")){
                System.out.print("몇 번째 연산 기록을 삭제할까요? 총 " + calculator.getRecordSize() + "번의 기록이 있습니다: ");
                String number = readNumber();
                calculator.removeRecord(Integer.parseInt(number));
            }
            else if(functionFlag.equals("4")){
                System.out.print("입력하신 값보다 큰 기록들을 모두 불러옵니다. 값을 입력해주세요: ");
                String number = readDecimal();
                calculator.showLargerRecord(Double.parseDouble(number));
            }

            System.out.print("계산을 더 진행하시겠습니까?(exit을 입력하면 종료됩니다): ");
            continueFlag = scanner.nextLine();
        }
    }

    private static String readFunctionFlag(){
        String functionFlag = scanner.nextLine();
        while(!Pattern.matches(NUMBER_REG, functionFlag) || Integer.parseInt(functionFlag) <= 0 || Integer.parseInt(functionFlag) > 4){
            if(!Pattern.matches(NUMBER_REG, functionFlag)){
                System.out.print("잘못된 입력 형식입니다. 1-4 사이의 정수를 입력해주세요: ");
                functionFlag = scanner.nextLine();
            }
            else{
                System.out.print("잘못된 번호입니다. 1-4 사이의 번호를 입력해주세요: ");
                functionFlag = scanner.nextLine();
            }
        }
        return functionFlag;
    }

    private static String readNumber(){
        String number = scanner.nextLine();
        while(!Pattern.matches(NUMBER_REG,number)){
            System.out.print("정수 형태를 입력해 주세요: ");
            number = scanner.nextLine();
        }
        return number;
    }

    private static String readDecimal(){
        String number = scanner.nextLine();
        while(!Pattern.matches(DECIMAL_REG,number)){
            System.out.print("정수나 소수 형태를 입력해 주세요: ");
            number = scanner.nextLine();
        }
        return number;
    }

    private static Symbol02 readSymbol(){
        String symbol = scanner.nextLine();
        while(!Pattern.matches(OPERATION_REG, symbol)){
            System.out.print("사칙연산 + - / * 중 하나를 입력해 주세요: ");
            symbol = scanner.nextLine();
        }
        return Symbol02.fromSymbol(symbol);
    }
}
