package com.fantasybaby.file.html2image;

import gui.ava.html.Html2Image;
import gui.ava.html.image.generator.HtmlImageGenerator;
import gui.ava.html.renderer.ImageRenderer;

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
public class ConvertHtml2ImageNew {
    public static String getFilePath(String htmlFile) {
        String path1 = ConvertHtml2ImageNew.class.getClassLoader().getResource("").getPath();
        File file1 = new File(path1).getParentFile();
        String path = file1.getAbsolutePath() + File.separator + "resources" + File.separator + "html" + File.separator + htmlFile;
        System.out.println(path);
        return path;
    }
    public static void  convertToImageNew(){
        final Html2Image html2Image;
        String htmlContent = ConvertHtml2Image.getHtmlContent("print.html", "GBK");
        html2Image = Html2Image.fromHtml(htmlContent);
        ImageRenderer imageRenderer = html2Image.getImageRenderer();
        imageRenderer.setHeight(1000);
        imageRenderer.setWidth(1000);
        imageRenderer.setAutoHeight(false);
        imageRenderer.saveImage("d:/1.png");
    }
    public static void main(String[] args) {
        ConvertHtml2ImageNew.convertToImageNew();
    }
}
