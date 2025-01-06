package com.example.calculator_lv3.calculator;

import com.example.calculator_lv3.operation.Operation;
import java.util.ArrayList;
import java.util.*;

public class Calculator03 {

    private Operation operation;

    private List<String> problemHistoryList = new ArrayList<>();
    private List<Double> answerHistoryList = new ArrayList<>();

    private double firstNumber;
    private double secondNumber;

    public void setOperation(Operation operation){
        this.operation = operation;
    }

    public void setFirstNumber(double firstNumber){
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(double secondNumber){
        this.secondNumber = secondNumber;
    }

    // 첫 번째 원소 제거하는 메서드
    public boolean removeOldestRecord(){
        if(!problemHistoryList.isEmpty()){
            problemHistoryList.remove(0);
            answerHistoryList.remove(0);
            return true;
        }
        else{
            return false;
        }
    }

    // 첫 번째 원소 조회하는 메서드
    public Optional<String> showOldestRecord(){
        Optional<String> optional;
        if(!problemHistoryList.isEmpty()){
            optional = Optional.of("가장 오래된 연산: " + problemHistoryList.get(0) + " = " + answerHistoryList.get(0));
        }
        else{
            optional = Optional.empty();
        }
        return optional;
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

    public boolean deleteRecord(int number){
        if(getRecordSize() == 0 || answerHistoryList.size() < number){
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

    // 연산을 진행하는 메서드
    public double calculate(){
        return operation.operate(firstNumber, secondNumber);
    }
}
