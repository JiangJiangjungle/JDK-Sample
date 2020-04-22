package com.jsj.jdk8.stream.intermediate_operation;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Stream中的map方法示例
 *
 * @author jiangshenjie
 */
public class StreamMapDemo {

    public static void main(String[] args) {
        Collection<String> stringCollection = Arrays.asList("啦啦啦", "ABC", "E", "AWE");
        //输入元素类型为String，输出Character类型的元素
        stringCollection.stream()
                .map(str -> str.charAt(0))
                .forEach(System.out::println);
    }
}
