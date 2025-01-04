package com.example;
import java.util.*;







public class Main {
    public static void main(String[] args) {
        Parent p = new Child();

    }
}


class Parent {
    void show() {
        System.out.println("Parent's show()");
    }
}

class Child extends Parent {

    void show() {
        System.out.println("Child's show()");
    }
}
