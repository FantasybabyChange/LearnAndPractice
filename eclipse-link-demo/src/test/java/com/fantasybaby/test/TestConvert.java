package com.fantasybaby.test;

import com.fantasybaby.file.FileLoader;
import org.junit.Test;

/**
 * @author reid.liu
 * @date 2018-09-19 19:18
 */
public class TestConvert {
    @Test
    public void loadFile(){
        String s = new FileLoader().readFileToString("json.txt");
        System.out.println(s);
    }
    @Test
    public void convertToObject(){

    }
}
