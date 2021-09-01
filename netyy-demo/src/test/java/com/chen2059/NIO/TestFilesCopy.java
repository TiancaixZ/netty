package com.chen2059.NIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @program: netty
 * @description:
 * @author: Chen2059
 * @create: 2021-08-27
 **/
public class TestFilesCopy {
    public static void main(String[] args) {
        String source = "";
        String target = "";

        try {
            Files.walk(Paths.get(source)).forEach(path -> {
                final String replace = path.toString().replace(source, target);

                if (Files.isDirectory(path)){
                    try {
                        Files.createDirectory(Paths.get(replace));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if(Files.isRegularFile(path)){
                    try {
                        Files.copy(path,Paths.get(replace));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
