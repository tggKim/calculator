package com.example.calculator_lv2;

import java.util.Optional;
import java.util.Scanner;

public class App01 {
    public static void main(String[] args){
        // 입력받기 위한 Scanner
        Scanner sc = new Scanner(System.in);

        // 계속 입력받을지 여부를 저장하는 변수
        String flag = "continue";

        // Calculator02 클래스의 내부 메서드를 사용하기 위해서 변수로 선언
        Calculator02 calculator = new Calculator02();

        // "exit"을 입력받을 때까지 반복문이 반복됨
        while(!flag.equals("exit")) {

            // 기능을 번호를 통해서 선택하도록 함
            System.out.println("원하시는 기능을 번호를 입력해서 선택해주세요");
            System.out.println("=======================================================================");
            System.out.println(
                    "1.사칙연산 계산하기\n" +
                    "2.가장 오래된 연산 출력하기\n" +
                    "3.가장 오래된 연산 기록 삭제하기\n" +
                    "4.특정 연산 기록 불러오기\n" +
                            "5.특정 연산 기록 삭제하기");
            System.out.println("=======================================================================");
            System.out.print("번호 선택: ");
            // 원하는 기능을 뜻하는 번호를 선택
            int calculatorLogicFlag = calculator.readInteger(sc);
            // 올바른 번호를 입력할 때까지 반복해서 입력받음
            while(calculatorLogicFlag <= 0 || calculatorLogicFlag > 5){
                System.out.print("잘못된 번호입니다 다시 선택해주세요: ");
                calculatorLogicFlag = calculator.readInteger(sc);
            }

            // 입력한 번호에 맞는 기능을 수행
            if(calculatorLogicFlag == 1){
                calculateNumbersAndPrint(sc, calculator);
            }
            else if(calculatorLogicFlag == 2){
                calculator.showOldestRecord();
            }
            else if(calculatorLogicFlag == 3){
                calculator.removeOldestRecord();
            }
            else if(calculatorLogicFlag == 4){
                int size =  calculator.getRecordSize();
                if(size == 0){
                    System.out.println("기록된 연산이 없습니다 연산을 진행해 주세요.");
                }
                else{
                    System.out.print("몇 번째 연산 기록을 불러올까요? 총 " + size + "번의 기록이 있습니다: ");
                    int recordNumber = calculator.readInteger(sc);
                    Optional<Double> o1 = calculator.getAnswerRecord(recordNumber);
                    Optional<String> o2 = calculator.getProblemRecord(recordNumber);
                    while(o1.isEmpty() && o2.isEmpty()){
                        System.out.print("원하시는 번쨰의 연산 기록이 없습니다. " + size + "번의 기록이 있으므로 이에 해당하는 연산의 순서를 입력해주세요: ");
                        recordNumber = calculator.readInteger(sc);
                        o1 = calculator.getAnswerRecord(recordNumber);
                        o2 = calculator.getProblemRecord(recordNumber);
                    }
                    System.out.println(recordNumber + "번째 계산 기록: " + o2.get() + " = " + o1.get());
                }
            }
            else{
                int size =  calculator.getRecordSize();
                if(size == 0){
                    System.out.println("기록된 연산이 없습니다 연산을 진행해 주세요.");
                }
                else{
                    System.out.print("몇 번째 연산 기록을 삭제할까요? 총 " + size + "번의 기록이 있습니다: ");
                    int recordNumber = calculator.readInteger(sc);
                    boolean b = calculator.deleteRecord(recordNumber);
                    while(!b){
                        System.out.print("원하시는 번쨰의 연산 기록이 없습니다. " + size + "번의 기록이 있으므로 이에 해당하는 연산의 순서를 입력해주세요: ");
                        recordNumber = calculator.readInteger(sc);
                        b = calculator.deleteRecord(recordNumber);
                    }
                    System.out.println(recordNumber + "번째 계산 기록이 삭제되었습니다.");
                }
            }

            // 버퍼를 비우지 않으면 앞서 입력하고 남아있을 수 있는 개행문자 때문에 빈 문자열 바로 반환해버림
            sc.nextLine();
            System.out.print("더 계산기를 이용하시겠습니까? 진행하려면 엔터를 누르세요 (exit 입력 시 종료): ");
            flag = sc.nextLine();

            // 다음 입력과 1칸 띄우기
            System.out.println();
        }

    }

    public static void calculateNumbersAndPrint(Scanner sc, Calculator02 calculator){

        boolean firstNumberFlag = false;
        boolean secondNumberFlag = false;

        System.out.print("첫 번째 숫자를 입력하세요: ");
        int firstNumber = calculator.readInteger(sc);

        System.out.print("두 번째 숫자를 입력하세요: ");
        int secondNumber = calculator.readInteger(sc);

        String symbol = calculator.readSymbol(sc);

        // 연산을 수행
        double answer = calculator.calculate(firstNumber, secondNumber, symbol, sc);

        // 연산 결과를 출력
        calculator.printAnswer(symbol, answer);
    }
}
