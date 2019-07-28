package com.jsj.jdk.demo.buffer;

import java.nio.ByteBuffer;

/**
 * 堆外内存使用示例
 */
public class BufferDemo {
    public static void main(String[] args) throws Throwable {
        ByteBuffer buffer = ByteBuffer.allocateDirect(64);
        while (true) {
            buffer.put((byte) 'a');
            System.out.println(buffer);
            Thread.sleep(5000);
        }
    }
}
