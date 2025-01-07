package com.example.calculator_lv3.service;

import com.example.calculator_lv3.calculator.Calculator03;
import com.example.calculator_lv3.config.OperationConfig;
import com.example.calculator_lv3.exception.DivisionByZeroException;
import com.example.calculator_lv3.operation.AddOperation;
import com.example.calculator_lv3.operation.DivideOperation;
import com.example.calculator_lv3.operation.MultiplyOperation;
import com.example.calculator_lv3.operation.SubtractOperation;
import com.example.calculator_lv3.parser.Symbol;

import java.util.Optional;

public class CalculatorService {
    private Calculator03 calculator = OperationConfig.calculator03;

    public void showRecord(int number){
        Optional<String> problemOptional = calculator.getProblemRecord(number);
        Optional<Double> answerOptional = calculator.getAnswerRecord(number);

        if(problemOptional.isEmpty() && answerOptional.isEmpty()){
            System.out.println("===========================================================================");
            System.out.println("연산을 진행한 기록이 없거나 입력하신 순서에 대한 기록이 없습니다.");
            System.out.println("===========================================================================");
        }
        else{
            System.out.println("===========================================================================");
            System.out.println(problemOptional.get() + " = " + answerOptional.get());
            System.out.println("===========================================================================");
        }
    }

    public void deleteRecord(int number){
        if(calculator.removeRecord(number)){
            System.out.println("===========================================================================");
            System.out.println(number + "번째 연산 기록이 제거되었습니다.");
            System.out.println("===========================================================================");
        }
        else{
            System.out.println("===========================================================================");
            System.out.println("연산을 진행한 기록이 없거나 입력하신 순서에 대한 기록이 없습니다.");
            System.out.println("===========================================================================");
        }
    }

    public void showLargerRecord(double number){
        if( calculator.getLargerRecord(number).isEmpty()){
            System.out.println("===========================================================================");
            System.out.println("입력하신 값보다 큰 값이 없습니다.");
            System.out.println("===========================================================================");
        }
        else{
            System.out.println("===========================================================================");
            calculator.getLargerRecord(number).forEach(answer-> System.out.print(answer+" "));
            System.out.println();
            System.out.println("===========================================================================");
        }
    }

    public void setOperation(Symbol symbol){
        if(symbol == Symbol.ADD){
            calculator.setOperation(new AddOperation());
        }
        else if(symbol == Symbol.SUBTRACT){
            calculator.setOperation(new SubtractOperation());
        }
        else if(symbol == Symbol.MULTIPLY){
            calculator.setOperation(new MultiplyOperation());
        }
        else{
            calculator.setOperation(new DivideOperation());
        }
    }

    public double calculate(double firstNumber, double secondNumber) throws DivisionByZeroException {
        return calculator.calculate(firstNumber, secondNumber);
    }

    public int getSize(){
        return calculator.getRecordSize();
    }
}
