package com.jsj.jdk8.lambda;

/**
 * Lambda表达式将函数作为参数传入方法中，用于函数式接口。
 * <p>
 * 语法格式如下：
 * <p>
 * (parameters) -> expression 或 (parameters) ->{ statements; }
 * <p>
 * • 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * • 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * • 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * • 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 *
 * @author jiangshenjie
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //声明了方法参数类型
        MathOperation add = (int a, int b) -> a + b;
        //不声明参数类型
        MathOperation sub = (a, b) -> a - b;
        //加大括号的返回语句(推荐使用)
        MathOperation multi = (int a, int b) -> {
            return a * b;
        };
        //不加大括号
        MathOperation div = (int a, int b) -> a / b;

        LambdaDemo demo = new LambdaDemo();
        System.out.println("10 + 5 = " + demo.operate(10, 5, add));
        System.out.println("10 - 5 = " + demo.operate(10, 5, sub));
        System.out.println("10 x 5 = " + demo.operate(10, 5, multi));
        System.out.println("10 / 5 = " + demo.operate(10, 5, div));
    }

    private int operate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }

    interface MathOperation {
        int operate(int a, int b);
    }

}
