package com.fantasybaby.file.html2image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**https://coderanch.com/wiki/659907/RTF-Image
 * https://www.mekau.com/1634.html
 * @author fantasybaby
 * @date 2018/10/11
 */
public class ConvertByHtmlJPanel {
    public void convertToImage(){
        String html = ConvertHtml2Image.getHtmlContent("print3.html","UTF-8");
        System.out.println(html);
        JEditorPane pane = new JEditorPane();
        pane.setEditable(false);
        pane.setContentType("text/html");
        pane.setText(html);
        pane.setSize(pane.getPreferredSize());
        pane.setBackground(Color.WHITE);
        BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        /*
         * Have the image painted by SwingUtilities
         */
        JPanel container = new JPanel();
//        container.setBackground(Color.WHITE);
        SwingUtilities.paintComponent(g, pane, container, 0, 0, 1000, image.getHeight());
        g.dispose();
        // get the byte array of the image (as jpeg)
        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();*/
        try {
            ImageIO.write(image, "png", new File("D://test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConvertByHtmlJPanel().convertToImage();
    }
}
