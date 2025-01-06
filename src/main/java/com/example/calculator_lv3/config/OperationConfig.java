package com.example.calculator_lv3.config;

import com.example.calculator_lv3.operation.*;
import com.example.calculator_lv3.parser.Symbol;

public class OperationConfig {
    public Operation getOperation(Symbol symbol){
        if(symbol == Symbol.ADD){
            return new AddOperation();
        }
        else if(symbol == Symbol.SUBTRACT){
            return new SubtractOperation();
        }
        else if(symbol == Symbol.MULTIPLY){
            return new MultiplyOperation();
        }
        else{
            return new DivideOperation();
        }
    }
}
