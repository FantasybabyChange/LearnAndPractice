package com.fantasybaby.normaltest.mactest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * @author FantasyBaby
 */
@Slf4j
public class MacAdressUtil {
    public static String getAllNetWorkMac() throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        List<String> allMacs = new ArrayList();
        List<String> onlyMacs = new ArrayList();
        while(networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if(networkInterface.isVirtual()||networkInterface.isLoopback()||!networkInterface.isUp()){
                continue;
            }
            StringBuffer sb = new StringBuffer("");
            if(Objects.nonNull(networkInterface)){

                List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
                StringBuffer ips = new StringBuffer("");
                for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                    InetAddress address = interfaceAddress.getAddress();
                    String hostAddress = address.getHostAddress();
                    if(StringUtils.isNotBlank(hostAddress)&&!address.isLoopbackAddress()&&address.isSiteLocalAddress()&&!networkInterface.isVirtual()){
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
        StringBuffer sb = new StringBuffer("");
        onlyMacs.forEach(mac -> sb.append(mac));
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String allNetWorkMac = getAllNetWorkMac();
            SingleFileHTTPServer.start(allNetWorkMac);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
