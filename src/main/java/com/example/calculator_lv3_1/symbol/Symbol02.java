package com.example.calculator_lv3_1.symbol;

public enum Symbol02 {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String symbol;

    private Symbol02(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public static Symbol02 fromSymbol(String symbol) {
        for (Symbol02 op : Symbol02.values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        return null;
    }
}
