package com.jsj.jdk.demo.constractor;

public class SubClass extends SuperClass {

    {
        System.out.println("子类的构造代码块");
    }

    Member m1 = new Member("子类的成员变量");

    public SubClass() {
        super();
        System.out.println("子类的构造函数");
    }

    public static void main(String[] args) {
        new SubClass();
    }
}
