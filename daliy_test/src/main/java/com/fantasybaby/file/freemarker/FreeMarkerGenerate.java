package com.fantasybaby.file.freemarker;

import com.fantasybaby.file.html2image.ConvertHtml2ImageNew;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gui.ava.html.Html2Image;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;
import java.util.HashMap;
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
    public static String testFreeMarker() throws IOException, TemplateException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_27);
        config.setDefaultEncoding("GBK");
        config.setDirectoryForTemplateLoading(new File(getTemplatePath()));
        Template template = config.getTemplate("print.ftl");
        Map<String,Object> map = new HashMap<>();
        UserBean userBean = new UserBean();
        userBean.setAddress("上海虹桥");
        userBean.setUserAge(13);
        userBean.setUserName("中华田园犬");
        map.put("user",userBean);
        Writer out = new StringWriter();
        template.process(map, out);
        return out.toString();
    }

    public static void main(String[] args) {
        try {
            String freeMarker = FreeMarkerGenerate.testFreeMarker();
            ConvertHtml2ImageNew.convertToImageNew(freeMarker,"D://hello.png");
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
