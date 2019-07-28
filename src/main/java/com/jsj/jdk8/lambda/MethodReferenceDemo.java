package com.jsj.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 方法引用
 *
 * @author jiangshenjie
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        //实例方法引用
        Consumer<String> forEach = System.out::println;
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(forEach);
    }
}
