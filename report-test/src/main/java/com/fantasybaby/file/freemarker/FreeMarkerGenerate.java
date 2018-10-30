package com.fantasybaby.file.freemarker;

import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author fantasybaby
 * @date 2018/10/11
 */
public class FreeMarkerGenerate {
    public static String getTemplatePath(){
        String path1 = FreeMarkerGenerate.class.getClassLoader().getResource("").getPath();
        File file1 = new File(path1).getParentFile();
        return  file1.getAbsolutePath() + File.separator + "resources" + File.separator + "ftl";
    }
    public static String testFreeMarker(String path) throws IOException, TemplateException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_27);
        config.setDefaultEncoding("UTF-8");
        config.setOutputEncoding("UTF-8");
        config.setEncoding(Locale.CHINA,"UTF-8");
        config.setDirectoryForTemplateLoading(new File(getTemplatePath()));
        Template template = config.getTemplate(path);
        Map<String,Object> map = new HashMap<>();
        UserBean userBean = new UserBean();
        userBean.setAddress("上海虹桥");
        userBean.setUserAge(13);
        userBean.setUserName("中华田园犬");
        List<List<OrderDetail>> allList = Lists.newArrayList();

//        generatePage(allList);
        List<OrderDetail> lists = generateNoPage();
        map.put("items",lists);
        map.put("sos",false);
        map.put("startIndex",1);
        Writer out = new StringWriter();
        template.process(map, out);
        return out.toString();
    }

    private static List<OrderDetail> generateNoPage() {
        List<OrderDetail> lists = Lists.newArrayList();
        for (int j = 0; j < 20; j++) {
            OrderDetail o2 = new OrderDetail();
            o2.setQuantity((j+1));
            UserBean userBean2 = new UserBean();
            userBean2.setAddress("上海虹桥"+j);
            userBean2.setUserAge(13);
            userBean2.setUserName("中华田园犬"+j);
            o2.setUserBean(userBean2);
            lists.add(o2);
        }
        return lists;
    }

    private static void generatePage(List<List<OrderDetail>> allList) {
        for (int i = 0; i < 3; i++) {
            List<OrderDetail> lists = Lists.newArrayList();
            for (int j = 0; j < 44; j++) {
                OrderDetail o2 = new OrderDetail();
                o2.setQuantity((i+1)*j);
                UserBean userBean2 = new UserBean();
                userBean2.setAddress("上海虹桥"+(i+1)*j);
                userBean2.setUserAge(13);
                userBean2.setUserName("中华田园犬"+(i+1)*j);
                o2.setUserBean(userBean2);
                lists.add(o2);
            }
            allList.add(lists);
        }
    }

    public static void main(String[] args) {
        try {
            String freeMarker = FreeMarkerGenerate.testFreeMarker("print.ftl");
//            ConvertHtml2ImageNew.convertToImageNew(freeMarker,"D://hello.png");
//            ConvertHtml2ImageNew.convertToImageNew(freeMarker,"D://hello.jpg");
//            ConvertHtml2ImageNew.convertToPdf(freeMarker,"D://hello.pdf");
//            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
//            ImageIO
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
