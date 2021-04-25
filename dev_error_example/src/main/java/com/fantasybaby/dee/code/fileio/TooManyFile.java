package com.fantasybaby.dee.code.fileio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 * 文件编码问题
 * Created on 4/9/2021.
 *
 * @author Reid Liu
 */
@Slf4j
public class TooManyFile {
    public static void writeSimpleFile() throws IOException {
        Files.write(Paths.get("demo.txt"),
                IntStream.rangeClosed(1, 10).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList())
                , UTF_8, CREATE, TRUNCATE_EXISTING);
    }

    /**
     * too many file
     */
    public static void readMultiple() {
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try {
                Files.lines(Paths.get("demo.txt")).forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("total : {}", longAdder.longValue());
    }

    public static void releaseMultiple() {

        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try (Stream<String> lines = Files.lines(Paths.get("demo.txt"))) {
                lines.forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("total : {}", longAdder.longValue());
    }

    public static void main(String[] args) throws IOException {
//        writeSimpleFile();
//        readMultiple();
        releaseMultiple();
    }
}
