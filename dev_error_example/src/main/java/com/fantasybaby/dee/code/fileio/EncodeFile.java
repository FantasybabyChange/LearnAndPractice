package com.fantasybaby.dee.code.fileio;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StopWatch;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    /**
     * 默认使用UTF-8
     *
     * @throws IOException
     */
    public static void readGBKFileNoCharset() throws IOException {

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


    private static void readGBKFileCharset() throws IOException {
        char[] chars = new char[10];
        String content = "";
        try (FileInputStream fileInputStream = new FileInputStream("hello.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("GBK"))) {
            int count;
            while ((count = inputStreamReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        log.info("result: {}", content);
    }

    /**
     * read all line
     * 会OOM
     *
     * @throws IOException
     */
    private static void readGBKFileAllLineCharset() throws IOException {
        log.info("result: {}", Files.readAllLines(Paths.get("hello.txt"), Charset.forName("GBK")).stream().findFirst().orElse(""));
    }

    private static void readFileLimit() throws IOException {
        //输出文件大小
        log.info("file size:{}", Files.size(Paths.get("test.txt")));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("read 200000 lines");
        //使用Files.lines方法读取20万行数据
        log.info("lines {}", Files.lines(Paths.get("test.txt")).limit(200000).collect(Collectors.toList()).size());
        stopWatch.stop();
        stopWatch.start("read 2000000 lines");
        //使用Files.lines方法读取200万行数据
        log.info("lines {}", Files.lines(Paths.get("test.txt")).limit(2000000).collect(Collectors.toList()).size());
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        AtomicLong atomicLong = new AtomicLong();
        //使用Files.lines方法统计文件总行数
        Files.lines(Paths.get("test.txt")).forEach(line -> atomicLong.incrementAndGet());
        log.info("total lines {}", atomicLong.get());
    }

    public static void main(String[] args) throws IOException {
        writeGBKFile();
        readGBKFileNoCharset();
        readGBKFileCharset();
        readGBKFileAllLineCharset();
        readFileLimit();
    }

}
