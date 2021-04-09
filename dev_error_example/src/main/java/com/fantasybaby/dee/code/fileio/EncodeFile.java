package com.fantasybaby.dee.code.fileio;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件编码问题
 * Created on 4/9/2021.
 *
 * @author Reid Liu
 */
@Slf4j
public class EncodeFile {
    public static void writeGBKFile() throws IOException {
        Files.deleteIfExists(Paths.get("hello.txt"));
        Files.write(Paths.get("hello.txt"), "你好hi".getBytes(Charset.forName("GBK")));
        log.info("bytes:{}", Hex.encodeHexString(Files.readAllBytes(Paths.get("hello.txt"))).toUpperCase());
    }

    public static void readGBKFile() throws IOException {

        char[] chars = new char[10];
        String content = "";
        try (FileReader fileReader = new FileReader("hello.txt")) {
            int count;
            while ((count = fileReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        log.info("result:{}", content);
    }

    public static void main(String[] args) throws IOException {
        writeGBKFile();
        readGBKFile();
    }

}
