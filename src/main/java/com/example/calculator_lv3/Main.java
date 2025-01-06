package com.example.calculator_lv3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        App02 app02 = new App02();
        Scanner scanner = new Scanner(System.in);

        String flag = "true";
        while(!flag.equals("exit")){
            app02.option();
            System.out.print("계산기를 계속 이용하려면 엔터를 입력하세요(exit을 입력 후 엔터를 누르면 종료됩니다): ");
            flag = scanner.nextLine();
            System.out.println();
        }
    }
}



