package com.fantasybaby.file.html2image;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author: liuxi
 * @time: 2018/10/10 20:00
 */
public class ConvertHtml2Image {
    public void getHtmlFromFile(String htmlFile) {
        try {
            Charset charset = Charset.forName("UTF-8");//Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
            CharsetDecoder decoder = charset.newDecoder();
            String path1 = this.getClass().getClassLoader().getResource("").getPath();
            File file1 = new File(path1).getParentFile();
            String path = file1.getAbsolutePath() + File.separator +"resources"+File.separator + htmlFile;
            System.out.println(path);
            RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            CharBuffer cb = CharBuffer.allocate(512);
            int read = channel.read(buffer);
            StringBuffer sb = new StringBuffer();
            while (read != -1) {
                System.out.println("Read " + read);
                buffer.flip();
                decoder.decode(buffer, cb, false);
                cb.flip();
                while (cb.hasRemaining()) {
//                    System.out.println(String.valueOf(cb.get()));
                    sb.append(String.valueOf(cb.get()));
                }

                buffer.clear();
                cb.clear();
                read = channel.read(buffer);
            }
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToImage(){
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        String html = "<div style=\"width: 500px; height: 500px;\"><img src=\"http://car0.autoimg.cn/upload/spec/8090/u_20101129100303187264.jpg\" width=\"350\" height=\"233\"><a href=\"#\" style=\"color: red;\">testImage</a></div>";
//        File f = new File("C:\\workspace\\javaSeWorkspace\\daliy_test\\src\\main\\resources\\html\\print.html");

        imageGenerator.loadHtml(html);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageGenerator.getBufferedImage();
        imageGenerator.saveAsImage("d:/hello-world.png");
        imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
    }

    public static void main(String[] args) {
//        new ConvertHtml2Image().convertToImage();
        new ConvertHtml2Image().getHtmlFromFile("print.html");
    }
}
