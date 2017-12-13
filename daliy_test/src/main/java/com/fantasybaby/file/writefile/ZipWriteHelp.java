package com.fantasybaby.file.writefile;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by FantasyBaby on 2017-08-13.
 */
public class ZipWriteHelp {
    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        String fileName = "test.txt";
        String zipName = "test.zip";
        String filePath = null;
        try {
            filePath = ZipWriteHelp.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println(filePath);
            filePath = new File(filePath).getParentFile().getParentFile().getPath();
            File zipFile = new File(filePath+File.separator+zipName);
            if(zipFile.exists()){
                zipFile.delete();
            }
            File txtFile = new File(filePath+File.separator+fileName);
            if(txtFile.exists()){
                txtFile.delete();
            }
            BufferedOutputStream txtFileStream = new BufferedOutputStream(new FileOutputStream(txtFile));
            for (int i=1;i<10;i++){
                txtFileStream.write(new String(i+"huiasbiuauiahhah"+"\r\n").getBytes("UTF-8"));
            }

            txtFileStream.flush();
            txtFileStream.close();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(zipFile));
            ZipOutputStream zipStream = new ZipOutputStream(bos);
            zipStream.putNextEntry(new ZipEntry(txtFile.getName()));

            FileInputStream fis = new FileInputStream(txtFile);
            /*int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                zipStream.write(buf, 0, len);
            }*/
            for (int i=1;i<10;i++){
                zipStream.write(new String(i+"huiasbiuauiahhah"+"\r\n").getBytes("UTF-8"));
            }
            fis.close();

            zipStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
