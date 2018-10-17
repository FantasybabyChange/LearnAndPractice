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
        List<OrderDetail> lists = Lists.newArrayList();
        OrderDetail o = new OrderDetail();
        o.setQuantity(1);
        o.setUserBean(userBean);
        lists.add(o);
        OrderDetail o1 = new OrderDetail();
        o1.setQuantity(2);
        UserBean userBean1 = new UserBean();
        userBean1.setAddress("上海虹桥1");
        userBean1.setUserAge(13);
        userBean1.setUserName("中华田园犬1");
        o1.setUserBean(userBean1);
        lists.add(o1);
        map.put("items",lists);
        map.put("sos",false);
        map.put("startIndex",1);
        Writer out = new StringWriter();
        template.process(map, out);
        return out.toString();
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
