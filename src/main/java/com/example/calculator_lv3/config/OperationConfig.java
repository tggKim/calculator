package com.example.calculator_lv3.config;

import com.example.calculator_lv3.calculator.Calculator03;
import com.example.calculator_lv3.operation.*;
import com.example.calculator_lv3.parser.Symbol;

public class OperationConfig {

    public static Calculator03 calculator03 = new Calculator03();

    public void setOperation(Symbol symbol){
        if(symbol == Symbol.ADD){
            calculator03.setOperation(new AddOperation());
        }
        else if(symbol == Symbol.SUBTRACT){
            calculator03.setOperation(new SubtractOperation());
        }
        else if(symbol == Symbol.MULTIPLY){
            calculator03.setOperation(new MultiplyOperation());
        }
        else{
            calculator03.setOperation(new DivideOperation());
        }
    }
}
