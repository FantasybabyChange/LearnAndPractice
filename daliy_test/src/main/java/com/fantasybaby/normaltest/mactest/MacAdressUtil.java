package com.fantasybaby.normaltest.mactest;

import com.fantasybaby.unicode.encrypt.MD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author FantasyBaby
 */
@Slf4j
public class MacAdressUtil {
    public static void getAllNetWorkMac() throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        List<String> allMacs = new ArrayList();
        List<String> onlyMacs = new ArrayList();
        while(networkInterfaces.hasMoreElements()){
            StringBuffer sb = new StringBuffer("");
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if(Objects.nonNull(networkInterface)){

                List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
                StringBuffer ips = new StringBuffer("");
                for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                    InetAddress address = interfaceAddress.getAddress();
                    String hostAddress = address.getHostAddress();
                    if(StringUtils.isNotBlank(hostAddress)&&IpUtil.isIp(hostAddress)){
                        ips.append(hostAddress);
                    }
                }
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if(Objects.nonNull(hardwareAddress)){
                    sb.setLength(0);
                    for (int i = 0; i < hardwareAddress.length; i++) {
                        if(i != 0){
                            sb.append("-");
                        }
                        int temp = hardwareAddress[i]&0xff;
                        String str = Integer.toHexString(temp);
                        if(str.length() == 1){
                            sb.append("0"+str);
                        }else{
                            sb.append(str);
                        }
                    }
                }
                if(sb.length() > 0 && ips.length() > 0){
                    allMacs.add(sb.toString().toUpperCase()+":"+ips.toString());
                    onlyMacs.add(sb.toString().toUpperCase());
                }
            }
        }
        allMacs.forEach(System.out::println);
    }

    public static void main(String[] args) {
        try {
            getAllNetWorkMac();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
