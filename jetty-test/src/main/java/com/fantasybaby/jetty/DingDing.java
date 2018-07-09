package com.fantasybaby.jetty;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author liuxi
 * @date2018年07月04日 13:59
 */
public class DingDing {

        public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9c4a70df6c9aa8bd9a8cfdd2eb4568459be484389a7eadd71c3e4f93aa7e45f3";

        public static void main(String args[]) throws Exception{

            HttpClient httpclient = HttpClients.createDefault();

            HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");

            String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"刘波老师来晚了\",text:\"@18701881724    大家都来了  \"},at:{\"atMobiles\":[\"18701881724\"]}}";
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        }
}
