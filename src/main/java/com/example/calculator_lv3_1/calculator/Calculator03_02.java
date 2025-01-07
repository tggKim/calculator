package com.example.calculator_lv3_1.calculator;

import com.example.calculator_lv3_1.symbol.Symbol02;

import java.util.ArrayList;
import java.util.List;

public class Calculator03_02<T extends Number> {

    private List<Double> answerHistoryList = new ArrayList<>();

    // 기록된 계산 결과 가져오는 메서드
    public void getAnswerRecord(int number){
        try{
            double answer= answerHistoryList.get(number-1);
            System.out.println("===================================================");
            System.out.println(answer);
            System.out.println("===================================================");
        }catch (Exception e){
            System.out.println("===================================================");
            System.out.println(number + "번째의 기록이 없습니다.");
            System.out.println("===================================================");
        }
    }

    public void removeRecord(int number){
        try{
            answerHistoryList.remove(number - 1);
            System.out.println("===================================================");
            System.out.println("삭제되었습니다.");
            System.out.println("===================================================");
        }catch (Exception e){
            System.out.println("===================================================");
            System.out.println(number + "번째의 기록이 없습니다.");
            System.out.println("===================================================");
        }
    }

    // 연산 기록 크기를 리턴하는 메서드
    public int getRecordSize(){
        return answerHistoryList.size();
    }

    // 연산을 진행하는 메서드
    public double calculate(T firstNumber, T secondNumber, Symbol02 symbol){
        double firstValue = firstNumber.doubleValue();
        double secondValue = secondNumber.doubleValue();
        double result;

        if(symbol == Symbol02.ADD){
            result = firstValue + secondValue;
        }
        else if(symbol == Symbol02.SUBTRACT){
            result = firstValue - secondValue;
        }
        else if(symbol == Symbol02.MULTIPLY){
            result = firstValue * secondValue;
        }
        else{
            if (secondValue == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            result = firstValue / secondValue;
        }

        answerHistoryList.add(result);
        return result;
    }

    public void showLargerRecord(Double number){
        List<Double> list = answerHistoryList.stream().filter(value -> value > number)
                .toList();
        if(list.isEmpty()){
            System.out.println("===========================================================================");
            System.out.println("입력하신 값보다 큰 값이 없습니다.");
            System.out.println("===========================================================================");
        }
        else{
            System.out.println("===========================================================================");
            list.forEach(answer-> System.out.print(answer+" "));
            System.out.println();
            System.out.println("===========================================================================");
        }

    }

}
