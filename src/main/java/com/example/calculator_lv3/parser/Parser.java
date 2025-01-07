package com.example.calculator_lv3.parser;

import com.example.calculator_lv3.calculator.Calculator03;
import com.example.calculator_lv3.config.OperationConfig;
import com.example.calculator_lv3.exception.DivisionByZeroException;
import com.example.calculator_lv3.exception.InvalidNumberException;
import com.example.calculator_lv3.exception.InvalidOperatorException;

import java.util.regex.Pattern;

public class Parser {
    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^-?[0-9]+$";
    private static final String DECIMAL_REG = "^-?[0-9]+(\\.[0-9]+)?$";

    public double parseFirstNum(String firstInput) throws InvalidNumberException {
        // 구현 1. 패턴이 일치하면 입력한 값을 정수로 변환시켜서 Calculator에 저장
        // 패턴이 일치하지 않으면 예외를 던짐
        if(!Pattern.matches(NUMBER_REG, firstInput)) {
            throw new InvalidNumberException("잘못된 입력 형식입니다 정수 형태를 입력해주세요: ");
        }
        else{
            return Double.parseDouble(firstInput);
        }
    }

    public double parseSecondNum(String secondInput) throws InvalidNumberException {
        // 패턴이 일치하면 입력한 값을 정수로 변환시켜서 Calculator에 저장
        // 패턴이 일치하지 않으면 예외를 던짐
        if(!Pattern.matches(NUMBER_REG, secondInput)){
            throw new InvalidNumberException("잘못된 입력 형식입니다 정수 형태를 입력해주세요: ");
        }
        else{
            return Double.parseDouble(secondInput);
        }
    }

    public Symbol parseOperator(String operationInput) throws InvalidOperatorException {
        // 패턴이 일치하면 입력한 부호에 해당하는 구현 클래스를 Calculator에 주입해준다.
        // 패턴이 일치하지 않으면 예외를 던짐
        if(!Pattern.matches(OPERATION_REG, operationInput)) {
            throw new InvalidOperatorException("사칙연산 부호에 해당하는 문자를 입력해 주세요: ");
        }
        else{
            // operationInput에 해당하는 Symbol을 찾음
            return Symbol.fromSymbol(operationInput);
        }
    }

    public boolean readNumber(String number){
        return Pattern.matches(NUMBER_REG, number);
    }

    public boolean readDecimal(String number){
        return Pattern.matches(DECIMAL_REG, number);
    }
}
