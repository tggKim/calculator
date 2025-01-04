package com.example.calculator_lv2;
import java.sql.SQLOutput;
import java.util.*;

public class Calculator02 {
    private List<Double> answerHistoryList = new ArrayList<>();
    private List<String> problemHistoryList = new ArrayList<>();

    // 계산할 피연산자를 입력받기 위한 메서드
    public int readInteger(Scanner sc){
        // 반복문이 실행되도록 초기값은 false로 지정
        boolean flag = false;
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
    public String readSymbol(Scanner sc){
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
    public double calculate(int firstNumber, int secondNumber, String symbol, Scanner sc){
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
                secondNumber = readInteger(sc);
            }

            // 나누기 연산에서는 소수계산이 되어야 하므로 double로 형변환 한다
            answer = (double)firstNumber / secondNumber;
        }

        // 계산 결과를 List에 저장함
        problemHistoryList.add(firstNumber + " " + symbol + " " + secondNumber);
        answerHistoryList.add(answer);
        return answer;
    }

    // 결과를 출력하는 메서드
    public void printAnswer(String symbol, double answer){
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

    // 첫 번째 원소 제거하는 메서드
    public void removeOldestRecord(){
        if(!problemHistoryList.isEmpty()){
            System.out.println("=======================================================================");
            System.out.println("제거되었습니다.");
            System.out.println("=======================================================================");
            problemHistoryList.remove(0);
            answerHistoryList.remove(0);
        }
        else{
            System.out.println("=======================================================================");
            System.out.println("연산을 한 기록이 없습니다. 연산을 진행해 주세요");
            System.out.println("=======================================================================");
        }
    }

    // 첫 번째 원소 조회하는 메서드
    public void showOldestRecord(){
        if(!problemHistoryList.isEmpty()){
            System.out.println("=======================================================================");
            System.out.println("가장 오래된 연산: " + problemHistoryList.get(0) + " = " + answerHistoryList.get(0));
            System.out.println("=======================================================================");
        }
        else{
            System.out.println("=======================================================================");
            System.out.println("연산을 한 기록이 없습니다. 연산을 진행해 주세요");
            System.out.println("=======================================================================");
        }
    }

    public Optional<Double> getAnswerRecord(int number){
        Optional<Double> optional;
        if(answerHistoryList.size() < number){
            optional = Optional.empty();
        }
        else{
            optional = Optional.of(answerHistoryList.get(number - 1));
        }
        return optional;
    }

    public Optional<String> getProblemRecord(int number){
        Optional<String> optional;
        if(problemHistoryList.size() < number){
            optional = Optional.empty();
        }
        else{
            optional = Optional.of(problemHistoryList.get(number - 1));
        }
        return optional;
    }

    public boolean deleteRecord(int number){
        if(answerHistoryList.size() < number){
            return false;
        }
        else{
            answerHistoryList.remove(number - 1);
            problemHistoryList.remove(number - 1);
            return true;
        }
    }

    // 연산 기록을 리턴하는 메서드
    public int getRecordSize(){
        return answerHistoryList.size();
    }
}
