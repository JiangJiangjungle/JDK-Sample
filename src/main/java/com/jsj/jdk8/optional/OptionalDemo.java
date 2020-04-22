package com.jsj.jdk8.optional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Optional类的使用
 *
 * @author jiangshenjie
 */
public class OptionalDemo {
    public static void main(String[] args) {
        testGetValue();
    }

    /**
     * 利用ofNullable方法构造Optional实例
     */
    public static void testCreateOptional() {
        String value = "hello";
        Optional.ofNullable(value).ifPresent(System.out::println);
    }

    /**
     * 利用orElseGet方法获取值
     */
    public static void testGetValue() {
        String value = null;
        String result = Optional.ofNullable(value)
                .orElseGet(() -> "empty value");
        System.out.println(result);
    }
}
