package com.chen2059.NIO;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestFilesWalkFileTree {
    public static void main(String[] args) {
        try {
            final AtomicInteger filecount = new AtomicInteger();
            final AtomicInteger dircount = new AtomicInteger();
            Files.walkFileTree(Paths.get("/Users/chenguozhen/Downloads/apache-zookeeper-3.5.9-bin"), new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    dircount.incrementAndGet();
                    System.out.println("dir======" + dir.getFileName());
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    filecount.incrementAndGet();
                    System.out.println(file.getFileName());
                    return super.visitFile(file, attrs);
                }

            });

            System.out.println(filecount);
            System.out.println(dircount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
