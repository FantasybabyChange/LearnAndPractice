package com.fantasybaby.normaltest.mactest;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
@Slf4j
public class SingleFileHTTPServer extends Thread {
    private static String contentType="text/plain";
    //设置监听端口
    private static int port = 8080;

    private static String encoding="UTF-8";
    private byte[] content;
    private byte[] header;

    private SingleFileHTTPServer(String data, String encoding,
                                 String MIMEType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, MIMEType, port);
    }

    private SingleFileHTTPServer(byte[] data, String encoding, String MIMEType, int port)throws UnsupportedEncodingException {
        this.content=data;
        String header="HTTP/1.0 200 OK\r\n"+
                "Server: OneFile 1.0\r\n"+
                "Access-Control-Allow-Origin: *\r\n"+
                "Access-Control-Allow-Methods: GET POST\r\n"+
                "Content-length: "+this.content.length+"\r\n"+
                "Content-type: "+MIMEType+"\r\n\r\n";
        this.header=header.getBytes("ASCII");
    }

    public static void start(String data){
        Thread t= null;
        try {
            t = new SingleFileHTTPServer(data, encoding, contentType, port);
            log.info("start server");
            t.start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            ServerSocket server=new ServerSocket(port);
            System.out.println("Accepting connections on port "+server.getLocalPort());
            System.out.println("Data to be sent:");
            System.out.write(this.content);

            while (true) {
                Socket connection=null;
                try {
                    connection=server.accept();
                    OutputStream out=new BufferedOutputStream(connection.getOutputStream());
                    InputStream in=new BufferedInputStream(connection.getInputStream());

                    StringBuffer request=new StringBuffer();
                    while (true) {
                        int c=in.read();
                        if (c=='\r'||c=='\n'||c==-1) {
                            break;
                        }
                        request.append((char)c);

                    }

                    //如果检测到是HTTP/1.0及以后的协议，按照规范，需要发送一个MIME首部
                    if (request.toString().indexOf("HTTP/")!=-1) {
                        out.write(this.header);
                    }

                    out.write(this.content);
                    out.flush();

                } catch (IOException e) {
                }finally{
                    if (connection!=null) {
                        connection.close();
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Could not start server. Port Occupied");
        }
    }
}