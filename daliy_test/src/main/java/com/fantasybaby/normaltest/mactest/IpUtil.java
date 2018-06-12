package com.fantasybaby.normaltest.mactest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuxi
 * @date2018年06月12日 12:57
 */
public class IpUtil {
    private static final  String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    public static boolean isIp(String addr){
            if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
            {
                return false;
            }
            Pattern pat = Pattern.compile(rexp);

            Matcher mat = pat.matcher(addr);
            return mat.find();
        }
}
