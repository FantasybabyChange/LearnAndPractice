package com.fantasybaby.file.writefile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liuxi
 * @time: 2018/8/2 16:45
 */
/*
使用递归找出某目录("C:\\JavaProducts")下的所有子目录以及子文件
*/
public class SearchKeyWords {
    public static void main(String[] args) {
        List<File> paths = new ArrayList<>();
        paths = getAllFilePaths(new File("C:\\workspace\\viva-wes\\viva-wes-device"), paths);
        for (File path : paths) {
            readFile(path);
        }
    }

    public static String readFile(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.read() != -1){
                String s = bufferedReader.readLine();
                if(s != null && s.indexOf("I18n.getValue(\"") > 0){
                    System.out.println(s);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static List<File> getAllFilePaths(File filePath, List<File> filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
//                filePaths.add(f.getPath());
                getAllFilePaths(f, filePaths);
            } else if(f.getName().lastIndexOf(".java") > 0){
                filePaths.add(f);
            }
        }
        return filePaths;
    }
}
