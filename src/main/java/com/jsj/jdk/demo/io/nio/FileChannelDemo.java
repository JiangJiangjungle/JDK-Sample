package com.jsj.jdk.demo.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        String path = "/Users/jiangshenjie/Public/projects/maven/settings.xml";
        read(path);
    }

    public static void read(String path) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(path, "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int ret = channel.read(buffer);
            for (; ret != -1; ) {
                buffer.flip();
                byte[] dst = new byte[buffer.remaining()];
                buffer.get(dst);
                System.out.print(new String(dst, Charset.defaultCharset()));
                buffer.compact();
                ret = channel.read(buffer);
            }
            System.out.println();
        }
    }

}
