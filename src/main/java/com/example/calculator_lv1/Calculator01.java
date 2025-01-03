package com.example.calculator_lv1;
import java.util.*;
// 2개의 숫자와 사칙연산의 문자를 받음
// "exit" 을 입력받을 때까지 계속해서 값을 받고 연산 결과를 반환

public class Calculator01 {
    public static void main(String[] args){

        // 입력받기 위한 Scanner
        Scanner sc = new Scanner(System.in);

        // 입력받을 두 수를 저장할 변수
        int firstNumber, secondNumber;

        // 입력받을 사칙연산의 기호 저장할 변수
        String symbol = "start";

        // 계속 입력받을지 여부를 저장하는 변수
        String flag = "continue";

        // 계산한 값을 저장할 변수
        double answer;

        // 입력받은 수가 정수 형태인지 판별할때 사용할 플레그
        boolean firstNumberFlag = false;
        boolean secondNumberFlag = false;

        while(!flag.equals("exit")) {

            // 계산할 첫 번째 수 입력 받고 검증
            System.out.print("첫 번째 숫자를 입력하세요: ");
            firstNumber = readInteger(sc, firstNumberFlag);

            // 계산할 두 번째 수 입력 받고 검증
            System.out.print("두 번째 숫자를 입력하세요: ");
            secondNumber = readInteger(sc, secondNumberFlag);

            // 사칙연산 부호를 읽고 검증함
            symbol = readSymbol(sc);

            // 연산을 수행
            answer = calculate(firstNumber, secondNumber, symbol, sc, secondNumberFlag);

            // 연산 결과를 출력
            printAnswer(symbol, answer);

            // 버퍼를 비우지 않으면 앞서 입력하고 남아있을 수 있는 개행문자 때문에 빈 문자열 바로 반환해버림
            sc.nextLine();
            System.out.print("더 계산하시겠습니까? 진행하려면 엔터를 누르세요 (exit 입력 시 종료): ");
            flag = sc.nextLine();

            // 다음 입력과 1칸 띄우기
            System.out.println();
        }
    }

    // 계산할 피연산자를 입력받기 위한 메서드
    public static int readInteger(Scanner sc, boolean flag){
        //입력받을 정수 저장할 지역변수
        int number = 0;

        // 입력받은 값이 정수 형태일 때까지 반복해서 입력받음
        while(!flag){
            // nextInt() 는 입력받은 토큰이 정수형태가 아니면 예외가 터지므로 try문 사용
            try{
                number = sc.nextInt();
                // number = sc.nextInt(); 에서 예외가 터지지 않으면 flag가 true로 바뀌어서 반복문 종료됨
                flag = true;
            }catch (InputMismatchException e){
                // 만약 입력에서 정수형태가 아닌 값을 입력시 catch문으로 들어옴

                // 이렇게 버퍼를 지우지 않으면 잘못입력한 토큰이 남아있어서 무한 반복에 빠지게 됨
                sc.nextLine();
                System.out.print("정수만 입력하실 수 있습니다. 다시 입력하세요: ");
            }
        }

        // 다음 계산 입력을 위해서 플래그 값 false로 설정한다. 하지않으면 다음 입력에서 정수입력이 스킵된다.
        flag = false;
        return number;
    }

    // 사칙연산 부호를 입력받는 메서드
    public static String readSymbol(Scanner sc){
        String symbol;

        // 부호를 입력
        System.out.print("사칙연산 기호를 입력하세요: ");
        symbol = sc.next();

        // while문을 통해서 사칙연산 부호를 입력할때까지 반복함
        while(!symbol.equals("+") && !symbol.equals("-") && !symbol.equals("*") && !symbol.equals("/")){
            System.out.print("올바른 사칙연산 부호를 입력해 주세요 +,-,*,/ 만 허용됩니다: ");
            symbol = sc.next();
        }
        return symbol;
    }

    // 계산을 진행하는 메서드
    public static double calculate(int firstNumber, int secondNumber, String symbol, Scanner sc, boolean secondNumberFlag){
        double answer;

        // 사칙연산 부호에 따라 연산을 진행
        if(symbol.equals("+")){
            answer = firstNumber + secondNumber;
        }
        else if(symbol.equals("-")){
            answer = firstNumber - secondNumber;
        }
        else if(symbol.equals("*")){
            answer = firstNumber * secondNumber;
        }
        else{

            /*
            나누기 연산에서 분모가 0이면 예외가 터지므로 이를 방지하도록 secondNumber가 0인 동안에는
            다시 입력하도록 반복문을 수행
            */
            while(secondNumber == 0){
                System.out.print("나누는 정수는 0일 될 수 없습니다. 다른 정수를 입력해 주세요: ");
                // 여기서 readInteger()를 사용해서 정수형태의 문자 입력받음
                secondNumber = readInteger(sc, secondNumberFlag);
            }

            // 나누기 연산에서는 소수계산이 되어야 하므로 double로 형변환 한다
            answer = (double)firstNumber / secondNumber;
        }
        return answer;
    }

    // 결과를 출력하는 메서드
    public static void printAnswer(String symbol, double answer){
        System.out.println("=======================================================================");

        // 나누기 연산에서는 소수점까지 출력
        if(symbol.equals("/")){
            System.out.println("계산 결과: " + answer);
        }
        else{
            // 나머지 연산이 아닌 연산에서는 소수점이 나오지 않게 출력
            System.out.println(String.format("계산 결과: %.0f", answer));
        }
        System.out.println("=======================================================================");
    }
}
