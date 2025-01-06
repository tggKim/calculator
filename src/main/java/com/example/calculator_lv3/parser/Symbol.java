package com.example.calculator_lv3.parser;

public enum Symbol {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String symbol;

    private Symbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public static Symbol fromSymbol(String symbol) {
        for (Symbol op : Symbol.values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        return null;
    }
}
