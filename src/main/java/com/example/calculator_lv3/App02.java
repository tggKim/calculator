package com.example.calculator_lv3;

import com.example.calculator_lv3.exception.DivisionByZeroException;
import com.example.calculator_lv3.exception.InvalidNumberException;
import com.example.calculator_lv3.exception.InvalidOperatorException;
import com.example.calculator_lv3.parser.Parser;
import com.example.calculator_lv3.parser.Symbol;
import com.example.calculator_lv3.service.CalculatorService;

import java.util.Scanner;

public class App02 {

    private CalculatorService service = new CalculatorService();
    Parser parser = new Parser();
    Scanner scanner = new Scanner(System.in);

    public void calculateNumbers() {
        double firstNumber = 0.0;
        double secondNumber = 0.0;
        Symbol symbol = null;

        boolean b = false;
        System.out.print("첫 번째 수를 입력해주세요: ");
        while(!b){
            try{
                firstNumber = parser.parseFirstNum(scanner.nextLine());
                b = true;
            }catch (InvalidNumberException e){
                System.out.print(e.getMessage());
            }
        }
        b = false;

        System.out.print("두 번째 수를 입력해주세요: ");
        while(!b){
            try{
                secondNumber = parser.parseSecondNum(scanner.nextLine());
                b = true;
            }catch (InvalidNumberException e){
                System.out.print(e.getMessage());
            }
        }
        b = false;

        System.out.print("사칙연산 부호를 입력해 주세요: ");
        while(!b){
            try{
                symbol = parser.parseOperator(scanner.nextLine());
                b = true;
            }catch (InvalidOperatorException e){
                System.out.print(e.getMessage());
            }
        }
        b = false;

        service.setOperation(symbol);
        double answer = 0;
        while(!b){
            try{
                answer = service.calculate(firstNumber, secondNumber);
                b = true;
            }catch (DivisionByZeroException e){
                System.out.print(e.getMessage());
                boolean x = false;
                while(!x){
                    try{
                        parser.parseSecondNum(scanner.nextLine());
                        x = true;
                    }catch (InvalidNumberException ex){
                        System.out.print(ex.getMessage());
                    }
                }
            }
        }

        System.out.println("결과: " + answer);

    }

    public void option() {

        System.out.println("원하시는 기능을 번호로 선택해 주세요");
        System.out.println("===========================================================================");
        System.out.println("1. 숫자 계산\n" +
                "2. 원하는 연산 기록 출력\n" +
                "3. 원하는 연산 기록 삭제\n" +
                "4. 원하는 값보다 큰 기록들 불러오기");
        System.out.println("===========================================================================");

        System.out.print("번호 선택: ");
        // 원하는 기능을 뜻하는 번호를 선택
        String numberString = scanner.nextLine();
        // 올바른 번호를 입력할 때까지 반복해서 입력받음
        while(!parser.readNumber(numberString) || Integer.parseInt(numberString) <= 0 || Integer.parseInt(numberString) > 4){
            if(!parser.readNumber(numberString)){
                System.out.print("잘못된 입력 형식입니다. 1-3 사이의 번호를 입력해주세요: ");
                numberString = scanner.nextLine();
            }
            else{
                System.out.print("잘못된 번호입니다. 1-3 사이의 번호를 입력해주세요: ");
                numberString = scanner.nextLine();
            }
        }

        int flag = Integer.parseInt(numberString);
        if(flag == 1){
            calculateNumbers();
        }
        else if(flag == 2){
            System.out.print("몇 번째 연산 기록을 불러올까요? 총 " + service.getSize() + "번의 기록이 있습니다: ");
            String number = getNumber();
            service.showRecord(Integer.parseInt(number));
        }
        else if(flag == 3){
            System.out.print("몇 번째 연산 기록을 제거할까요? 총 " + service.getSize() + "번의 기록이 있습니다: ");
            String number = getNumber();
            service.deleteRecord(Integer.parseInt(number));
        }
        else if(flag == 4){
            System.out.print("입력하신 값보다 큰 기록들을 모두 불러옵니다. 값을 입력해주세요: ");
            String number = getDecimal();
            service.showLargerRecord(Double.parseDouble(number));
        }

    }

    private String getNumber(){
        String str = scanner.nextLine();
        while(!parser.readNumber(str)){
            System.out.print("잘못된 입력 형식입니다 정수 형태를 입력해주세요: ");
            str = scanner.nextLine();
        }
        return str;
    }

    private String getDecimal(){
        String str = scanner.nextLine();
        while(!parser.readDecimal(str)){
            System.out.print("잘못된 입력 형식입니다 정수나 실수 형태를 입력해주세요: ");
            str = scanner.nextLine();
        }
        return str;
    }
}
