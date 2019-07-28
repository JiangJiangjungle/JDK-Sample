package com.jsj.jdk8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 集合中元素的映射,可以实现从输入类型到输出类型的转换
 *
 * @author jiangshenjie
 */
public class StreamMapDemo {
    /**
     * 获取集合中每个字符串的第一个字符
     */
    public static void main(String[] args) {
        Collection<String> stringCollection = Arrays.asList("啦啦啦", "ABC", "E");
        //Intermediate操作,输入Stream类型为String，输出Character类型的Stream
        Function<String, Character> function = str -> str.charAt(0);
        //Terminal操作
        Consumer<Character> consumer = System.out::println;
        stringCollection.stream().map(function).forEach(consumer);
    }
}
