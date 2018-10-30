package com.fantasybaby.file.html2image;

import gui.ava.html.Html2Image;
import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.*;

/**
 * https://code.google.com/archive/p/java-html2image/wikis
 *
 * @author: liuxi
 * @time: 2018/10/10 20:00
 */
public class ConvertHtml2Image {
    public static String getFilePath(String htmlFile) {
        String path1 = ConvertHtml2Image.class.getClassLoader().getResource("").getPath();
        File file1 = new File(path1).getParentFile();
        String path = file1.getAbsolutePath() + File.separator + "resources" + File.separator + "html" + File.separator + htmlFile;
        System.out.println(path);
        return path;
    }

    public static String getHtmlContent(String fileName, String charset) {

        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        String filePath = getFilePath(fileName);
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), charset));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取HTML文件，获取字符内容异常");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭流异常");
            }
        }
        return sb.toString();
    }

    public static String html2Img(String htmText, String saveImageLocation) {

        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        try {
            imageGenerator.loadHtml(htmText);
            Thread.sleep(100);
            imageGenerator.getBufferedImage();
            Thread.sleep(200);
            imageGenerator.saveAsImage(saveImageLocation);
            //imageGenerator.saveAsHtmlWithMap("hello-world.html", saveImageLocation);
            //不需要转换位图的，下面三行可以不要
//            BufferedImage sourceImg = ImageIO.read(new File(saveImageLocation));
//            sourceImg = transform_Gray24BitMap(sourceImg);
//            ImageIO.write(sourceImg, "BMP", new File(saveImageLocation));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("将HTML文件转换成图片异常");
        }
        return saveImageLocation;
    }


    public static BufferedImage transform_Gray24BitMap(BufferedImage image) {

        int h = image.getHeight();
        int w = image.getWidth();
        int[] pixels = new int[w * h]; // 定义数组，用来存储图片的像素
        int gray;
        PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
        try {
            pg.grabPixels(); // 读取像素值
        } catch (InterruptedException e) {
            throw new RuntimeException("转换成24位图的BMP时，处理像素值异常");
        }

        for (int j = 0; j < h; j++) { // 扫描列
            for (int i = 0; i < w; i++) { // 扫描行
                // 由红，绿，蓝值得到灰度值
                gray = (int) (((pixels[w * j + i] >> 16) & 0xff) * 0.8);
                gray += (int) (((pixels[w * j + i] >> 8) & 0xff) * 0.1);
                gray += (int) (((pixels[w * j + i]) & 0xff) * 0.1);
                pixels[w * j + i] = (255 << 24) | (gray << 16) | (gray << 8) | gray;
            }
        }

        MemoryImageSource s = new MemoryImageSource(w, h, pixels, 0, w);
        Image img = Toolkit.getDefaultToolkit().createImage(s);
        BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);//如果要转换成别的位图，改这个常量即可
        buf.createGraphics().drawImage(img, 0, 0, null);
        return buf;
    }

    public void convertToImage() {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//        String html = "<div style=\"width: 500px; height: 500px;\"><img src=\"http://pic1.zhimg.com/80/v2-e60a4cab4c6cdbd634df7246023cf580_hd.jpg\" width=\"350\" height=\"233\"><a href=\"#\" style=\"color: blue;\">沙雕</a></div>";
//        File f = new File("C:\\workspace\\javaSeWorkspace\\daliy_test\\src\\main\\resources\\html\\print.html");
//        String html = getHtmlFromFile("print.html");
//        imageGenerator.loadHtml(html);
        imageGenerator.loadUrl("http://www.baidu.com");
        /*try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        imageGenerator.getBufferedImage();
        imageGenerator.saveAsImage("d:/hello-world.png");
        imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
    }

    public static void main(String[] args) {
//        new ConvertHtml2Image().convertToImage();
//        new ConvertHtml2Image().getHtmlFromFile("print.html");
//        String htmlContent = ConvertHtml2Image.getHtmlContent("print.html", "UTF-8");
//        ConvertHtml2Image.html2Img(htmlContent,"D://a.jpg");
//        System.out.println(htmlContent);
        final Html2Image html2Image;
            String htmlContent = ConvertHtml2Image.getHtmlContent("print.html", "UTF-8");
            html2Image = Html2Image.fromHtml(htmlContent);
            html2Image.getImageRenderer().saveImage("d:/1.png");
    }
}
