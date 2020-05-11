package com.jsj.jdk.demo.io.buffer;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * 堆外内存使用示例
 */
public class DirectBufferDemo {
    public static void main(String[] args) throws Throwable {
        ByteBuffer buffer = ByteBuffer.allocateDirect(64);
        int i = 0;
        while (true) {
            if (++i % 50 == 0) {
                ((DirectBuffer) buffer).cleaner().clean();
                buffer = ByteBuffer.allocateDirect(64);
            }
            buffer.put((byte) 'a');
            System.out.println(buffer);
            Thread.sleep(500);
        }
    }
}
