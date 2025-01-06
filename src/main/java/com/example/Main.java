package com.example;
import java.util.*;







public class Main {
    int number = 10;
    public void method1() {
        A a = x -> {
            System.out.println(number);
            //System.out.println(aaa);
        };

        A a2 = new A(){

            @Override
            public void method(int a) {
                System.out.println(Main.this.number);
                System.out.println(aaa);
            }
        };

        method2(this::printNumber);
    }

    public void method2(A a){

    }

    // (int) -> void 시그니처
    public void printNumber(int x) {
        System.out.println("number = " + number);
        System.out.println("aaa = " + A.aaa);  // 인터페이스의 상수 필드는 A.aaa 로 접근
    }

    public static void main(String[] args){
        String str = "210020100021";
        String[] strs = str.split("0");
        for(int i=0;i<strs.length;i++){
            System.out.println(strs[i]);
        }
    }
}

interface A{
    int aaa = 20;
    void method(int a);
}




