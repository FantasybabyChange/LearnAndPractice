package com.fantasybaby.file.html2image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;

/**毫无卵用
 * @author fantasybaby
 * @date 2018/10/11
 */
public class ConvertHtmlToImagePanel2 {
    protected static void generateOutput() throws Exception {

        //load the webpage into the editor
        //JEditorPane ed = new JEditorPane(new URL("http://www.google.com"));
        JEditorPane ed = new JEditorPane(new URL("https://www.mekau.com/1634.html"));
        ed.setSize(1000,200);

        //create a new image
        BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        //paint the editor onto the image
        SwingUtilities.paintComponent(image.createGraphics(),
                ed,
                new JPanel(),
                0, 0, image.getWidth(), image.getHeight());
        //save the image to file
        ImageIO.write((RenderedImage)image, "png", new File("D://html.png"));
    }
    public static void main(String[] args) {
        try {
            generateOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
