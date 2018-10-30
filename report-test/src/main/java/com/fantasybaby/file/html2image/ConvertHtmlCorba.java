package com.fantasybaby.file.html2image;

/**毫无卵用 等待更新
 * @author fantasybaby
 * @date 2018/10/11
 */
public class ConvertHtmlCorba {
    private static final String TEST_URI = "http://baidu.com";

    public static void main(String[] args) throws Exception {
      /*  UserAgentContext uacontext = new SimpleUserAgentContext();
        DocumentBuilderImpl builder = new DocumentBuilderImpl(uacontext);
        URL url = new URL(TEST_URI);
        InputStream in = url.openConnection().getInputStream();
        try {
            Reader reader = new InputStreamReader(in, "ISO-8859-1");
            InputSourceImpl inputSource = new InputSourceImpl(reader, TEST_URI);
            Document d = builder.parse(inputSource);
            HTMLDocumentImpl document = (HTMLDocumentImpl) d;
            HTMLCollection images = document.getImages();
            int length = images.getLength();
            for (int i = 0; i < length; i++) {
                System.out.println("- Image#" + i + ": " + images.item(i));
            }
        } finally {
            in.close();
        }*/
    }

}
