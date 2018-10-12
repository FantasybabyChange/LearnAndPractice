package com.fantasybaby.file.html2image;

import gui.ava.html.Html2Image;
import gui.ava.html.image.generator.HtmlImageGenerator;
import gui.ava.html.pdf.PdfRenderer;
import gui.ava.html.renderer.ImageRenderer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
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
    public static void  convertToImageNew(String htmlContent,String outPutPath){
        final Html2Image html2Image;
//        String htmlContent = ConvertHtml2Image.getHtmlContent("print2.html", "UTF-8");
        System.out.println(htmlContent);
        html2Image = Html2Image.fromHtml(htmlContent);

        ImageRenderer imageRenderer = html2Image.getImageRenderer();
        imageRenderer.setWriteCompressionMode(1);
        imageRenderer.setWriteCompressionQuality(1f);
//        imageRenderer.setWriteCompressionType()
//        imageRenderer.setImageType("JPG");
//        imageRenderer.setHeight(1000);
//        imageRenderer.setWidth(1000);
//        imageRenderer.setAutoHeight(false);
        imageRenderer.saveImage(outPutPath);
    }
    public static OutputStream convertToImageStream(String  htmlContent){
        final Html2Image html2Image;
//        String htmlContent = ConvertHtml2Image.getHtmlContent("print2.html", "UTF-8");
        System.out.println(htmlContent);
        html2Image = Html2Image.fromHtml(htmlContent);
        ImageRenderer imageRenderer = html2Image.getImageRenderer();
        OutputStream outputStream = new ByteArrayOutputStream();
        imageRenderer.saveImage(outputStream,true);
        return outputStream;
    }
    public static void convertToPdf(String htmlConent,String outPutPath){
        final Html2Image html2Image;
        html2Image = Html2Image.fromHtml(htmlConent);
        PdfRenderer pdfRenderer = html2Image.getPdfRenderer();
        pdfRenderer.saveToPDF(outPutPath);
    }

    public static void main(String[] args) {
        String htmlContent = ConvertHtml2Image.getHtmlContent("print3.html", "UTF-8");
        ConvertHtml2ImageNew.convertToImageNew(htmlContent,"D://test.png");
    }
}
