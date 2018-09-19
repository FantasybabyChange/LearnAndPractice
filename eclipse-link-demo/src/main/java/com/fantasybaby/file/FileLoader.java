package com.fantasybaby.file;

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
 * @author reid.liu
 * @date 2018-09-19 18:04
 */
public class FileLoader {
    private static String rootPath = "resources";
    public String getPath(String fileName){
        String path1 = this.getClass().getClassLoader().getResource("").getPath();
        File file1 = new File(path1).getParentFile();
        return file1.getAbsolutePath()+File.separator+rootPath+File.separator+fileName;
    }
    public String readFileToString(String fileName){
        String filePath = getPath(fileName);
        Charset charset = Charset.forName("UTF-8");//Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
        CharsetDecoder decoder = charset.newDecoder();
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(new File(filePath),"rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            CharBuffer cb = CharBuffer.allocate(10);
            int read = channel.read(buffer);
            StringBuffer sb= new StringBuffer();
            while (read != -1){
                buffer.flip();
                decoder.decode(buffer,cb,false);
                cb.flip();
                while (cb.hasRemaining()) {
                 sb.append(String.valueOf(cb.get()));
                }
                buffer.clear();
                cb.clear();
                read = channel.read(buffer);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        new FileLoader().readFileToString("json.txt");
    }
}
