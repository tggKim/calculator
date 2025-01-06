package com.example.calculator_lv3.parser;

import com.example.calculator_lv3.calculator.Calculator03;

import java.util.regex.Pattern;

public class Parser {
    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^[0-9]*$";

    private Calculator03 calculator = new Calculator03();

    public void parseFirstNum(String firstInput){
        // 구현 1. 패턴이 일치하면 입력한 값을 정수로 변환시켜서 Calculator에 저장
        // 패턴이 일치하지 않으면 예외를 던짐
        if(!Pattern.matches(NUMBER_REG, firstInput)) {

        }
        else{
            calculator.setFirstNumber(Integer.parseInt(firstInput));
        }
    }

    public void parseSecondNum(String secondInput) {
        // 구현 1. 패턴이 일치하면 입력한 값을 정수로 변환시켜서 Calculator에 저장
        // 패턴이 일치하지 않으면 예외를 던짐
        if(!Pattern.matches(NUMBER_REG, secondInput)){

        }
        else{
            calculator.setSecondNumber(Integer.parseInt(secondInput));
        }
    }

    public void parseOperator(String operationInput) {
        // 구현 1. 패턴이 일치하면 입력한 부호에 해당하는 구현 클래스를 Calculator에 주입해준다.
        // 패턴이 일치하지 않으면 예외를 던짐
        if(!Pattern.matches(OPERATION_REG, operationInput)){

        }
        else{

        }
    }

    // 연산을 수행
    public double executeCalculator(){
        return calculator.calculate();
    }
}
