package com.example.calculator_lv3.calculator;

import com.example.calculator_lv3.exception.DivisionByZeroException;
import com.example.calculator_lv3.operation.*;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

public class Calculator03 {

    private Operation operation;

    private List<String> problemHistoryList = new ArrayList<>();
    private List<Double> answerHistoryList = new ArrayList<>();

    public void setOperation(Operation operation){
        this.operation = operation;
    }

    // 기록된 계산 결과 가져오는 메서드
    public Optional<Double> getAnswerRecord(int number){
        Optional<Double> optional;
        if(getRecordSize() == 0 || answerHistoryList.size() < number){
            optional = Optional.empty();
        }
        else{
            optional = Optional.of(answerHistoryList.get(number - 1));
        }
        return optional;
    }

    // 기록된 계산 가져오는 메서드
    public Optional<String> getProblemRecord(int number){
        Optional<String> optional;
        if(getRecordSize() == 0 || problemHistoryList.size() < number){
            optional = Optional.empty();
        }
        else{
            optional = Optional.of(problemHistoryList.get(number - 1));
        }
        return optional;
    }

    public boolean removeRecord(int number){
        if(getRecordSize() == 0 || answerHistoryList.size() < number){
            return false;
        }
        else{
            answerHistoryList.remove(number - 1);
            problemHistoryList.remove(number - 1);
            return true;
        }
    }

    // 연산 기록 크기를 리턴하는 메서드
    public int getRecordSize(){
        return answerHistoryList.size();
    }

    // 연산을 진행하는 메서드
    public double calculate(double firstNumber, double secondNumber) throws DivisionByZeroException {

        if(operation instanceof AddOperation){
            problemHistoryList.add(firstNumber + " + " + secondNumber);
        }
        else if(operation instanceof SubtractOperation){
            problemHistoryList.add(firstNumber + " - " + secondNumber);
        }
        else if(operation instanceof MultiplyOperation){
            problemHistoryList.add(firstNumber + " * " + secondNumber);
        }
        else if(operation instanceof DivideOperation){
            if(secondNumber == 0.0){
                throw new DivisionByZeroException("0으로 나눌 수 없습니다 두 번째 숫자 다시 입력하세요: ");
            }
            problemHistoryList.add(firstNumber + " / " + secondNumber);
        }

        double answer = operation.operate(firstNumber, secondNumber);
        answerHistoryList.add(answer);

        return answer;
    }

    public List<Double> getLargerRecord(double number){
        return answerHistoryList.stream().filter(value -> value > number)
                .toList();
    }
}
