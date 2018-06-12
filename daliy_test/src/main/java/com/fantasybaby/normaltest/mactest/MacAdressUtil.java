package com.fantasybaby.normaltest.mactest;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Objects;

@Slf4j
public class MacAdressUtil {
    public static void getAllNetWorkMac() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()){
            StringBuffer sb = new StringBuffer("");
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if(Objects.nonNull(networkInterface)){
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if(Objects.nonNull(hardwareAddress)){
                    for (int i = 0; i < hardwareAddress.length; i++) {
                        if(i != 0){
                            sb.append("-");
                        }
                        int temp = hardwareAddress[i]&0xff;
                        String str = Integer.toHexString(temp);
                        log.info("每8位:{}",str);
                        if(str.length() == 1){
                            sb.append("0"+str);
                        }else{
                            sb.append(str);
                        }
                    }
                }
                log.info("mac address {}",sb.toString().toUpperCase());
            }
        }
    }
    /*private static void getLocalMac(InetAddress ia) throws SocketException {
        byte[] hardwareAddress = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        log.info("mace length {}",hardwareAddress.length);
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < hardwareAddress.length; i++) {
            if(i != 0){
                sb.append("-");
            }
            int temp = hardwareAddress[i]&0xff;
            String str = Integer.toHexString(temp);
            log.info("每8位:{}",str);
            if(str.length() == 1){
                sb.append("0"+str);
            }else{
                sb.append(str);
            }
        }
        log.info("本机IP:" + sb.toString());
    }*/

    public static void main(String[] args) {
        InetAddress localHost = null;
        try {
            getAllNetWorkMac();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
