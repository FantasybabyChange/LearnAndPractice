package com.fantasybaby.file;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author: liuxi
 * @time: 2020/1/6 11:26
 */
public class ReadAndReplace {
    String sql ="insert into `basic_container` ( `container_code`, `tenant_id`, `warehouse_id`, `container_type_id`, `super_container_id`, `container_state`, `use_range`, `rfid_code`, `digital_code`, `state`, `created_time`, `created_user`, `created_app`, `last_updated_time`, `last_updated_user`, `last_updated_app`) values('T-1','1','9001','3',NULL,NULL,NULL,NULL,'%s','effective',now(),'快仓管理员','EVO_BASIC',now(),'快仓管理员','EVO_BASIC');\n" +
            "insert into `basic_container` ( `container_code`, `tenant_id`, `warehouse_id`, `container_type_id`, `super_container_id`, `container_state`, `use_range`, `rfid_code`, `digital_code`, `state`, `created_time`, `created_user`, `created_app`, `last_updated_time`, `last_updated_user`, `last_updated_app`) values('T-1-01','1','9001','4','6',NULL,NULL,NULL,'%d','effective',now(),'Basic_Server','EVO_BASIC',now(),'Basic_Server','EVO_BASIC');";
    List<String> containerLists = Lists.newArrayList();
    public void readFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line =bufferedReader.readLine();

        while (StringUtils.isNotEmpty(line)){
            line = bufferedReader.readLine();
            containerLists.add(line);
        }
        bufferedReader.close();
        fileReader.close();
    }
    public void generateSql(){
        int i = 0;
        for (String containerList : containerLists) {
            if(StringUtils.isEmpty(containerList)){
                continue;
            }
            String s = sql.replaceAll("T-1", containerList).replaceAll("%s",i++ + "").replaceAll("%d",i++ + "");
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        ReadAndReplace readAndReplace = new ReadAndReplace();
        readAndReplace.readFile("D:\\contaienr.txt");
        readAndReplace.generateSql();

    }

}
