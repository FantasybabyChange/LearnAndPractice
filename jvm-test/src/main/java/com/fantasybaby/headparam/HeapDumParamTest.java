package com.fantasybaby.headparam;

import java.util.Vector;

/**
 * -XX:+HeapDumpOnOutOfMemoryError
 *  -XX:+HeapDumpPath
 * @author: liuxi
 * @time: 2018/7/24 18:02
 */
public class HeapDumParamTest {
    public static void main(String[] args) {
        Vector v=new Vector();
        for(int i=0;i<25;i++)
            v.add(new byte[1*1024*1024]);

    }
}
